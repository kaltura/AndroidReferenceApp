package com.kaltura.services;

//<editor-fold defaultstate="collapsed" desc="comment">
import android.os.Handler;
import android.util.Log;

import com.kaltura.client.KalturaApiException;
import com.kaltura.client.KalturaClient;
import com.kaltura.client.KalturaConfiguration;
import com.kaltura.client.KalturaMultiResponse;
import com.kaltura.client.services.KalturaAdminUserService;
import com.kaltura.client.services.KalturaUiConfService;
import com.kaltura.client.types.KalturaUiConf;
//</editor-fold>

/**
 * Manage details for the administrative user
 *
 */
public class AdminUser {

    private static KalturaClient client;
    private static boolean userIsLogin;
    /**
     * Contains the session if the user has successfully logged
     */
    public static String ks;
    /**
     * 
     * api host
     */
    public static String host;
    
    public static String cdnHost;
    
    public static int uiconfId;
    
    public static String html5Url;

    /**
     *
     */
    public static KalturaClient getClient() {
        return client;
    }

    /**
     */
    public static boolean userIsLogin() {
        return userIsLogin;
    }

    /**
     * Get an admin session using admin email and password (Used for login to
     * the KMC application)
     *
     * @param TAG constant in your class
     * @param email
     * @param password
     *
     * @throws KalturaApiException
     */
    public static void login(final String TAG, final String email, final String password, final LoginTaskListener loginTaskListener) {
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                try {
                    // set a new configuration object
                    KalturaConfiguration config = new KalturaConfiguration();
                    config.setTimeout(10000);
                    config.setEndpoint(host);

                    client = new KalturaClient(config);
                    client.setMultiRequest(true);
                    
                    KalturaAdminUserService userService = new KalturaAdminUserService(client);
                    client.getAdminUserService().login(email, password);
 
                    KalturaUiConfService uiconf = new KalturaUiConfService( client );
                    client.getUiConfService().get( uiconfId );
                    KalturaMultiResponse multi = client.doMultiRequest();

                    // set the kaltura client to use the recieved ks as default for all future operations
                    ks = (String) multi.get(0);
                    Log.w(TAG, ks);
                    client.setSessionId(ks);
                    userIsLogin = true;
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            loginTaskListener.onLoginSuccess();
                        }
                    });
                    
                    KalturaUiConf uiconfObj = (KalturaUiConf) multi.get(1);
                    html5Url = uiconfObj.html5Url.replace("mwEmbedLoader", "mwEmbedFrame");
                    
                } catch (final KalturaApiException e) {
                    e.printStackTrace();
                    Log.w(TAG, "Login error: " + e.getMessage() + " error code: " + e.code);
                    userIsLogin = false;
                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            loginTaskListener.onLoginError(e.getMessage());
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    public interface LoginTaskListener {

        void onLoginSuccess();

        void onLoginError(String errorMessage);
    }
}
