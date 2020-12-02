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
import com.alphaverse.projectresume.model.Qualification;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.alphaverse.projectresume.activities.profilelist.ProfileListAdapter.PROFILE_ID;

public class AcademicFragment extends Fragment implements QualificationDialogFragment.InsertQ, QualificationListAdapter.OnItemClickListener, QualificationDialogFragment.UpdateQ {
    public static final String Q_PROFILE_ID = "q_profile_id";
    public static final int REQ_CODE_UPDATE = 2;
    public static final int REQ_CODE_TO_CREATE = 1;
    private View academicView;
    private int profileId;
    private Context context;
    private RecyclerView academic_recycler_view;
    private ResumeViewModel resumeViewModel;
    private QualificationListAdapter qualificationListAdapter;
    private QualificationListAdapter.OnItemClickListener listener = this;

    public AcademicFragment(Context context, int profileId) {
        this.context = context;
        this.profileId = profileId;

    }

    public AcademicFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        academicView = inflater.inflate(R.layout.fragment_academic, container, false);
        resumeViewModel= ViewModelProviders.of(this).get(ResumeViewModel.class);
        academic_recycler_view=academicView.findViewById(R.id.academic_rv);
        initUI();

        getQualificationList();

        return academicView;
    }

    private void getQualificationList() {
        resumeViewModel.getQualificationOfTheProfile(profileId).observe(this, new Observer<List<Qualification>>() {
            @Override
            public void onChanged(List<Qualification> qualifications) {
                if (qualifications != null) {
                    qualificationListAdapter = new QualificationListAdapter(listener, context);
                    academic_recycler_view.setAdapter(qualificationListAdapter);
                    academic_recycler_view.setLayoutManager(new LinearLayoutManager(context));
                    qualificationListAdapter.setProfileWithQualificationsList(qualifications);
                }
            }
        });
    }

    private void initUI() {
        academic_recycler_view=academicView.findViewById(R.id.academic_rv);
        FloatingActionButton fab = academicView.findViewById(R.id.academic_fab);
        fab.setOnClickListener(view -> {
            QualificationDialogFragment dialog = new QualificationDialogFragment();
            dialog.setTargetFragment(AcademicFragment.this, REQ_CODE_TO_CREATE);
            Bundle bundle = new Bundle();
            bundle.putInt(Q_PROFILE_ID, profileId);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "QualificationDialog");
        });
    }

    @Override
    public void sendUpdate(Qualification inputFromQualification) {
        resumeViewModel.updateQualificationForProfile(inputFromQualification);
    }

    @Override
    public void sendInsert(Qualification inputFromQualification) {
        resumeViewModel.insertQualificationForProfile(inputFromQualification);
    }

    @Override
    public void onItemClicked(View view, int qId, int qPId, String degree, String ins, String yop, String percentage) {
        Bundle bundle = new Bundle();
        bundle.putInt("QId", qId);
        bundle.putInt("QProfileId", qPId);
        bundle.putString("Degree", degree);
        bundle.putString("Institution", ins);
        bundle.putString("YOP", yop);
        bundle.putString("Percentage", percentage);
        QualificationDialogFragment dialog = new QualificationDialogFragment();
        dialog.setTargetFragment(AcademicFragment.this, REQ_CODE_UPDATE);
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), "QualificationUpdateDialog");

    }
}