package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Projects;
import com.alphaverse.projectresume.model.Qualification;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProjectFragment extends Fragment implements ProjectDialogFragment.InsertProject,ProjectDialogFragment.UpdateProject,ProjectListAdapter.OnItemClickListener{
    private View projectView;
    private Context context;
    private  int profileId;
    public static final String PROJECT_PROFILE_ID = "project_profile_id";
    public static final int REQ_CODE_FOR_PROJECT_UPDATE = 23;
    public static final int REQ_CODE_FOR_PROJECT_CREATE = 90;
    private RecyclerView project_recycler_view;
    private ResumeViewModel resumeViewModel;
    private ProjectListAdapter projectListAdapter;
    private ProjectListAdapter.OnItemClickListener listener = this;
    public ProjectFragment(Context context, int profileId) {
        this.context = context;
        this.profileId = profileId;
    }

    public ProjectFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        projectView=inflater.inflate(R.layout.fragment_project,container,false);
        resumeViewModel= ViewModelProviders.of(this).get(ResumeViewModel.class);
        project_recycler_view=projectView.findViewById(R.id.project_rv);
        initProject();
        getProjectList();
        return projectView;
    }

    private void getProjectList() {
        resumeViewModel.getProjectsOfTheProfile(profileId).observe(this, new Observer<List<Projects>>() {
            @Override
            public void onChanged(List<Projects> projectsList) {
                if (projectsList != null) {
                    projectListAdapter = new ProjectListAdapter(listener, context);
                    project_recycler_view.setAdapter(projectListAdapter);
                    project_recycler_view.setLayoutManager(new LinearLayoutManager(context));
                    projectListAdapter.setProfileWithProjectsList(projectsList);
                }
            }
        });
    }

    private void initProject() {
        project_recycler_view=projectView.findViewById(R.id.project_rv);

        FloatingActionButton fab = projectView.findViewById(R.id.project_fab);
        fab.setOnClickListener(view -> {
            ProjectDialogFragment dialog = new ProjectDialogFragment();
            dialog.setTargetFragment(ProjectFragment.this, REQ_CODE_FOR_PROJECT_CREATE);
            Bundle bundle = new Bundle();
            bundle.putInt(PROJECT_PROFILE_ID, profileId);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "ProjectDialog");
        });
    }

    @Override
    public void sendUpdate(Projects inputFromProject) {
        resumeViewModel.updateProjectForProfile(inputFromProject);
    }

    @Override
    public void sendInsert(Projects inputFromProject) {

resumeViewModel.insertProjectForProfile(inputFromProject);
    }

    @Override
    public void onItemClicked(View view, int projectId, int projectPId, String projectName, String description, String duration) {
        Bundle bundle = new Bundle();
        bundle.putInt("profileId", projectId);
        bundle.putInt("ProjectProfileId", projectPId);
        bundle.putString("projectName", projectName);
        bundle.putString("description", description);
        bundle.putString("duration", duration);
        ProjectDialogFragment dialogFragment=new ProjectDialogFragment();
        dialogFragment.setTargetFragment(ProjectFragment.this, REQ_CODE_FOR_PROJECT_UPDATE);
        dialogFragment.setArguments(bundle);
        dialogFragment.show(getFragmentManager(),"ProjectUpdateDialog");

    }
}