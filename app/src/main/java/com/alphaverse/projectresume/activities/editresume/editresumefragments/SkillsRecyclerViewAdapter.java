package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Skills;

import java.util.List;

public class SkillsRecyclerViewAdapter extends RecyclerView.Adapter<SkillsRecyclerViewAdapter.SkillsViewHolder> {
    public OnItemClickListener listener;
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Skills> skillsList;

    public SkillsRecyclerViewAdapter(Context context, OnItemClickListener listener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public SkillsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.skills_item, parent, false);
        return new SkillsViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillsViewHolder holder, int position) {
        if (skillsList != null) {
            Skills current = skillsList.get(position);
            holder.setSkills(current.getSkillId(), current.sProfileId, current.getSkill());
        }

    }

    public void setProfileWithSkillsList(List<Skills> skills) {
        skillsList = skills;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (skillsList != null) {
            return skillsList.size();
        } else return 0;
    }

    public interface OnItemClickListener {
        void onItemClicked(View view, int skillId, int skillPId, String skill);
    }


    public class SkillsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView skillsTextView;
        private int skillId, skillProfileId;
        private Context context;
        private String skill;

        public SkillsViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            skillsTextView = itemView.findViewById(R.id.skill_list_tv);
            this.context = ctx;
            skillsTextView.setOnClickListener(this);
        }

        private void setSkills(int skillId, int skillProfileId, String skill) {
            this.skillId = skillId;
            this.skillProfileId = skillProfileId;
            this.skill = skill;
            skillsTextView.setText(skill);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.skill_list_tv:
                    listener.onItemClicked(v, skillId, skillProfileId, skill);
            }
        }
    }
}
