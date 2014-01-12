package com.kaltura.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaltura.bar.ActionBar;
import com.kaltura.client.enums.KalturaMediaType;
import com.kaltura.client.types.KalturaBaseEntry;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.client.types.KalturaMediaEntryFilter;
import com.kaltura.components.GridForLand;
import com.kaltura.components.GridForPort;
import com.kaltura.components.ItemGrid;
import com.kaltura.enums.States;
import com.kaltura.mediatorActivity.TemplateActivity;
import com.kaltura.services.Media;
import com.kaltura.utils.SearchTextEntry;
import com.kaltura.utils.Sort;
import com.kaltura.utils.Utils;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//import com.nostra13.universalimageloader.core.ImageLoadingListener;

public class MostPopular extends TemplateActivity implements Observer {

    private List<KalturaMediaEntry> listEntries;
    private List<KalturaMediaEntry> copyEntries;
    private EditText etSearch;
    private SearchTextEntry searchText;
    private KalturaMediaEntry mostPlaysEntry;
    private RelativeLayout rl_category;
    private DownloadListCatigoriesTask downloadTask;
    private View search;
    private int width;
    private ProgressBar pb_loading;
    private LinearLayout ll_base;
    private int sizeListentry;
    private int orientation;
    private View itemTopRight;
    private List<GridForLand> contentLand;
    private List<GridForPort> contentPort;
    private KalturaMediaEntry rightTopEntry;
    private boolean listCategoriesIsLoaded = false;
    private Activity activity;
    private List<ImageView> view;
    private List<ProgressBar> progressBar;
    private int count = 0;
    int k = 0;

    public MostPopular() {
        listEntries = new ArrayList<KalturaMediaEntry>();
        copyEntries = new ArrayList<KalturaMediaEntry>();
        searchText = new SearchTextEntry();
        searchText.addObserver(this);
        downloadTask = new DownloadListCatigoriesTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        setContentView(R.layout.category);
        Configuration c = getResources().getConfiguration();
        orientation = c.orientation;

        activity = this;

        Log.w(TAG, "create orien3: " + getScreenOrientation() + " " + orientation);

        setView();

        ll_base = (LinearLayout) findViewById(R.id.ll_base);
        rl_category.setVisibility(View.INVISIBLE);
        if (bar != null) {
            bar.setVisibleSearchButon(View.GONE);
            bar.setTitle(getText(R.string.most_popular));
            bar.setVisibleBackButton(View.INVISIBLE);
        }

        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
            case Configuration.ORIENTATION_UNDEFINED:
            case Configuration.ORIENTATION_SQUARE:
                width = display.getWidth() / 2;
                downloadTask.execute();
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                search.setVisibility(View.GONE);
                bar.setVisibleSearchButon(View.VISIBLE);
                width = display.getHeight() / 2;
                downloadTask.execute();
                break;
            default:
                break;
        }

