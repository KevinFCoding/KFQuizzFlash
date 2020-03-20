package com.azzkf.quizzflash;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionsAdapter extends RecyclerView.Adapter<QuestionsAdapter.ViewHolder> implements View.OnClickListener {

    String TAG = "QuestionAdapter";
    private List<Question> level;

    public QuestionsAdapter(List<Question> level) {
        this.level = level;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemquestion, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Question question = level.get(position);

        holder.RA.setText(question.getAnswerA());
        holder.RB.setText(question.getAnswerB());
        holder.RC.setText(question.getAnswerC());
        holder.RD.setText(question.getGoodAnswers());
        holder.img.setImageResource(question.getImgID());
        holder.qst.setText(question.getQuestion());
        holder.itemView.setTag(question);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {

        return level.size();
    }

    /***
     * Hardest part with some problem due to the ArrayList
     * q and v?getTag() sending an object and not an ArrayList caused me some trouble and made me
     * create some safe parameter on QuizzGame
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.itemRecycler:
                Question q = (Question) v.getTag();
                Context context = v.getContext();
                Intent intent = new Intent(context, QuizzGameActivity.class);
                intent.putExtra("questions", q);
                intent.putExtra("difficulty", "Special");
                intent.putExtra("back", R.drawable.mario);
                context.startActivity(intent);
                break;
        }

    }

    //Viewholder linked to a graphic element

    class ViewHolder extends RecyclerView.ViewHolder {

        /***
         * Getting the holder for each response, the question and the img to place on the item
         */

        final ImageView img;
        final TextView RA;
        final TextView RB;
        final TextView RC;
        final TextView RD;
        final TextView qst;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.listImageView);
            RA = itemView.findViewById(R.id.RAtextView);
            RB = itemView.findViewById(R.id.RBtextView);
            RC = itemView.findViewById(R.id.RCtextView);
            RD = itemView.findViewById(R.id.RDtextView);
            qst = itemView.findViewById(R.id.questiontextView);


        }
    }
}
