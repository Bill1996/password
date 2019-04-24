package vvfgaa.password.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface KeyDao {
    @Insert
    void insert(KeyEntity key);

    @Query("SELECT * FROM `key`")
    LiveData<List<KeyEntity>> loadAllKeys();

    @Query("SELECT * FROM `key` WHERE id = :id")
    LiveData<KeyEntity> loadKey(int id);
}
