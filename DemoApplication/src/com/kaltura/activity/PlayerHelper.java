package com.kaltura.activity;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import com.kaltura.playersdk.CustomPlayerView;
import com.kaltura.playersdk.events.OnToggleFullScreenListener;
import com.kaltura.services.AdminUser;

/**
 * This Helper class will perform kaltura-player related actions
 * @author michalradwantzor
 *
 */
public class PlayerHelper implements OnToggleFullScreenListener {
	
	private CustomPlayerView mPlayerView;
	private Boolean mInFS = false;
	private Activity mActivity;
	private int mWidth;
	private int mHeight;
	private List<View> mComponents;
	
	/**
	 * Creates a new PlayerHelper
	 * @param player instance to control
	 * @param activity that holds the player
	 */
	public PlayerHelper( CustomPlayerView player, Activity activity ) {
		mPlayerView = player;
		mActivity = activity;
		mPlayerView.setOnFullScreenListener(this);
	}
	
	/**
	 * Build Iframe URL and display the player
	 * @param activity
	 * @param entryId
	 * @param partnerId
	 * @param uiconfId
	 * @param width
	 * @param height
	 * @param components to hide when opening fullscreen
	 */
	public void showPlayer( String entryId, String partnerId, int width, int height, List<View> components) {
		mComponents = components;
        String iframeUrl = AdminUser.cdnHost + AdminUser.html5Url + "?wid=_" + partnerId + "&uiconf_id=" + AdminUser.uiconfId + "&entry_id=" + entryId;
        LayoutParams params = (LayoutParams) mPlayerView.getLayoutParams();
        mWidth = params.width;
        mHeight = params.height;
        //override params with given arguments
        if ( width != 0 ) {
        	mWidth = width;
        }
        if ( height != 0 ) {
        	mHeight = height;
        }  
         mPlayerView.addComponents(iframeUrl, mWidth, mHeight, mActivity);
         mPlayerView.setBackgroundColor(Color.BLACK);
	}

	@Override
	public void onToggleFullScreen() {
        if ( !mInFS) {
	        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            openFullscreen();
        } else {
	        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
           closeFullscreen();
        }
		
	}
	
	 public void openFullscreen() {
	        mInFS = true;
	        if ( mComponents!=null ){
	        	for(Iterator<View> i = mComponents.iterator(); i.hasNext(); ) {
		            i.next().setVisibility(View.GONE);
		        }
	        }
	        
	        Point size = new Point();
	    	mActivity.getWindowManager().getDefaultDisplay().getSize(size);	
	    	updateVideoDimensions( size.x, size.y );
	     
	 }
	 
	    private void updateVideoDimensions( int width, int height ) {
	    	ViewGroup.LayoutParams lp = mPlayerView.getLayoutParams();
	        lp.width = width;
	        lp.height = height;
	      
	        mPlayerView.setLayoutParams( lp );
	        
	        mPlayerView.setPlayerViewDimensions(width, height);
	        mPlayerView.invalidate();
	    }
	
	    public void closeFullscreen() {
	        mInFS = false;
	        if ( mComponents!=null ){
	        	for(Iterator<View> i = mComponents.iterator(); i.hasNext(); ) {
		            i.next().setVisibility(View.VISIBLE);
		        }
	        }
	        updateVideoDimensions( mWidth, mHeight);
	    }

}
