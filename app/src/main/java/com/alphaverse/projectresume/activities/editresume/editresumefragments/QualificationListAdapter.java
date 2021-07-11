package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Qualification;

import java.util.List;


public class QualificationListAdapter extends RecyclerView.Adapter<QualificationListAdapter.QualificationViewHolder> {
    public OnItemClickListener listener;
    private Context context;
    private LayoutInflater inflater;
    private List<Qualification> ProfileWithQualificationsList;

    public QualificationListAdapter(OnItemClickListener listener, Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }
//    private ResumeMakerViewModel resumeMakerViewModel;

    @NonNull
    @Override
    public QualificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.qualification_list_item, parent, false);
        return new QualificationViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull QualificationViewHolder holder, int position) {
        if (ProfileWithQualificationsList != null) {
            Qualification current = ProfileWithQualificationsList.get(position);
            holder.setQualification(current.getQualificationId(), current.getQProfileId(), current.getDegree(), current.getInstitution(), current.getYOP(), current.getPercentage());
        }
    }

    public void setProfileWithQualificationsList(List<Qualification> qualifications) {
        ProfileWithQualificationsList = qualifications;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (ProfileWithQualificationsList != null) {
            return ProfileWithQualificationsList.size();
        } else return 0;
    }

    public interface OnItemClickListener {
        void onItemClicked(View view, int qId, int qPId, String degree, String ins, String yop, String percentage);
    }

    public class QualificationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvDegree;
        private ImageButton editBtn,deleteBtn;
        private Context context;
        private int qId, qProfileId;
        private String degree, institution, YOP, percentage;

        public QualificationViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;
            tvDegree = itemView.findViewById(R.id.qualification_list_tv);
            editBtn = itemView.findViewById(R.id.qualification_list_edit_iv);
            deleteBtn = itemView.findViewById(R.id.qualification_list_delete_iv);

            editBtn.setOnClickListener(this);
        }

        public void setQualification(int qId, int qProfileId, String degree, String institution, String YOP, String percentage) {
            this.qId = qId;
            this.qProfileId = qProfileId;
            this.degree = degree;
            this.institution = institution;
            this.YOP = YOP;
            this.percentage = percentage;
            tvDegree.setText("Education-"+(getAdapterPosition()+1));
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.qualification_list_edit_iv:
                    listener.onItemClicked(view, qId, qProfileId, degree, institution, YOP, percentage);


            }
        }
    }
}
