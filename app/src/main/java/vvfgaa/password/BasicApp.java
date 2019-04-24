package vvfgaa.password;

import android.app.Application;

import vvfgaa.password.db.AppDatabase;

public class BasicApp extends Application {

    static {
        System.loadLibrary("genuine");
    }

    private boolean mFake;
    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();
        mFake = BuildConfig.VERSION_CODE != Genuine.version();
        mAppExecutors = new AppExecutors();
    }

    public final boolean isFake() {
        return mFake;
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }

}