        if (determineScreenSize() == Configuration.SCREENLAYOUT_SIZE_UNDEFINED) {
            finish();
        }



    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.category);
        this.orientation = newConfig.orientation;

        ll_base = (LinearLayout) findViewById(R.id.ll_base);
        setView();
        rl_category.setVisibility(View.INVISIBLE);
        if (listCategoriesIsLoaded) {
            searchText.init(TAG, etSearch, listEntries);
            etSearch.addTextChangedListener(searchText);
            updateData(listEntries);
        }

        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
            case Configuration.ORIENTATION_UNDEFINED:
            case Configuration.ORIENTATION_SQUARE:
                search.setVisibility(View.VISIBLE);
                if (bar != null) {
                    bar.setVisibleSearchButon(View.GONE);
                    bar.setVisibleBackButton(View.INVISIBLE);
                    bar.setTitle(getText(R.string.most_popular));
                }
                width = display.getWidth() / 2;
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                search.setVisibility(View.GONE);
                if (bar != null) {
                    bar.setVisibleSearchButon(View.VISIBLE);
                    bar.setVisibleBackButton(View.INVISIBLE);
                    bar.setTitle(getText(R.string.most_popular));
                }
                width = display.getHeight() / 2;
                
                break;
            default:
                break;
        }

        Log.w(TAG, "create orien3: " + getScreenOrientation() + " " + orientation);
    }

    private void setView() {
        rl_category = (RelativeLayout) findViewById(R.id.rl_category);
        etSearch = (EditText) findViewById(R.id.et_search);
        bar = new ActionBar(this, TAG);
        search = findViewById(R.id.search);
        pb_loading = (ProgressBar) findViewById(R.id.pb_loading);
        ll_base = (LinearLayout) findViewById(R.id.ll_base);
        itemTopRight = (View) findViewById(R.id.right_top_item);
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
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    //Hide the keyboard on the screen of a finger        	
                    // imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_MENU:
                getActivityMediator().showMain();
                finish();
                break;
            case KeyEvent.KEYCODE_BACK:
                Log.w(TAG, "Click on Back button");
                try {
                    finalize();
                } catch (Throwable ex) {
                    Logger.getLogger(MostPopular.class.getName()).log(Level.SEVERE, null, ex);
                }
                finish();
                break;
            default:
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_bar_menu:
                ll_base.removeAllViewsInLayout();
                getActivityMediator().showMain();
                try {
                    finalize();
                } catch (Throwable ex) {
                    Logger.getLogger(MostPopular.class.getName()).log(Level.SEVERE, null, ex);
                }
                finish();
                break;
            case R.id.iv_thumbnail:
                getActivityMediator().showInfo(mostPlaysEntry.id, getString(R.string.most_popular), mostPlaysEntry.partnerId);
                break;
            case R.id.iv_bar_search:
                if (search.getVisibility() == View.GONE) {
                    search.setVisibility(View.VISIBLE);
                } else {
                    search.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }

    private class DownloadListCatigoriesTask extends AsyncTask<Void, States, List<KalturaMediaEntry>> {

        private String message;

        @Override
        protected List<KalturaMediaEntry> doInBackground(Void... params) {
            // Test for connection
            try {
                if (Utils.checkInternetConnection(getApplicationContext())) {
                    /**
                     * Getting list of all entries category
                     */
                    publishProgress(States.LOADING_DATA);
                    /**
                     * Getting list of all entries category
                     */
                    KalturaMediaEntryFilter filter = new KalturaMediaEntryFilter();
                    filter.mediaTypeEqual = KalturaMediaType.VIDEO;
                    listEntries = Media.listAllEntriesByIdCategories(TAG, filter, 1, Media.ENTRIES_MAX_COUNT);
                }
                listCategoriesIsLoaded = true;
                Log.w(TAG, "thread is end");
            } catch (Exception e) {
                e.printStackTrace();
                message = e.getMessage();
                Log.w(TAG, message);
                publishProgress(States.NO_CONNECTION);
            }
            return listEntries;
        }

        @Override
        protected void onPostExecute(List<KalturaMediaEntry> listCategory) {
            progressDialog.hide();
            if (listEntries.size() != 0) {
                searchText.init(TAG, etSearch, listEntries);
                etSearch.addTextChangedListener(searchText);
                updateData(listEntries);
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

    private void ImageLoader() {
        Log.w(TAG, "Start image loader");
        float scale = (float) display.getWidth() / (float) display.getHeight();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).imageScaleType(ImageScaleType.IN_SAMPLE_INT).bitmapConfig(Bitmap.Config.RGB_565).build();

        // This configuration tuning is custom. You can tune every option, you may tune some of them, 
        // or you can create default configuration by
        //  ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(activity)
                .threadPoolSize(3).threadPriority(Thread.NORM_PRIORITY - 2).memoryCacheSize(150000000) // 150 Mb
                .discCacheSize(50000000) // 50 Mb
                .denyCacheImageMultipleSizesInMemory().defaultDisplayImageOptions(options)
                .enableLogging().build();
        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config);
        imageLoader.init(config);

        final List<String> url = new ArrayList<String>();
        view = new ArrayList<ImageView>();
        progressBar = new ArrayList<ProgressBar>();
        url.add(mostPlaysEntry.thumbnailUrl + "/width/250/height/250");
        ImageView thumb = ((ImageView) findViewById(R.id.iv_thumbnail));
        thumb.getLayoutParams().width = display.getWidth();
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            thumb.getLayoutParams().height = (int) (display.getWidth() * scale);
        } else {
            thumb.getLayoutParams().height = display.getHeight() - 200;
        }
        thumb.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.add(thumb);
        progressBar.add(pb_loading);

        if (orientation == Configuration.ORIENTATION_LANDSCAPE && rightTopEntry != null) {
            url.add(rightTopEntry.thumbnailUrl + "/width/250/height/250");
            Log.w(TAG, "set last bitmap");
            thumb = ((ImageView) itemTopRight.findViewById(R.id.iv_thumbnail));
            thumb.getLayoutParams().width = display.getWidth();
            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                thumb.getLayoutParams().height = (int) (display.getWidth() * scale);
            } else {
                thumb.getLayoutParams().height = display.getHeight() - 200;
            }
            thumb.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.add(thumb);
            progressBar.add(pb_loading);

            thumb.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Log.w(TAG, "click on thumb");
                    getActivityMediator().showInfo(rightTopEntry.id, "Most Popular", rightTopEntry.partnerId);
                }
            });
        }

        for (KalturaMediaEntry entry : copyEntries) {
            url.add(entry.thumbnailUrl + "/width/250/height/250");
        }
        count = 0;
        for (String string : url) {
            Log.w(TAG, "url: " + count++ + " " + string);
        }

        int state = 0;
        count = 0;
        for (int j = 0; j < copyEntries.size(); j++) {
        	ItemGrid ig = null;
            switch (orientation) {
                case Configuration.ORIENTATION_PORTRAIT:
                    switch (state) {
                        case 0:
                            //left
                            Log.w(TAG, "xl: " + count);
                            ig = contentPort.get(count).getLeftItemGrid();
                            state = 2;
                            break;
                        case 2:
                            //right
                            Log.w(TAG, "xr: " + count);
                            ig = contentPort.get(count).getRightItemGrid();
                            count++;
                            state = 0;
                            break;
                    }
                    break;
                case Configuration.ORIENTATION_LANDSCAPE:
                    switch (state) {
                        case 0:
                            //left
                            Log.w(TAG, "xl: " + count);
                            ig = contentLand.get(count).getLeftItemGrid();
                            state = 1;
                            break;
                        case 1:
                            //center
                            Log.w(TAG, "xc: " + count);
                            ig = contentLand.get(count).getCenterItemGrid();
                            state = 2;
                            break;
                        case 2:
                            //right
                            Log.w(TAG, "xr: " + count);
                            ig = contentLand.get(count).getRightItemGrid();
                            count++;
                            state = 0;
                            break;
                    }
                    break;
                default:
                    break;
            }
            if ( ig != null ) {
            	view.add(ig.getThumb());
                progressBar.add(ig.getProgressBar());
            }
        }

        count = 0;
        Log.w(TAG, "size: " + progressBar.size());
        k = 0;
        for (String string : url) {
        	if (string != null) {
                imageLoader.displayImage(string, view.get(count), new ImageLoadingListener() {

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
    				public void onLoadingComplete(String imageUri, View view,
    						Bitmap loadedImage) {
    					// do nothing
                        if (k < progressBar.size()) {
                            progressBar.get(k++).setVisibility(View.GONE);
                        }
                        Log.w(TAG, "onLoadingComplete: " + k);

                        Log.w(TAG, "k<>size: " + k + "--" + url.size());
                        if (k >= url.size()) {
                            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
                        }
    					
    				}

    				@Override
    				public void onLoadingCancelled(String imageUri, View view) {
    					// TODO Auto-generated method stub
    					
    				}
                });
                count++;
        	}

            
        }

    }

    private void updateData(List<KalturaMediaEntry> listEntries) {
        copyEntries = new ArrayList<KalturaMediaEntry>();
        copyEntries.addAll(listEntries);

        if (copyEntries.size() > 0) {
            sizeListentry = copyEntries.size();
            Collections.sort(copyEntries, new Sort<KalturaMediaEntry>("plays", "reverse"));
            mostPlaysEntry = copyEntries.get(0);
            copyEntries.remove(mostPlaysEntry);
            addContentLastEntry();
        } else {
            rl_category.setVisibility(View.GONE);
        }

        switch (orientation) {
            case Configuration.ORIENTATION_PORTRAIT:
                Log.w(TAG, "start in port");
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                createGridForPort();
                if (listEntries.size() > 0) {
                    ImageLoader();
                }
                break;
            case Configuration.ORIENTATION_LANDSCAPE:
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                if (listEntries.size() > 1) {
                    itemTopRight.setVisibility(View.VISIBLE);
                    rightTopEntry = copyEntries.get(0);
                    ((TextView) itemTopRight.findViewById(R.id.tv_name)).setText(rightTopEntry.name);
                    ((TextView) itemTopRight.findViewById(R.id.tv_episode)).setText(Utils.durationInSecondsToString(rightTopEntry.duration));
                    copyEntries.remove(0);
                } else {
                    itemTopRight.setVisibility(View.GONE);
                }
                createGridForLand();
                Log.w(TAG, "start in land");
                if (listEntries.size() > 0) {
                    ImageLoader();
                }
                break;
            default:
                break;
        }
    }
    
    private void processItem( ItemGrid ig, int index, int width, int height ) {   	 
    	ig.getName().setText(copyEntries.get(index).name);
    	ig.getEpisode().setText(Utils.durationInSecondsToString(copyEntries.get(index).duration));
    	ig.getThumb().getLayoutParams().width = width;
    	ig.getThumb().getLayoutParams().height = height;
    	ig.getThumb().setScaleType(ImageView.ScaleType.CENTER_CROP);
    	ig.setKey(copyEntries.get(index));
    }
    
    private void processNoElement( ItemGrid ig ) {
        ig.getThumb().setVisibility(View.INVISIBLE);
        ig.getProgressBar().setVisibility(View.INVISIBLE);
        ig.getName().setVisibility(View.INVISIBLE);
        ig.getEpisode().setVisibility(View.INVISIBLE);
    }
    
    private void addThumbListener( ItemGrid ig, final int index ) {
    	ig.getThumb().setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (index < copyEntries.size()) {
                	KalturaBaseEntry ent = copyEntries.get(index);
                    getActivityMediator().showInfo(ent.id, "Most Popular", ent.partnerId);
                    Log.w(TAG, "click first" + index);
                }
            }
        });
    }

    private void createGridForPort() {
        Log.w(TAG, "grid for port");
        ll_base.removeAllViewsInLayout();
        if (copyEntries.size() > 0) {
            contentPort = new ArrayList<GridForPort>();
            Log.w(TAG, "size: " + copyEntries.size());
            int countConent = copyEntries.size() / 2;
            Log.w(TAG, "countConent: " + countConent);
            int rest = copyEntries.size() % 2;
            Log.w(TAG, "rest: " + rest);

            if (rest != 0) {
                countConent = countConent + 1;
                Log.w(TAG, "countConent: " + countConent);
            }

            //Create countContent contents
            int offset = 0;
            int size = copyEntries.size();

            if (size > 2) {
                Log.w(TAG, "1 offset: " + offset);
                contentPort.add(new GridForPort(TAG, this, offset));
                size = size - 2;
                for (int i = 0; i < countConent - 1; i++) {
                    offset = offset + 2;
                    size = size - 2;
                    Log.w(TAG, "3 offset: " + offset);
                    contentPort.add(new GridForPort(TAG, this, offset));
                }
            } else {
                Log.w(TAG, "2 offset: " + 0);
                contentPort.add(new GridForPort(TAG, this, 0));
            }

            float scale = (float) display.getWidth() / (float) display.getHeight();
            //set params
            for (final GridForPort templateContent : contentPort) {
                ll_base.addView(templateContent.getRowGrid());

                if (templateContent.getOffset() + 0 < copyEntries.size()) {
                	processItem( templateContent.getLeftItemGrid(), templateContent.getOffset() + 0, display.getWidth() / 2, (int)(display.getWidth() / 2 * scale));
                } else {
                    Log.w(TAG, "no right element");
                }
                if (templateContent.getOffset() + 1 < copyEntries.size()) {
                	processItem( templateContent.getRightItemGrid(), templateContent.getOffset() + 1, this.width, (int)(display.getWidth() / 2 * scale));
                } else {
                    Log.w(TAG, "no right element");
                    processNoElement( templateContent.getRightItemGrid() );
                }

                addThumbListener( templateContent.getLeftItemGrid(), templateContent.getOffset() );
                addThumbListener( templateContent.getRightItemGrid(), templateContent.getOffset() + 1);
            }
        } else {
            Log.w(TAG, "list size is 0");
        }
    }

    private void createGridForLand() {
        Log.w(TAG, "grid for land");
        ll_base.removeAllViewsInLayout();
        if (copyEntries.size() > 0) {
            contentLand = new ArrayList<GridForLand>();
            Log.w(TAG, "size: " + copyEntries.size());
            int countConent = copyEntries.size() / 3;
            Log.w(TAG, "countConent: " + countConent);
            int rest = copyEntries.size() % 3;
            Log.w(TAG, "rest: " + rest);

            if (rest != 0) {
                countConent = countConent + 1;
                Log.w(TAG, "countConent: " + countConent);
            }

            //Create countContent contents
            int offset = 0;
            int size = copyEntries.size();

            if (size > 3) {
                Log.w(TAG, "1 offset: " + offset);
                contentLand.add(new GridForLand(TAG, this, offset));
                size = size - 3;
                for (int i = 0; i < countConent - 1; i++) {
                    offset = offset + 3;
                    size = size - 3;
                    Log.w(TAG, "3 offset: " + offset);
                    contentLand.add(new GridForLand(TAG, this, offset));
                }
            } else {
                Log.w(TAG, "2 offset: " + 0);
                contentLand.add(new GridForLand(TAG, this, 0));
            }

            float scale = (float) display.getHeight() / (float) display.getWidth();
            //set params
            for (final GridForLand templateContent : contentLand) {
                ll_base.addView(templateContent.getRowGrid());

                if (templateContent.getOffset() + 0 < copyEntries.size()) {
                	processItem(templateContent.getLeftItemGrid(),templateContent.getOffset() + 0, display.getWidth() / 3, (int)(display.getWidth() / 3 * scale));
                    
                } else {
                    Log.w(TAG, "no left element");
                }

                if (templateContent.getOffset() + 1 < copyEntries.size()) {
                	processItem(templateContent.getCenterItemGrid(), templateContent.getOffset() + 1, display.getWidth() / 3,  (int) (display.getWidth() / 3 * scale));
                } else {
                    Log.w(TAG, "no center element");
                    processNoElement( templateContent.getCenterItemGrid() );
                }

                if (templateContent.getOffset() + 2 < copyEntries.size()) {
                	processItem(templateContent.getRightItemGrid(), templateContent.getOffset() + 2, display.getWidth() / 3 , (int) (display.getWidth() / 3 * scale));

                } else {
                    Log.w(TAG, "no right element");
                    processNoElement( templateContent.getRightItemGrid() );
                }
                
                addThumbListener( templateContent.getLeftItemGrid(), templateContent.getOffset() );
                addThumbListener( templateContent.getCenterItemGrid(), templateContent.getOffset() );
                addThumbListener( templateContent.getRightItemGrid(), templateContent.getOffset() + 1 );
             
            }
        } else {
            Log.w(TAG, "list size is 0");
        }
    }

    private void addContentLastEntry() {

        Log.w(TAG, "sizeListentry: " + sizeListentry);
        if (sizeListentry != 0) {
            rl_category.setVisibility(View.VISIBLE);
            try {
                ((TextView) findViewById(R.id.tv_name)).setText(mostPlaysEntry.name);
                ((TextView) findViewById(R.id.tv_episode)).setText("");
                ((TextView) findViewById(R.id.tv_duration)).setText(Utils.durationInSecondsToString(mostPlaysEntry.duration));
            } catch (Exception e) {
                e.printStackTrace();
                Log.w(TAG, "err: " + e.getMessage());
            }
        }
    }

    @Override
    public void update(Observable paramObservable, Object paramObject) {
        updateData((List<KalturaMediaEntry>) paramObject);
    }
}
