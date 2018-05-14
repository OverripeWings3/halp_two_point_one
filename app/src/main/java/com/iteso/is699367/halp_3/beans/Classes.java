package com.iteso.is699367.halp_3.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Classes implements Parcelable {
    private String name;
    private String room;
    private String teacher;
    private String time;


    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Classes() {

    }
    public Classes(String name, String room, String teacher, String time) {
        this.name =name;
        this.room =room;
        this.teacher =teacher;
        this.time =time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.room);
        dest.writeString(this.teacher);
        dest.writeString(this.time);
    }

    private Classes(Parcel in) {
        this.name = in.readString();
        this.room = in.readString();
        this.teacher = in.readString();
        this.time = in.readString();
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

