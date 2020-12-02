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
import com.alphaverse.projectresume.model.Strengths;

import java.util.List;

public class StrengthRecyclerViewAdapter extends RecyclerView.Adapter<StrengthRecyclerViewAdapter.StrengthViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private OnStrengthClickListener listener;
    private List<Strengths> strengthsList;

    public StrengthRecyclerViewAdapter(Context context, OnStrengthClickListener listener) {
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public StrengthViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.skills_item, parent, false);
        return new StrengthViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull StrengthViewHolder holder, int position) {
        if (strengthsList!=null){
            Strengths current=strengthsList.get(position);
            holder.setStrength(current.getStrengthId(),current.getStrProfileId(),current.getStrength());
        }

    }

    public void setProfileWithStrengthsList(List<Strengths> strengths) {
        strengthsList = strengths;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (strengthsList != null) {
            return strengthsList.size();
        } else return 0;
    }

    public interface OnStrengthClickListener {
        void onStrengthClicked(View view, int strengthId, int strengthPId, String strength);
    }

    public class StrengthViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView strengthTextView;
        private int strengthId, strengthProfileId;
        private Context context;
        private String strength;
        public StrengthViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            strengthTextView=itemView.findViewById(R.id.skill_list_tv);
            this.context=ctx;
            strengthTextView.setOnClickListener(this);
        }
        private void setStrength(int strengthId, int strengthProfileId, String strength) {
            this.strengthId = strengthId;
            this.strengthProfileId = strengthProfileId;
            this.strength = strength;
            strengthTextView.setText(strength);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.skill_list_tv:
                    listener.onStrengthClicked(v,strengthId,strengthProfileId,strength);
                    break;
            }



        }
    }
}
