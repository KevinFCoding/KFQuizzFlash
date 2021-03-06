package com.azzkf.quizzflash;

import android.os.Parcel;
import android.os.Parcelable;

/***
 * First object to manage the hub activity.
 * Easier scallability for new Games / Quizz implementations.
 */
public class HubTag implements Parcelable {
    private String whichQuizz;
    private String whichList;
    private int backgroundHub;
    private String choosedQuizz;

    public HubTag(String whichQuizz, String whichList, int backgroundHub, String choosedQuizz) {
        this.whichQuizz = whichQuizz;
        this.whichList = whichList;
        this.backgroundHub = backgroundHub;
        this.choosedQuizz = choosedQuizz;
    }

    protected HubTag(Parcel in) {
        whichQuizz = in.readString();
        whichList = in.readString();
        backgroundHub = in.readInt();
        choosedQuizz = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(whichQuizz);
        dest.writeString(whichList);
        dest.writeInt(backgroundHub);
        dest.writeString(choosedQuizz);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<HubTag> CREATOR = new Creator<HubTag>() {
        @Override
        public HubTag createFromParcel(Parcel in) {
            return new HubTag(in);
        }

        @Override
        public HubTag[] newArray(int size) {
            return new HubTag[size];
        }
    };

    /***
     * Getter presseted for each Contructor information 
     * @return
     */

    public String getWhichQuizz() {
        return whichQuizz;
    }

    public String getWhichList() {
        return whichList;
    }

    public int getBackgroundHub() {
        return backgroundHub;
    }

    public String getChoosedQuizz() {
        return choosedQuizz;
    }
}
