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
import com.alphaverse.projectresume.model.Projects;


import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ProjectViewHolder> {
    public OnItemClickListener listener;
    private Context context;
    private LayoutInflater inflater;
    private List<Projects> ProfileWithProjectsList;

    public ProjectListAdapter(OnItemClickListener listener, Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.project_item, parent, false);
        return new ProjectListAdapter.ProjectViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Projects current = ProfileWithProjectsList.get(position);
        holder.setProject(current.getProjectId(), current.getProjectProfileId(), current.getProjectName(), current.getProjectDescription(), current.getProjectDuration());
    }
    public void setProfileWithProjectsList(List<Projects> projects) {
        ProfileWithProjectsList = projects;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (ProfileWithProjectsList != null) {
            return ProfileWithProjectsList.size();
        } else return 0;
    }
    public interface OnItemClickListener {
        void onItemClicked(View view, int projectId, int projectPId, String projectName, String description, String duration);
    }

    public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvProjectName;
        private ImageButton editBtn,deleteBtn;
        private Context context;
        private int projectId, projectProfileId;
        private String projectName, description, duration;
        public ProjectViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            context = ctx;
            tvProjectName = itemView.findViewById(R.id.project_list_tv);
            editBtn = itemView.findViewById(R.id.project_list_edit_iv);
            deleteBtn = itemView.findViewById(R.id.project_list_delete_iv);

            editBtn.setOnClickListener( this);
        }
        public void setProject(int projectId, int projectProfileId, String projectName, String description, String duration){
            this.projectId=projectId;
            this.projectProfileId=projectProfileId;
            this.projectName=projectName;
            this.description=description;
            this.duration=duration;
            tvProjectName.setText(projectName);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.project_list_edit_iv:
                    listener.onItemClicked(v,projectId,projectProfileId,projectName,description,duration);


            }
        }
    }
}
