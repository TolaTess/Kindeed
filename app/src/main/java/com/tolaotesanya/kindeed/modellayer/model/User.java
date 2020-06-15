package com.tolaotesanya.kindeed.modellayer.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private String userid;
    @ColumnInfo
    private String email;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String pictureURL;

    public User(@NonNull String userid, String email, String name, String pictureURL) {
        this.userid = userid;
        this.email = email;
        this.name = name;
        this.pictureURL = pictureURL;
    }

    @NonNull
    public String getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPictureURL() {
        return pictureURL;
    }
}
