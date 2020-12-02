package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.model.Skills;

import java.util.List;

public class IVRecyclerViewAdapter extends RecyclerView.Adapter<IVRecyclerViewAdapter.IVViewHolder> {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<IndustrialVisit> industrialVisitList;
    private OnIVClickListener onIVClickListener;

    public IVRecyclerViewAdapter(Context context, OnIVClickListener onIVClickListener) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.onIVClickListener=onIVClickListener;
    }

    @NonNull
    @Override
    public IVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = layoutInflater.inflate(R.layout.skills_item, parent, false);
        return new IVViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull IVViewHolder holder, int position) {
IndustrialVisit current=industrialVisitList.get(position);
holder.setIV(current.getIVId(),current.getIVProfileId(),current.IV);
    }

    public void setProfileWithIVList(List<IndustrialVisit> industrialVisits) {
        industrialVisitList = industrialVisits;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (industrialVisitList != null) {
            return industrialVisitList.size();
        } else return 0;
    }
    public interface OnIVClickListener {
        void onIVClicked(View view, int IVId, int IVPId, String IV);
    }

    public class IVViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView IVTextView;
        private int IVId, IVProfileId;
        private Context context;
        private String IV;
        public IVViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            IVTextView=itemView.findViewById(R.id.skill_list_tv);
            this.context=ctx;
            IVTextView.setOnClickListener(this);

        }
        private void  setIV(int IVId,int IVProfileId,String IV){
            this.IVId=IVId;
            this.IVProfileId=IVProfileId;
            this.IV=IV;
            IVTextView.setText(IV);
        }


        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.skill_list_tv:
                    onIVClickListener.onIVClicked(v,IVId,IVProfileId,IV);
            }

        }
    }
}
