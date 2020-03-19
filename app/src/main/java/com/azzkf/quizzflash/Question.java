package com.azzkf.quizzflash;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

public class Question implements Parcelable {

        private String answerA;
        private String answerB;
        private String answerC;
        private String goodAnswers;
        private String question;
        private String clue;
        private int imgID;
        private int sound;
        private String typeQuestion;
        private int difficulty;

    public Question(String answerA, String answerB, String answerC, String goodAnswers, String question, String clue, int imgID, int sound, String typeQuestion, int difficulty) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.goodAnswers = goodAnswers;
        this.question = question;
        this.clue = clue;
        this.imgID = imgID;
        this.sound = sound;
        this.typeQuestion = typeQuestion;
        this.difficulty = difficulty;
    }

    protected Question(Parcel in) {
        answerA = in.readString();
        answerB = in.readString();
        answerC = in.readString();
        goodAnswers = in.readString();
        question = in.readString();
        clue = in.readString();
        imgID = in.readInt();
        sound = in.readInt();
        typeQuestion = in.readString();
        difficulty = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answerA);
        dest.writeString(answerB);
        dest.writeString(answerC);
        dest.writeString(goodAnswers);
        dest.writeString(question);
        dest.writeString(clue);
        dest.writeInt(imgID);
        dest.writeInt(sound);
        dest.writeString(typeQuestion);
        dest.writeInt(difficulty);
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

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getGoodAnswers() {
        return goodAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public String getClue() {
        return clue;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public int getSound() {
        return sound;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public int getDifficulty() {
        return difficulty;
    }
}
