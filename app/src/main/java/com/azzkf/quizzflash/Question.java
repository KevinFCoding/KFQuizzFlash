package com.azzkf.quizzflash;

import android.media.MediaPlayer;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.util.List;

public class Question implements Parcelable {


        private List<String> answers;
        private String question;
        private String clue;
        private int imgID;
        private MediaPlayer sound;

    public Question(List<String> answers, String question, String clue, int imgID, MediaPlayer sound) {
            this.answers = answers;
            this.question = question;
            this.clue = clue;
        this.imgID = imgID;
        this.sound = sound;
    }

    protected Question(Parcel in) {
        answers = in.createStringArrayList();
        question = in.readString();
        clue = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringList(answers);
        dest.writeString(question);
        dest.writeString(clue);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public List<String> getAnswers() {
        return answers;
    }

    public String getQuestion() {
        return question;
    }

    public String getClue() {
        return clue;
    }

    public int getImg() {
        return imgID;
    }

    public MediaPlayer getSound() {
        return sound;
    }
}
