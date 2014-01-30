package com.kaltura.activity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaltura.bar.ActionBar;
import com.kaltura.client.KalturaApiException;
import com.kaltura.client.types.KalturaMediaEntry;
import com.kaltura.enums.States;
import com.kaltura.mediatorActivity.TemplateActivity;
import com.kaltura.playersdk.PlayerViewController;
import com.kaltura.playersdk.events.KPlayerEvalListener;
import com.kaltura.playersdk.events.KPlayerEventListener;
import com.kaltura.services.Media;
import com.kaltura.sharing.Sharing;
import com.kaltura.utils.Utils;

//import com.nostra13.universalimageloader.core.ImageLoadingListener;

public class Info extends TemplateActivity {

    private String entryId;
    private KalturaMediaEntry entry;
    private LinearLayout ll_info;
    private DownloadEntryTask downloadTask;
    private String nameCategory;
    private Sharing sharing;
    private String partnerId;
    private PlayerHelper mPlayerHelper;
    private boolean mIsLargeScreen = false;

    public Info() {
        downloadTask = new DownloadEntryTask();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {   	
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mIsLargeScreen = ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) >=
                Configuration.SCREENLAYOUT_SIZE_LARGE);
             
        if ( ! mIsLargeScreen ){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
     
        OrientationEventListener orientationListener = new OrientationEventListener( this, SensorManager.SENSOR_DELAY_NORMAL) {
            int lastPos = 0;
            @Override
            public void onOrientationChanged(int i) {
            	int diff = Math.abs(lastPos - i);
                if ( diff >= 45 && diff <= 270 ) {
                    int requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
                    if ( isInRange(i, 90) ) {
                    	if (Build.VERSION.SDK_INT >= 9)
                    		requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
                    	else
                    		requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    } else if ( isInRange(i, 180) ) {
                    	if (Build.VERSION.SDK_INT >= 9)
                    		requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;	
                    } else if ( isInRange(i, 270) ) {
                        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
                    }
                    lastPos = i;
                    setRequestedOrientation( requestedOrientation );
                }
            }
            /**
             *
             * @param current
             * @param pos
             * @return true if current is in the range (max difference of 45) of pos value
             */
            private boolean isInRange( int current, int pos ) {
                return ( Math.abs( pos - current ) <= 45 );
            }
        };

        if ( !mIsLargeScreen && orientationListener.canDetectOrientation() )
            orientationListener.enable(); 
        
        init();
        setContentView(R.layout.info);
        setView();
        extractBundle();

        if (bar != null) {
            bar.setTitle(getText(R.string.info));
            bar.setVisibleBackButton(View.VISIBLE);
            bar.setVisibleNameCategory(View.VISIBLE);
            bar.setTextNameCategory(nameCategory);
        }
        ll_info.setVisibility(View.INVISIBLE);
        sharing = new Sharing(this);
        downloadTask.execute();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if ( mPlayerHelper != null) {
        	mPlayerHelper.notifyConfigurationChanged( newConfig );
        }
    }

    private void setView() {
        bar = new ActionBar(this, TAG);
        ll_info = (LinearLayout) findViewById(R.id.ll_info);
        setFont();

    }

    @Override
    public void onStart() {
        super.onStart();
        sharing.addListener();
    }

    @Override
    protected void onStop() {
        super.onStop();
        sharing.removeListener();
    }

    private void setFont() {
        ((TextView) findViewById(R.id.tv_name)).setTypeface(typeFont);
        ((TextView) findViewById(R.id.tv_episode)).setTypeface(typeFont);
        ((TextView) findViewById(R.id.tv_duration)).setTypeface(typeFont);
        ((TextView) findViewById(R.id.tv_description)).setTypeface(typeFont);
    }

    private void extractBundle() {
        try {
            Bundle extras = getIntent().getExtras();
            entryId = extras.getString("entryId");
            partnerId = extras.getString("partnerId");
            nameCategory = extras.getString("nameCategory");

        } catch (Exception e) {
            e.printStackTrace();
            entryId = "";
            partnerId = "";
            nameCategory = "";
        }
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
                //Hide the keyboard on the screen of a finger        	
                //imm.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), 0);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_button_facebook:
                Log.w(TAG, "test facebook button");
                sharing.sendToFacebook(entry);
                break;
            case R.id.iv_button_twitter:
                Log.w(TAG, "test twitter button");
                sharing.sendToTwitter(entry);
                break;
            case R.id.iv_button_mail:
                Log.w(TAG, "test mail button");
                sharing.sendToMail(entry);
                break;
            case R.id.iv_bar_menu:
                getActivityMediator().showMain();
                break;
            case R.id.rl_button_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void updateData() {
    	((TextView) findViewById(R.id.tv_name)).setText(entry.name);
        ((TextView) findViewById(R.id.tv_episode)).setText("");
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        ((TextView) findViewById(R.id.tv_duration)).setText(sdf.format(new Date(entry.duration * 1000)));
        ((TextView) findViewById(R.id.tv_description)).setText(entry.description);
        ll_info.setVisibility(View.VISIBLE); 
        PlayerViewController playerView = (PlayerViewController) findViewById( R.id.custom_player );
        mPlayerHelper = new PlayerHelper( playerView, this, mIsLargeScreen );
        Point size = new Point();
        //calculate player size
    	getWindowManager().getDefaultDisplay().getSize(size);
    	size.y = size.y / 2;
        //list of items to hide when opening fullscreen
        List<View> comps = new ArrayList<View>();
        comps.add(findViewById(R.id.ll_data));
        comps.add(findViewById(R.id.bar));
        mPlayerHelper.showPlayer(entryId, partnerId, size.x, size.y, comps);      
         
    }

    private class DownloadEntryTask extends AsyncTask<Void, States, Void> {

        private String message;

        @Override
        protected Void doInBackground(Void... params) {
            // Test for connection
            Log.w(TAG, "Thread is start");
            try {
                if (Utils.checkInternetConnection(getApplicationContext())) {
                    /**
                     * Getting information about the entry
                     */
                    publishProgress(States.LOADING_DATA);
                    try {
                        entry = Media.getEntrybyId(TAG, entryId);
                    } catch (KalturaApiException e) {
                    	Utils.handleException(TAG, e);
                        entry = new KalturaMediaEntry();
                    }
                }
                Log.w(TAG, "Thread is end");
            } catch (Exception e) {
            	message = Utils.handleException(TAG, e);
                publishProgress(States.NO_CONNECTION);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void param) {
            progressDialog.hide();
            updateData();
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
}
