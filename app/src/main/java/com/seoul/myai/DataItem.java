package com.seoul.myai;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import kotlin.reflect.KProperty;

@Entity(tableName = "table")
public class DataItem {


    @PrimaryKey(autoGenerate = true)
    private int id;


    private int src;
    private String name;




    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return  id;
    }

    public int getSrc() {
        return src;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
