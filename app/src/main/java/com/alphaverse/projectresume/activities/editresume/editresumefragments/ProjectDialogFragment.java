package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Projects;

import static com.alphaverse.projectresume.activities.editresume.editresumefragments.ProjectFragment.PROJECT_PROFILE_ID;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.ProjectFragment.REQ_CODE_FOR_PROJECT_CREATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.ProjectFragment.REQ_CODE_FOR_PROJECT_UPDATE;

public class ProjectDialogFragment extends DialogFragment {
    private static final String TAG ="ProjectDialogFragment" ;
    public InsertProject insertProject;
    public UpdateProject updateProject;
    private int profileId;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            insertProject=(InsertProject)getTargetFragment();
        }catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        } try {
            updateProject=(UpdateProject) getTargetFragment();
        }catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View popupProjectView=inflater.inflate(R.layout.create_project_popup,container,false);
        EditText etProjectName = popupProjectView.findViewById(R.id.et_project_name);
        EditText etProjectDescription = popupProjectView.findViewById(R.id.et_description);
        EditText etProjectDuration = popupProjectView.findViewById(R.id.et_duration);
        ImageButton saveBtn = popupProjectView.findViewById(R.id.save_project_iv);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        switch (getTargetRequestCode()) {
            case REQ_CODE_FOR_PROJECT_CREATE:
                Bundle bundle = getArguments();
                profileId = bundle.getInt(PROJECT_PROFILE_ID);
                saveBtn.setOnClickListener(view1 -> {
                    Projects projects = new Projects(profileId, etProjectName.getText().toString(), etProjectDescription.getText().toString(), etProjectDuration.getText().toString());
                    insertProject.sendInsert(projects);
                    getDialog().dismiss();
                    bundle.clear();
                });
                break;
            case REQ_CODE_FOR_PROJECT_UPDATE:
                Bundle bundle1 = getArguments();
                int projectId = bundle1.getInt("profileId");
                int projectProfileId = bundle1.getInt("ProjectProfileId");
                String projectName = bundle1.getString("projectName");
                String description = bundle1.getString("description");
                String duration = bundle1.getString("duration");

                etProjectName.setText(projectName);
                etProjectDescription.setText(description);
                etProjectDuration.setText(duration);
                saveBtn.setOnClickListener(view12 -> {

                    Projects projects=new Projects(projectId,projectProfileId,etProjectName.getText().toString(),etProjectDescription.getText().toString(),etProjectDuration.getText().toString());
                    updateProject.sendUpdate(projects);
                    getDialog().dismiss();
                    bundle1.clear();
                });
                break;
        }

        return popupProjectView;
    }

    public interface UpdateProject {
        void sendUpdate(Projects inputFromProject);
    }

    public interface InsertProject {
        void sendInsert(Projects inputFromProject);
    }
}

