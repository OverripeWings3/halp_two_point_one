package com.iteso.is699367.halp_3.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Tasks implements Parcelable {
    private int id;
    private String name;
    private String dueDate;
    private int done;
    private Classes clas;

    public Classes getClas() {
        return clas;
    }

    public void setClas(Classes clas) {
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

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }


    public Tasks() {

    }

    public Tasks(String name, String dueDate, int done, Classes clas) {
        this.name = name;
        this.dueDate = dueDate;
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
        dest.writeString(this.dueDate);
        dest.writeInt(this.done);
        dest.writeParcelable(this.clas, flags);
    }

    private Tasks(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.dueDate = in.readString();
        this.done = in.readInt();
        this.clas = in.readParcelable(Classes.class.getClassLoader());
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
