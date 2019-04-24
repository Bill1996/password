package vvfgaa.password.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import vvfgaa.password.R;
import vvfgaa.password.databinding.KeyItemBinding;
import vvfgaa.password.model.Key;

public class KeyAdapter extends RecyclerView.Adapter<KeyAdapter.KeyViewHolder> {

    List<? extends Key> mKeyList;

    @Nullable
    private final KeyClickCallback mKeyClickCallback;

    public KeyAdapter(@Nullable KeyClickCallback clickCallback) {
        mKeyClickCallback = clickCallback;
        setHasStableIds(true);
    }

    public void setKeyList(final List<? extends Key> KeyList) {
        if (mKeyList == null) {
            mKeyList = KeyList;
            notifyItemRangeInserted(0, KeyList.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mKeyList.size();
                }

                @Override
                public int getNewListSize() {
                    return KeyList.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return mKeyList.get(oldItemPosition).getId() ==
                            KeyList.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Key newKey = KeyList.get(newItemPosition);
                    Key oldKey = mKeyList.get(oldItemPosition);
                    return newKey.getId() == oldKey.getId()
                            && Objects.equals(newKey.getTitle(), oldKey.getTitle())
                            && Objects.equals(newKey.getUser(), oldKey.getUser());
                }
            });
            mKeyList = KeyList;
            result.dispatchUpdatesTo(this);
        }
    }

    @Override
    public KeyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        KeyItemBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.key_item,
                        parent, false);
        binding.setCallback(mKeyClickCallback);
        return new KeyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(KeyViewHolder holder, int position) {
        holder.binding.setKey(mKeyList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mKeyList == null ? 0 : mKeyList.size();
    }

    @Override
    public long getItemId(int position) {
        return mKeyList.get(position).getId();
    }

    static class KeyViewHolder extends RecyclerView.ViewHolder {

        final KeyItemBinding binding;

        public KeyViewHolder(KeyItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
