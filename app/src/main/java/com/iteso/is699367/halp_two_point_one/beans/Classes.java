package com.iteso.is699367.halp_two_point_one.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Classes implements Parcelable {
    private int id;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Classes() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
    }

    private Classes(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
    }

    public static final Creator<Classes> CREATOR = new Creator<Classes>() {
        @Override
        public Classes createFromParcel(Parcel source) {
            return new Classes(source);
        }

        @Override
        public Classes[] newArray(int size) {
            return new Classes[size];
        }
    };
}

