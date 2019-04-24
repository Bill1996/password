package vvfgaa.password.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import vvfgaa.password.R;
import vvfgaa.password.databinding.ListFragmentBinding;
import vvfgaa.password.db.KeyEntity;
import vvfgaa.password.model.Key;
import vvfgaa.password.viewmodel.KeyListViewModel;

public class KeyListFragment extends Fragment {

    public static final String TAG = "KeyListFragment";

    private KeyAdapter mKeyAdapter;

    private ListFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.list_fragment, container, false);

        mKeyAdapter = new KeyAdapter(mKeyClickCallback);
        mBinding.keysList.setAdapter(mKeyAdapter);

        return mBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final KeyListViewModel viewModel =
                ViewModelProviders.of(this).get(KeyListViewModel.class);



        subscribeUi(viewModel.getKeys());
    }

    private void subscribeUi(LiveData<List<KeyEntity>> liveData) {
        liveData.observe(this, new Observer<List<KeyEntity>>() {
            @Override
            public void onChanged(@Nullable List<KeyEntity> myKeys) {
                if (myKeys != null) {
                    mBinding.setIsLoading(false);
                    mKeyAdapter.setKeyList(myKeys);
                } else {
                    mBinding.setIsLoading(true);
                }
                mBinding.executePendingBindings();
            }
        });
    }

    private final KeyClickCallback mKeyClickCallback = new KeyClickCallback() {
        @Override
        public void onClick(Key Key) {

            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                //todo
            }
        }
    };
}
