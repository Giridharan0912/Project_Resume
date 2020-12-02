package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.Skills;

import java.util.List;

public class AchievementRecyclerViewAdapter extends RecyclerView.Adapter<AchievementRecyclerViewAdapter.AchievementViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Achievements> achievementsList;
    private OnAchievementClickListener onAchievementClickListener;

    public AchievementRecyclerViewAdapter(Context context,OnAchievementClickListener onAchievementClickListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.onAchievementClickListener=onAchievementClickListener;
    }

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.skills_item, parent, false);
        return new AchievementViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {
        Achievements current=achievementsList.get(position);
        holder.setAchievement(current.getAchievementId(),current.achievementProfileId,current.achievement);

    }

    public void setProfileWithAchievementList(List<Achievements> achievements) {
        achievementsList = achievements;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (achievementsList != null) {
            return achievementsList.size();
        } else return 0;
    }
    public interface OnAchievementClickListener {
        void onAchievementClicked(View view, int achievementId, int achievementPId, String achievement);
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView achievementTextView;
        private int achievementId, achievementProfileId;
        private Context context;
        private String achievement;
        public AchievementViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            achievementTextView=itemView.findViewById(R.id.skill_list_tv);
            this.context=ctx;
            achievementTextView.setOnClickListener(this);
        }
        private void setAchievement(int achievementId,int achievementProfileId,String achievement){
            this.achievementId=achievementId;
            this.achievementProfileId=achievementProfileId;
            this.achievement=achievement;
            achievementTextView.setText(achievement);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.skill_list_tv:
                    onAchievementClickListener.onAchievementClicked(v,achievementId,achievementProfileId,achievement);
            }
            }
        }
    }

