package com.kaltura.activity;

import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.kaltura.playersdk.PlayerViewController;
import com.kaltura.playersdk.events.OnToggleFullScreenListener;

/**
 * This Helper class will perform kaltura-player related actions
 * @author michalradwantzor
 *
 */
public class PlayerHelper implements OnToggleFullScreenListener {
	
	private PlayerViewController mPlayerView;
	private Boolean mInFS = false;
	private Activity mActivity;
	private int mWidth;
	private int mHeight;
	private List<View> mComponents;
	private Boolean mIsLargeScreen = false;
	private LinearLayout mLayout;
	
	/**
	 * Creates a new PlayerHelper
	 * @param player instance to control
	 * @param activity that holds the player
	 * @param isLargeScreen boolean if false player will open in fullscreen upon screen rotation
	 */
	public PlayerHelper( PlayerViewController player, Activity activity, Boolean isLargeScreen ) {
		mPlayerView = player;
		mActivity = activity;
		mIsLargeScreen = isLargeScreen;
		mPlayerView.setOnFullScreenListener(this);
	}
	
	/**
	 * Build Iframe URL and display the player
	 * @param entryId
	 * @param partnerId
	 * @param width
	 * @param height
	 * @param components to hide when opening fullscreen
	 */
	public void showPlayer(String entryId, String partnerId, int width, int height, List<View> components) {
		mComponents = components;
		mWidth = width;
        mHeight = height;
        mPlayerView.setPlayerViewDimensions(width, height);
        mPlayerView.addComponents(partnerId, entryId, mActivity);
	}
	
	public void setLayout(LinearLayout layout) {
		mLayout = layout;
	}
	
	public void notifyConfigurationChanged(Configuration newConfig) {
		if ( mIsLargeScreen ) {
    		if ( mInFS ) {
    			Point size = new Point();
    	    	mActivity.getWindowManager().getDefaultDisplay().getSize(size);
    	    	mPlayerView.setPlayerViewDimensions( size.x, size.y );
    		}
    	} else {
        	if ( newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ) {
        		openFullscreen();
        	} else {
        		closeFullscreen();
        	}
    	}
	}

	@Override
	public void onToggleFullScreen() {
        if ( !mInFS) {
        	if ( !mIsLargeScreen )
        		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            openFullscreen();
        } else {
        	if ( !mIsLargeScreen )
        		mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
           closeFullscreen();
        }
		
	}
	
	 private void openFullscreen() {
	        mInFS = true;
	        if ( mComponents!=null ){
	        	for(Iterator<View> i = mComponents.iterator(); i.hasNext(); ) {
		            i.next().setVisibility(View.GONE);
		        }
	        }
	        
	        Point size = new Point();
	    	mActivity.getWindowManager().getDefaultDisplay().getSize(size);
	    	if ( mLayout != null ) {
	    		LayoutParams params = (LayoutParams) mLayout.getLayoutParams();
	    		params.width = LayoutParams.MATCH_PARENT;
	    		params.height = LayoutParams.MATCH_PARENT;
	    		mLayout.invalidate();
	    	}
	    	mPlayerView.setPlayerViewDimensions( size.x, size.y );
	     
	 }	 
	
    private void closeFullscreen() {
        mInFS = false;
        if ( mComponents!=null ){
        	for(Iterator<View> i = mComponents.iterator(); i.hasNext(); ) {
	            i.next().setVisibility(View.VISIBLE);
	        }
        }
        mPlayerView.setPlayerViewDimensions( mWidth, mHeight);
    }

}
