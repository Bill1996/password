package vvfgaa.password;

import android.app.Application;

public class App extends Application {

    static {
        System.loadLibrary("genuine");
    }

    private boolean mFake;

    @Override
    public void onCreate() {
        super.onCreate();
        mFake = BuildConfig.VERSION_CODE != Genuine.version();
    }

    public final boolean isFake() {
        return mFake;
    }

}