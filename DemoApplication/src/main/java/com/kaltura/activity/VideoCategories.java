package com.kaltura.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.kaltura.bar.ActionBar;
import com.kaltura.boxAdapter.BoxAdapterAllCategories;
import com.kaltura.client.enums.KalturaMediaType;
import com.kaltura.client.types.KalturaCategory;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaMediaEntryFilter;
import com.kaltura.components.GridForLandLargeScreen;
import com.kaltura.components.GridForPortLargeScreen;
import com.kaltura.components.ItemGrid;
import com.kaltura.enums.States;
import com.kaltura.mediatorActivity.TemplateActivity;
import com.kaltura.services.Category;
import com.kaltura.services.Media;
import com.kaltura.sharing.Sharing;
import com.kaltura.utils.SearchTextCategory;
import com.kaltura.utils.Sort;
import com.kaltura.utils.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;

//import com.nostra13.universalimageloader.core.ImageLoadingListener;

public class VideoCategories extends TemplateActivity implements Observer,
		ListView.OnItemClickListener {

	private ListView lvAllCategories;
	private List<KalturaCategory> listCategory;
	private BoxAdapterAllCategories listAllCategories;
	private EditText etSearch;
	private SearchTextCategory searchText;
	private View search;
	private TextView tv_bar;
	private LinearLayout ll_categories;
	private int categoryId;
	private List<KalturaMediaEntry> listEntries;
	private int textColor;
	private int arrow;
	private List<GridForPortLargeScreen> contentPort;
	private List<GridForLandLargeScreen> contentLand;
	private LinearLayout ll_conent;
	private String categoryName = "";
	private int orientation;
	private boolean largeScreen;
	private boolean isMostPopular = false;
	private Activity activity;
	private List<ImageView> view;
	private List<ProgressBar> progressBar;
	private int count = 0;
	int k = 0;
	private static int position = 0;
	private int transparentColor = 255;
	private boolean visibleHightLight;
	private Sharing sharing;
	public static int CATEGORIES_MAX_COUNT = 20;

	public VideoCategories() {
		searchText = new SearchTextCategory();
		searchText.addObserver(this);
		listCategory = new ArrayList<KalturaCategory>();
		listEntries = new ArrayList<KalturaMediaEntry>();
		contentPort = new ArrayList<GridForPortLargeScreen>();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
		// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		setContentView(R.layout.categories);

		this.activity = this;
		// this.position = 0;
		Configuration c = getResources().getConfiguration();
		orientation = c.orientation;
		extractBundle();
		Log.w(TAG, "Position: " + position);

		switch (determineScreenSize()) {
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			Log.w(TAG, "not large");
			textColor = Color.BLACK;
			arrow = R.drawable.arrow;
			transparentColor = 0;
			visibleHightLight = false;
			setViewOtherScreens();
			if (bar != null) {
				bar.setTitle(getText(R.string.action_bar_catigories));
				bar.setVisibleBackButton(View.INVISIBLE);
			}
			sharing = new Sharing(this);
			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				if (bar != null) {
					bar.setVisibleSearchButon(View.INVISIBLE);
				}
				new DownloadListCatigoriesTask().execute();
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				search.setVisibility(View.GONE);
				if (bar != null) {
					bar.setVisibleSearchButon(View.VISIBLE);
				}
				// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				new DownloadListCatigoriesTask().execute();
				break;
			default:
				break;
			}
			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			Log.w(TAG, "large");
			textColor = Color.WHITE;
			arrow = R.drawable.arrow_white;
			transparentColor = 0;
			visibleHightLight = true;
			// Large Screen
			setViewLargeScreen();
			sharing = new Sharing(this);
			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				if (tv_bar != null) {
					tv_bar.setText(getText(R.string.action_bar_catigories));
				}
				if (bar != null) {
					bar.setTitle("");
					bar.setVisibleBackButton(View.VISIBLE);
					bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
				}
				new DownloadListCatigoriesTask().execute();
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

				if (bar != null) {
					bar.setTitle(getText(R.string.action_bar_catigories));
					bar.setVisibleBackButton(View.INVISIBLE);
					bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
					bar.setVisibleSearchButon(View.GONE);
				}
				new DownloadListCatigoriesTask().execute();
				break;
			default:
				break;
			}
			break;

		default:
			Log.w(TAG, "Undefined screen: ");
			Log.w(TAG,
					"width: " + display.getWidth() + " height: "
							+ display.getHeight());
			textColor = Color.WHITE;
			arrow = R.drawable.arrow_white;
			transparentColor = 0;
			visibleHightLight = true;
			// Large Screen
			setViewLargeScreen();
			sharing = new Sharing(this);
			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				if (tv_bar != null) {
					tv_bar.setText(getText(R.string.action_bar_catigories));
				}
				if (bar != null) {
					bar.setTitle("");
				}
				bar.setVisibleSearchButon(View.INVISIBLE);
				bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
				new DownloadListCatigoriesTask().execute();
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				if (bar != null) {
					bar.setTitle(getText(R.string.action_bar_catigories));
					bar.setVisibleSearchButon(View.VISIBLE);
					bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
					bar.setVisibleBackButton(View.INVISIBLE);
					bar.setVisibleSearchButon(View.GONE);
				}
				// GridViewForLand();
				new DownloadListCatigoriesTask().execute();
				break;
			default:
				break;
			}
			break;
		}
	}

	private void extractBundle() {
		try {
			Bundle extras = getIntent().getExtras();
			largeScreen = extras.getBoolean("largeScreen");
			Log.w(TAG, "extracted data: ");
			Log.w(TAG, "largeScreen: " + largeScreen);
		} catch (Exception e) {
			Utils.handleException(TAG, e);
			largeScreen = false;
		}
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);

	}

	@Override
	public void onStart() {
		super.onStart();
		switch (determineScreenSize()) {
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			sharing.addListener();
			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				ll_categories.getLayoutParams().width = display.getWidth();
				break;
			}
			break;
		default:
			sharing.addListener();
			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				ll_categories.getLayoutParams().width = display.getWidth() / 2;
				break;
			}
			break;
		}

	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		setContentView(R.layout.categories);
		this.orientation = newConfig.orientation;

		Log.w(TAG, "Position: " + position);

		Log.w(TAG, "get orientation: " + orientation);
		Log.w(TAG, "screen size: " + determineScreenSize());
		switch (determineScreenSize()) {
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			textColor = Color.BLACK;
			arrow = R.drawable.arrow;
			setViewOtherScreens();
			addListCategoriesOnScreen();
			if (bar != null) {
				bar.setTitle(getText(R.string.action_bar_catigories));
				bar.setVisibleBackButton(View.INVISIBLE);
			}
			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				search.setVisibility(View.VISIBLE);
				bar.setVisibleSearchButon(View.INVISIBLE);
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				search.setVisibility(View.GONE);
				bar.setVisibleSearchButon(View.VISIBLE);
				bar.setVisibleBackButton(View.INVISIBLE);
				break;
			default:
				break;
			}
			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
			Log.w(TAG,
					"width: " + display.getWidth() + " height: "
							+ display.getHeight());
			textColor = Color.WHITE;
			arrow = R.drawable.arrow_white;
			// Large Screen
			addListCategoriesOnScreen();
			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				if (tv_bar != null) {
					tv_bar.setText(getText(R.string.action_bar_catigories));
				}
				bar.setTitle("");
				bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
				bar.setVisibleBackButton(View.VISIBLE);
				GridViewForPort();
				new DownloadListCatigoriesTask().execute();
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				bar.setTitle(getText(R.string.action_bar_catigories));
				bar.setVisibleBackButton(View.INVISIBLE);
				bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
				bar.setVisibleBackButton(View.INVISIBLE);
				bar.setVisibleSearchButon(View.GONE);
				GridViewForLand();
				new DownloadListCatigoriesTask().execute();
				break;
			default:

				break;
			}
			break;
		default:
			Log.w(TAG, "Undefined screen: ");
			Log.w(TAG,
					"width: " + display.getWidth() + " height: "
							+ display.getHeight());
			textColor = Color.WHITE;
			arrow = R.drawable.arrow_white;
			// Large Screen
			addListCategoriesOnScreen();

			switch (orientation) {
			case Configuration.ORIENTATION_PORTRAIT:
			case Configuration.ORIENTATION_UNDEFINED:
			case Configuration.ORIENTATION_SQUARE:
				if (tv_bar != null) {
					tv_bar.setText(getText(R.string.action_bar_catigories));
				}
				bar.setTitle("");
				bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
				bar.setVisibleBackButton(View.VISIBLE);
				GridViewForPort();
				new DownloadListCatigoriesTask().execute();
				break;
			case Configuration.ORIENTATION_LANDSCAPE:
				bar.setTitle(getText(R.string.action_bar_catigories));
				bar.setVisibleBackButton(View.INVISIBLE);
				bar.setBackgroundBackButton(R.drawable.button_back_large_screen_selector);
				bar.setVisibleBackButton(View.INVISIBLE);
				bar.setVisibleSearchButon(View.GONE);
				GridViewForLand();
				new DownloadListCatigoriesTask().execute();

				break;
			default:
				break;
			}
			break;
		}
	}
	
	private void setParamsOnItemGrid( ItemGrid ig, final int index) {
		ig.getName().setText(listEntries.get(index).name);
		ig.getEpisode().setText(Utils.durationInSecondsToString(listEntries.get(index).duration));
		ig.getThumb().setScaleType(ImageView.ScaleType.CENTER_CROP);
		ig.getName().setVisibility(View.VISIBLE);
		ig.getEpisode().setVisibility(View.VISIBLE);
		ig.getThumb().setVisibility(View.VISIBLE);
		ig.getProgressBar().setVisibility(View.VISIBLE);
		
		ig.getThumb().setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Log.w(TAG, "click " + index);
				if (index < listEntries.size()) {
					KalturaMediaEntry entry = listEntries.get(index);
					getActivityMediator().showInfo(entry.id, categoryName,
							entry.partnerId);
					// showUploadDialog(listEntries.get(index));
					Log.w(TAG, "click first " + index);
				}
			}
		});
	}

	private void GridViewForPort() {

		if (listEntries.size() > 0) {
			contentPort = new ArrayList<GridForPortLargeScreen>();
			Log.w(TAG, "size: " + listEntries.size());
			int countConent = listEntries.size() / 4;
			Log.w(TAG, "countConent: " + countConent);
			int rest = listEntries.size() % 4;
			Log.w(TAG, "rest: " + rest);

			if (rest != 0) {
				countConent = countConent + 1;
				Log.w(TAG, "countConent: " + countConent);
			}

			// Create countContent contents
			int offset = 0;
			int size = listEntries.size();

			if (size > 4) {
				Log.w(TAG, "1 offset: " + offset);
				contentPort.add(new GridForPortLargeScreen(TAG, this, offset));
				size = size - 4;
				for (int i = 0; i < countConent - 1; i++) {
					offset = offset + 4;
					size = size - 4;
					Log.w(TAG, "3 offset: " + offset);
					contentPort.add(new GridForPortLargeScreen(TAG, this, offset));
				}
			} else {
				Log.w(TAG, "2 offset: " + 0);
				contentPort.add(new GridForPortLargeScreen(TAG, this, 0));
			}

			ll_conent.removeAllViewsInLayout();
			// set params
			for (final GridForPortLargeScreen templateContent : contentPort) {
				ll_conent.addView(templateContent.getRowGrid());
				templateContent.getRowGrid().getLayoutParams().width = (int) (float) (1.5 * display.getWidth());
				templateContent.getRowGrid().getLayoutParams().height = display.getHeight() / 3;

				if (templateContent.getOffset() < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getFirstItemGrid(), templateContent.getOffset());
				} else {
					Log.w(TAG, "first iteam grid not created");
				}
				if (templateContent.getOffset() + 1 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getFirstItemGrid(), templateContent.getOffset() + 1);
				} else {
					Log.w(TAG, "second iteam grid not created");
				}
				if (templateContent.getOffset() + 2 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getThirdItemGrid(), templateContent.getOffset() + 2);
				} else {
					Log.w(TAG, "third iteam grid not created");
				}
				if (templateContent.getOffset() + 3 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getFourthItemGrid(), templateContent.getOffset() + 3);
				} else {
					Log.w(TAG, "fourth iteam grid not created");
				}
				
			}
		} else {
			Log.w(TAG, "list size is 0");
		}
	}

	private void GridViewForLand() {

		if (listEntries.size() > 0) {
			contentLand = new ArrayList<GridForLandLargeScreen>();
			Log.w(TAG, "size: " + listEntries.size());
			int countConent = listEntries.size() / 6;
			Log.w(TAG, "countConent: " + countConent);
			int rest = listEntries.size() % 6;
			Log.w(TAG, "rest: " + rest);

			if (rest != 0) {
				countConent = countConent + 1;
				Log.w(TAG, "countConent: " + countConent);
			}

			// Create countContent contents
			int offset = 0;
			int size = listEntries.size();

			if (size > 6) {
				Log.w(TAG, "1 offset: " + offset);
				contentLand.add(new GridForLandLargeScreen(TAG, this, offset));
				size = size - 6;
				for (int i = 0; i < countConent - 1; i++) {
					offset = offset + 6;
					size = size - 6;
					Log.w(TAG, "3 offset: " + offset);
					contentLand.add(new GridForLandLargeScreen(TAG, this,
							offset));
				}
			} else {
				Log.w(TAG, "2 offset: " + 0);
				contentLand.add(new GridForLandLargeScreen(TAG, this, 0));
			}

			ll_conent.removeAllViewsInLayout();
			// set params
			for (final GridForLandLargeScreen templateContent : contentLand) {
				ll_conent.addView(templateContent.getRowGrid());
				templateContent.getRowGrid().getLayoutParams().width = display
						.getWidth() - ll_categories.getWidth();// display.getWidth();
				templateContent.getRowGrid().getLayoutParams().height = display
						.getHeight() / 2;

				if (templateContent.getOffset() < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getFirstItemGrid(), templateContent.getOffset());
				} else {
					Log.w(TAG, "first iteam grid not created");
				}
				if (templateContent.getOffset() + 1 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getSecondItemGrid(), templateContent.getOffset() + 1);
				} else {
					Log.w(TAG, "second iteam grid not created");
				}
				if (templateContent.getOffset() + 2 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getThirdItemGrid(), templateContent.getOffset()+ 2);
				} else {
					Log.w(TAG, "third iteam grid not created");
				}
				if (templateContent.getOffset() + 3 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getFourthItemGrid(), templateContent.getOffset() + 3);
				} else {
					Log.w(TAG, "fourth iteam grid not created");
				}
				if (templateContent.getOffset() + 4 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getFifthItemGrid(), templateContent.getOffset()+ 4);
				} else {
					Log.w(TAG, "fifth iteam grid not created");
				}
				if (templateContent.getOffset() + 5 < listEntries.size()) {
					setParamsOnItemGrid( templateContent.getSixthItemGrid(), templateContent.getOffset()+ 5);
				} else {
					Log.w(TAG, "sixth iteam grid not created");
				}

			}
		} else {
			Log.w(TAG, "list size is 0");
		}
	}

	private void ImageLoaderForPort() {
		Log.w(TAG, "Start image loader");

		DisplayImageOptions options = new DisplayImageOptions.Builder() // .showStubImage(R.drawable.arrow)
				// .showImageForEmptyUrl(R.drawable.arrow)
				.cacheInMemory(true).cacheOnDisc(true).build();

		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				activity).threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSize(1500000000) // 150 Mb
				.denyCacheImageMultipleSizesInMemory().build();
		// Initialize ImageLoaderForPort with configuration.
		ImageLoader.getInstance().init(config);
		imageLoader.init(config);

		final List<String> url = new ArrayList<String>();
		view = new ArrayList<ImageView>();
		progressBar = new ArrayList<ProgressBar>();

		for (KalturaMediaEntry entry : listEntries) {
			url.add(entry.thumbnailUrl + "/width/"
					+ new Integer(/*
								 * display.getWidth()
								 */300).toString() + "/height/"
					+ new Integer(/*
								 * display.getHeight()/2
								 */300).toString());
		}
		count = 0;
		for (String string : url) {
			Log.w(TAG, "url: " + count++ + " " + string);
		}

		int state = 0;
		count = 0;
		for (int j = 0; j < listEntries.size(); j++) {

			switch (state) {
			case 0:
				// left
				Log.w(TAG, "xl: " + count);
				view.add(contentPort.get(count).getFirstItemGrid().getThumb());
				progressBar.add(contentPort.get(count).getFirstItemGrid()
						.getProgressBar());
				state = 1;
				break;
			case 1:
				// center
				Log.w(TAG, "xc: " + count);
				view.add(contentPort.get(count).getSecondItemGrid().getThumb());
				progressBar.add(contentPort.get(count).getSecondItemGrid()
						.getProgressBar());
				state = 2;
				break;
			case 2:
				// right
				Log.w(TAG, "xr: " + count);
				view.add(contentPort.get(count).getThirdItemGrid().getThumb());
				progressBar.add(contentPort.get(count).getThirdItemGrid()
						.getProgressBar());
				state = 3;
				break;
			case 3:
				// right
				Log.w(TAG, "xr: " + count);
				view.add(contentPort.get(count).getFourthItemGrid().getThumb());
				progressBar.add(contentPort.get(count).getFourthItemGrid()
						.getProgressBar());
				count++;
				state = 0;
				break;
			}
		}

		count = 0;
		Log.w(TAG, "size: " + progressBar.size());
		k = 0;
		for (String string : url) {
			imageLoader.displayImage(string, view.get(count), options,
					new ImageLoadingListener() {

						@Override
						public void onLoadingStarted(String imageUri, View view) {
							// do nothing
							Log.w(TAG, "onLoadingStarted");

						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							Log.w(TAG, "onLoadingFailed");
							imageLoader.clearMemoryCache();
							imageLoader.clearDiscCache();

						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							// do nothing
							if (k < progressBar.size()) {
								progressBar.get(k++).setVisibility(View.GONE);
							}
							Log.w(TAG, "onLoadingComplete: " + k);
							if (k >= url.size()
									&& determineScreenSize() != Configuration.SCREENLAYOUT_SIZE_LARGE) {
								setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
							}

						}

						@Override
						public void onLoadingCancelled(String imageUri,
								View view) {
							// TODO Auto-generated method stub

						}
					});
			count++;
		}

	}

	private void ImageLoaderForLand() {
		Log.w(TAG, "Start image loader");

		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true).build();

		// This configuration tuning is custom. You can tune every option, you
		// may tune some of them,
		// or you can create default configuration by
		// ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				activity).threadPoolSize(3)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheSize(1500000000) // 150 Mb
				.denyCacheImageMultipleSizesInMemory().build();
		// Initialize ImageLoaderForPort with configuration.
		ImageLoader.getInstance().init(config);
		imageLoader.init(config);

		final List<String> url = new ArrayList<String>();
		view = new ArrayList<ImageView>();
		progressBar = new ArrayList<ProgressBar>();

		for (KalturaMediaEntry entry : listEntries) {
			url.add(entry.thumbnailUrl + "/width/"
					+ new Integer(/*
								 * display.getWidth()
								 */300).toString() + "/height/"
					+ new Integer(/*
								 * display.getHeight()/2
								 */300).toString());
		}
		count = 0;
		for (String string : url) {
			Log.w(TAG, "url: " + count++ + " " + string);
		}

		int state = 0;
		count = 0;
		for (int j = 0; j < listEntries.size(); j++) {

			switch (state) {
			case 0:
				// left
				Log.w(TAG, "xl: " + count);
				view.add(contentLand.get(count).getFirstItemGrid().getThumb());
				progressBar.add(contentLand.get(count).getFirstItemGrid()
						.getProgressBar());
				state = 1;
				break;
			case 1:
				// center
				Log.w(TAG, "xc: " + count);
				view.add(contentLand.get(count).getSecondItemGrid().getThumb());
				progressBar.add(contentLand.get(count).getSecondItemGrid()
						.getProgressBar());
				state = 2;
				break;
			case 2:
				// right
				Log.w(TAG, "xr: " + count);
				view.add(contentLand.get(count).getThirdItemGrid().getThumb());
				progressBar.add(contentLand.get(count).getThirdItemGrid()
						.getProgressBar());
				state = 3;
				break;
			case 3:
				// right
				Log.w(TAG, "xr: " + count);
				view.add(contentLand.get(count).getFourthItemGrid().getThumb());
				progressBar.add(contentLand.get(count).getFourthItemGrid()
						.getProgressBar());
				state = 4;
				break;
			case 4:
				// right
				Log.w(TAG, "xr: " + count);
				view.add(contentLand.get(count).getFifthItemGrid().getThumb());
				progressBar.add(contentLand.get(count).getFifthItemGrid()
						.getProgressBar());
				state = 5;
				break;
			case 5:
				// right
				Log.w(TAG, "xr: " + count);
				view.add(contentLand.get(count).getSixthItemGrid().getThumb());
				progressBar.add(contentLand.get(count).getSixthItemGrid()
						.getProgressBar());
				count++;
				state = 0;
				break;
			}
		}

		count = 0;
		Log.w(TAG, "size: " + progressBar.size());
		k = 0;
		for (String string : url) {
			imageLoader.displayImage(string, view.get(count), options,
					new ImageLoadingListener() {

						@Override
						public void onLoadingStarted(String imageUri, View view) {
							// do nothing
							Log.w(TAG, "onLoadingStarted");

						}

						@Override
						public void onLoadingFailed(String imageUri, View view,
								FailReason failReason) {
							Log.w(TAG, "onLoadingFailed");
							imageLoader.clearMemoryCache();
							imageLoader.clearDiscCache();

						}

						@Override
						public void onLoadingComplete(String imageUri,
								View view, Bitmap loadedImage) {
							// do nothing
							if (k < progressBar.size()) {
								progressBar.get(k++).setVisibility(View.GONE);
							}
							Log.w(TAG, "onLoadingComplete: " + k);
							if (k >= url.size()
									&& determineScreenSize() != Configuration.SCREENLAYOUT_SIZE_LARGE) {
								setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
							}

						}

						@Override
						public void onLoadingCancelled(String imageUri,
								View view) {
							// TODO Auto-generated method stub

						}
					});
			count++;
		}

	}

	private void setViewOtherScreens() {
		ll_conent = ((LinearLayout) findViewById(R.id.ll_conent));
		etSearch = (EditText) findViewById(R.id.et_search);
		bar = new ActionBar(this, TAG);
		lvAllCategories = (ListView) findViewById(R.id.lv_category);
		lvAllCategories.setOnItemClickListener(this);
		search = findViewById(R.id.search);
	}

	private void setViewLargeScreen() {
		ll_conent = ((LinearLayout) findViewById(R.id.ll_conent));
		tv_bar = (TextView) findViewById(R.id.tv_bar);
		bar = new ActionBar(this, TAG);
		ll_categories = (LinearLayout) findViewById(R.id.ll_categories);
		etSearch = (EditText) findViewById(R.id.et_search);
		lvAllCategories = (ListView) findViewById(R.id.lv_category);
		lvAllCategories.setOnItemClickListener(this);
		lvAllCategories.setSelection(0);

		// Bar
		search = findViewById(R.id.search);

	}

	public void addListCategoriesOnScreen() {
		Log.w(TAG, "update data");

		/**
		 * Sort by name
		 */
		Collections.sort(listCategory, new Sort<KalturaCategory>("name",
				"compareTo"));
		listAllCategories = new BoxAdapterAllCategories(context, listCategory,
				textColor, arrow, Color.argb(transparentColor, 1, 18, 35));
		if (largeScreen) {
			KalturaCategory mp = new KalturaCategory();
			mp.name = "Most Popular";
			mp.id = 1;
			listCategory.add(0, mp);
		}

		listAllCategories.setVisiblityHighlight(this.visibleHightLight);
		listAllCategories.notifyDataSetChanged();
		listAllCategories.setHighlightIndex(this.position);
		lvAllCategories.setAdapter(listAllCategories);
		searchText.init(TAG, etSearch, listCategory);
		etSearch.addTextChangedListener(searchText);
	}

	/**
	 * Called to process touch screen events.
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {

		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			// Hide the keyboard on the screen of a finger
			// imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(),
			// 0);
			break;
		}
		return super.dispatchTouchEvent(ev);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_MENU:
			return true;
		case KeyEvent.KEYCODE_BACK:
			moveTaskToBack(true);
			break;
		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_bar_menu:
			getActivityMediator().showMain();
			this.position = 0;
			finish();
			break;
		case R.id.iv_bar_search:
			if (search.getVisibility() == View.GONE) {
				search.setVisibility(View.VISIBLE);
			} else {
				search.setVisibility(View.GONE);
			}
			break;
		case R.id.rl_button_back:

			switch (determineScreenSize()) {
			case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			case Configuration.SCREENLAYOUT_SIZE_SMALL:
				// finish();
				break;
			case Configuration.SCREENLAYOUT_SIZE_LARGE:
			case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
				if (ll_categories.getVisibility() == View.VISIBLE) {
					ll_categories.setVisibility(View.GONE);
					tv_bar.setVisibility(View.GONE);
				} else {
					ll_categories.setVisibility(View.VISIBLE);
					tv_bar.setVisibility(View.VISIBLE);
				}

				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.w(TAG, "size" + determineScreenSize());
		switch (determineScreenSize()) {
		case Configuration.SCREENLAYOUT_SIZE_NORMAL:
		case Configuration.SCREENLAYOUT_SIZE_SMALL:
			Log.w(TAG, "itemClick: position = " + position + ", id = " + id);
			getActivityMediator().showVideoCategory(
					listAllCategories.getCategoryId(position),
					listAllCategories.getCategoryName(position));
			break;
		case Configuration.SCREENLAYOUT_SIZE_LARGE:
		case Configuration.SCREENLAYOUT_SIZE_UNDEFINED:
			Log.w(TAG, "itemClick: position = " + position + ", id = " + id
					+ "selectedId" + parent.getSelectedItemId());
			this.position = position;

			listAllCategories.setHighlightIndex(position);
			listAllCategories.setVisiblityHighlight(true);
			listAllCategories.notifyDataSetChanged();

			categoryId = listAllCategories.getCategoryId(position);
			categoryName = listAllCategories.getCategoryName(position);
			if ((orientation == Configuration.ORIENTATION_PORTRAIT)
					|| (orientation == Configuration.ORIENTATION_UNDEFINED)
					|| (orientation == Configuration.ORIENTATION_SQUARE)) {
				bar.setTitle(categoryName);
			} else {
				bar.setTitle("Categories");
			}
			if (categoryName.equals("Most Popular")) {
				// most popular
				new DownloadListEntriesTask().execute();
				isMostPopular = true;
			} else {
				new DownloadListEntriesTask().execute();
				isMostPopular = false;
			}
			break;
		}
	}

	@Override
	public void update(Observable paramObservable, Object paramObject) {
		/**
		 * Sort by name
		 */
		Collections.sort((List<KalturaCategory>) paramObject,
				new Sort<KalturaCategory>("name", "compareTo"));
		listAllCategories = new BoxAdapterAllCategories(context,
				(List<KalturaCategory>) paramObject, textColor, arrow,
				Color.argb(0, 1, 18, 35));
		lvAllCategories.setAdapter(listAllCategories);
	}

	private class DownloadListCatigoriesTask extends
			AsyncTask<Void, States, List<KalturaCategory>> {

		private String message;

		@Override
		protected List<KalturaCategory> doInBackground(Void... params) {
			// Test for connection
			Log.w(TAG, "Thread is start.");
			try {
				if (Utils.checkInternetConnection(getApplicationContext())) {
					/**
					 * Getting list of all categories
					 */
					publishProgress(States.LOADING_DATA);
					listCategory = Category.listAllCategories(TAG, 1,
							CATEGORIES_MAX_COUNT);
					Log.w(TAG, "Thread is end.");
				}
			} catch (Exception e) {
				message = Utils.handleException(TAG, e);
				publishProgress(States.NO_CONNECTION);
			}
			return listCategory;
		}

		@Override
		protected void onPostExecute(final List<KalturaCategory> listCategory) {
			progressDialog.hide();
			// setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

			try {
				if (Utils.checkInternetConnection(getApplicationContext())) {
					addListCategoriesOnScreen();
					if (determineScreenSize() == Configuration.SCREENLAYOUT_SIZE_LARGE
							|| determineScreenSize() == Configuration.SCREENLAYOUT_SIZE_UNDEFINED) {

						if (largeScreen) {
							// most popular
							Log.w(TAG, "pos: " + position + "name "
									+ categoryName);
							if (position == 0
									|| listAllCategories
											.getCategoryId(position) == 1) {
								Log.w(TAG, "is true");
								categoryName = "Most Popular";
								isMostPopular = true;
							} else {
								categoryId = listAllCategories
										.getCategoryId(position);
								categoryName = listAllCategories
										.getCategoryName(position);
								isMostPopular = false;
							}
							new DownloadListEntriesTask().execute();

						} else {
							categoryId = listAllCategories
									.getCategoryId(position);
							categoryName = listAllCategories
									.getCategoryName(position);
							new DownloadListEntriesTask().execute();
							isMostPopular = false;
						}
					}
				} else {
					Log.w(TAG, "Data weren't load!");
					VideoCategories.this.finish();
				}
			} catch (Exception e) {
				message = Utils.handleException(TAG, e);
				publishProgress(States.NO_CONNECTION);
			}
		}

		@Override
		protected void onProgressUpdate(States... progress) {
			for (States state : progress) {
				if (state == States.LOADING_DATA) {
					progressDialog.hide();
					showProgressDialog("Loading data...");
				}
				if (state == States.NO_CONNECTION) {
					progressDialog.hide();
					Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
				}
			}
		}
	}

	private class DownloadListEntriesTask extends AsyncTask<Void, States, Void> {

		private String message;

		@Override
		protected Void doInBackground(Void... params) {
			// Test for connection
			try {
				if (Utils.checkInternetConnection(getApplicationContext())) {
					// Getting list of all entries category

					publishProgress(States.LOADING_DATA);
					// Getting list of all entries category
					KalturaMediaEntryFilter filter = new KalturaMediaEntryFilter();
					filter.mediaTypeEqual = KalturaMediaType.VIDEO;
					if (!isMostPopular) {
						filter.categoriesIdsMatchAnd = new Integer(categoryId)
								.toString();
					}
					listEntries = Media.listAllEntriesByIdCategories(TAG,
							filter, 1, Media.ENTRIES_MAX_COUNT);
				}
				Log.w(TAG, "Thread is end!");
			} catch (Exception e) {
				message = Utils.handleException(TAG, e);
				publishProgress(States.NO_CONNECTION);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void param) {
			progressDialog.hide();

			switch (determineScreenSize()) {
			case Configuration.SCREENLAYOUT_SIZE_NORMAL:
			case Configuration.SCREENLAYOUT_SIZE_SMALL:
				switch (orientation) {
				case Configuration.ORIENTATION_PORTRAIT:
				case Configuration.ORIENTATION_UNDEFINED:
				case Configuration.ORIENTATION_SQUARE:
					break;
				case Configuration.ORIENTATION_LANDSCAPE:
					break;
				default:
					break;
				}
				break;
			case Configuration.SCREENLAYOUT_SIZE_LARGE:
				switch (orientation) {
				case Configuration.ORIENTATION_PORTRAIT:
				case Configuration.ORIENTATION_UNDEFINED:
				case Configuration.ORIENTATION_SQUARE:
					GridViewForPort();
					ImageLoaderForPort();
					break;
				case Configuration.ORIENTATION_LANDSCAPE:
					GridViewForLand();
					ImageLoaderForLand();
					break;
				default:
					break;
				}
				break;
			default:
				switch (orientation) {
				case Configuration.ORIENTATION_PORTRAIT:
				case Configuration.ORIENTATION_UNDEFINED:
				case Configuration.ORIENTATION_SQUARE:
					GridViewForPort();
					ImageLoaderForPort();
					break;
				case Configuration.ORIENTATION_LANDSCAPE:
					GridViewForLand();
					ImageLoaderForLand();
					break;
				default:
					break;
				}
				break;
			}
		}

		@Override
		protected void onProgressUpdate(States... progress) {
			for (States state : progress) {
				if (state == States.LOADING_DATA) {
					progressDialog.hide();
				}
				if (state == States.NO_CONNECTION) {
					progressDialog.hide();
					Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
				}
			}
		}
	}
}
