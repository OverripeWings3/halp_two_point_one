package com.iteso.is699367.halp_3.beans;

import android.os.Parcel;
import android.os.Parcelable;

public class Flashcards implements Parcelable {
    private int id;
    private String front;
    private String back;

    @Override
    public String toString() {
        return front;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }
    public void setBackt(String back) {
        this.back = back;
    }


    public Flashcards() {

    }
    public Flashcards(String front, String back) {
        this.front = front;
        this.back = back;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.front);
        dest.writeString(this.back);
    }

    private Flashcards(Parcel in) {
        this.id = in.readInt();
        this.front = in.readString();
        this.back = in.readString();
    }

    public static final Creator<Flashcards> CREATOR = new Creator<Flashcards>() {
        @Override
        public Flashcards createFromParcel(Parcel source) {
            return new Flashcards(source);
        }

        @Override
        public Flashcards[] newArray(int size) {
            return new Flashcards[size];
        }
    };
}
