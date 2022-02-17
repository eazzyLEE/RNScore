package com.rnscore;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Promise;
import java.util.Map;
import java.util.HashMap;
import com.scs.scssdk.SCSSDK;

import android.Manifest;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class SCSNative extends ReactContextBaseJavaModule {
    SCSNative(ReactApplicationContext context) {
        super(context);
    }

    @Override
    public String getName() {
        return "SCSNative";
    }

    private Integer STORAGE_PERMISSION_CODE = 101;

    // Function to check and request permission.
    private void checkPermission(String[] permission, Integer requestCode) {
        ActivityCompat.requestPermissions(this.getCurrentActivity(), permission, requestCode);
    }

    @ReactMethod
    public void score(String apiKey, String email, final Promise promise) {
        Log.d("SCSNative", "Score called");

        String key = apiKey;
        String em = email;

        //create array of necessary permissions strings
        String[] arrPerms = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.USE_FINGERPRINT,
                Manifest.permission.QUERY_ALL_PACKAGES};

        //check that permissions have been granted; request them if not.
        checkPermission(arrPerms,STORAGE_PERMISSION_CODE);

        //create and initialize the SCSSDK object
        SCSSDK scs;
        scs = new SCSSDK(key, "");
        //call the score method
        Object scoreRes = scs.score(this.getReactApplicationContext(), em, "", "", false );
        //return the results of the score method (a JSON string)
        promise.resolve(scoreRes.toString());
    }
}
