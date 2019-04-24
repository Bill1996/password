package vvfgaa.password;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import vvfgaa.password.db.AppDatabase;
import vvfgaa.password.db.KeyEntity;

public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<KeyEntity>> mObservableKeys;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservableKeys = new MediatorLiveData<>();

        mObservableKeys.addSource(mDatabase.KeyDao().loadAllKeys(),
                keyEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservableKeys.postValue(keyEntities);
                    }
                });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<KeyEntity>> getKeys() {
        return mObservableKeys;
    }

    public LiveData<KeyEntity> loadKey(final int keyId) {
        return mDatabase.KeyDao().loadKey(keyId);
    }
}
