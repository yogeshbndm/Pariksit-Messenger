package com.pariksit.pariksitmessenger;

import android.app.Activity;

import java.io.File;
import java.security.SecureRandom;

/**
 * Created by Pariksit on 11/26/2018.
 */

public class Utils {
    public static final String PREFS_NAME = "messenger";
    public static final String url = "https://www.pariksit.com/messenger/android/";
    public static final String fetchChatNodes = url+"fetchChatNodes/";
    public static final String chatlisturl = fetchChatNodes+"1.supplyChatNode.php";
    public static final String loginUrl = url+"register-login/0.credentialVerification.php";

    public static File getDirectoryForExams(Activity activity, String ext) {
        File path= new File(activity.getFilesDir(), "PARIKSIT-MESSENGER" + File.separator + "ZIPS" + File.separator + ext);
        return path;
    }

    public static String randomString(int len ){
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public static String getCurrentTimestamp() {
        Long tsLong = System.currentTimeMillis()/1000;
        return tsLong.toString();
    }
}
