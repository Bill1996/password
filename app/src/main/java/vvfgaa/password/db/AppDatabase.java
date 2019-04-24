package vvfgaa.password.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import vvfgaa.password.AppExecutors;

@Database(entities = {KeyEntity.class}, version = 1, exportSchema =false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    private static final String DATABASE_NAME = "key.db";

    public abstract KeyDao KeyDao();

    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(()->{
                            try {
                                Thread.sleep(4000);
                            } catch (InterruptedException ignored) {
                            }
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);
                            KeyEntity key1=new KeyEntity();
                            key1.setTitle("淘宝");
                            key1.setUser("vvfgaa");
                            database.KeyDao().insert(key1);
                            KeyEntity key2=new KeyEntity();
                            key2.setTitle("QQ");
                            key2.setUser("麻花腾");
                            database.KeyDao().insert(key2);
                            database.setDatabaseCreated();
                        });
                    }
                })
                .build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
}
