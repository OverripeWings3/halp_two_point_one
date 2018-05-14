package com.iteso.is699367.halp_3.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Tasks implements Parcelable {
    private int id;
    private String name;
    private String duedate;
    private String done;
    private String clas;

    public String getClas() {
        return clas;
    }

    public void setClas(String clas) {
        this.clas = clas;
    }

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

    public String getDuedate() {
        return duedate;
    }

    public void setDuedate(String duedate) {
        this.duedate = duedate;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }


    public Tasks() {

    }

    public Tasks(String name, String duedate, String done, String clas) {
        this.name = name;
        this.duedate = duedate;
        this.done = done;
        this.clas = clas;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.duedate);
        dest.writeString(this.done);
        dest.writeString(this.clas);
    }

    private Tasks(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.duedate = in.readString();
        this.done = in.readString();
        this.clas = in.readString();
    }

    public static final Creator<Tasks> CREATOR = new Creator<Tasks>() {
        @Override
        public Tasks createFromParcel(Parcel source) {
            return new Tasks(source);
        }

        @Override
        public Tasks[] newArray(int size) {
            return new Tasks[size];
        }
    };


}
