package com.seoul.myai.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.seoul.myai.DataItem;

import java.util.List;

@Dao
public interface DataItemDao {

    @Insert
    void Insert(DataItem dataItem);

    @Query("SELECT * FROM `table` ORDER BY id ASC")
    List<DataItem> getAll();

    @Query("DELETE FROM `table`")
    void deleteAll();



}
