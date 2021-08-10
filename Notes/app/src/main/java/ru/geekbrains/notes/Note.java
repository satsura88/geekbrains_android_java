package ru.geekbrains.notes;

import android.os.Parcel;
import android.os.Parcelable;

public class Note implements Parcelable {
    private String name;
    private String description;
    private int date;

    public Note(String name, String description, int date) {
        this.name = name;
        this.description = description;
        this.date = date;
    }

    protected Note(Parcel in) {
        name = in.readString();
        description = in.readString();
        date = in.readInt();
    }

    public Note(int index, String s) {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(date);
    }

    @Override
    public int describeContents() {

        return 0;
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
