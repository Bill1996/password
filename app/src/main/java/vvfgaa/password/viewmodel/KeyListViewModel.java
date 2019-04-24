package vvfgaa.password.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import java.util.List;

import vvfgaa.password.BasicApp;
import vvfgaa.password.DataRepository;
import vvfgaa.password.db.KeyEntity;


public class KeyListViewModel extends AndroidViewModel {

    private final DataRepository mRepository;

    private final MediatorLiveData<List<KeyEntity>> mObservableKeys;

    public KeyListViewModel(Application application) {
        super(application);

        mObservableKeys = new MediatorLiveData<>();
        mObservableKeys.setValue(null);

        mRepository = ((BasicApp) application).getRepository();
        LiveData<List<KeyEntity>> Keys = mRepository.getKeys();

        mObservableKeys.addSource(Keys, mObservableKeys::setValue);
    }

    public LiveData<List<KeyEntity>> getKeys() {
        return mObservableKeys;
    }

}
