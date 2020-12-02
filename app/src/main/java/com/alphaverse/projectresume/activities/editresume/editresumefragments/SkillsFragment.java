package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Skills;
import com.alphaverse.projectresume.model.Strengths;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;

import java.util.List;

public class SkillsFragment extends Fragment implements SkillsDialogFragment.InsertSkill, SkillsDialogFragment.UpdateSkill, SkillsRecyclerViewAdapter.OnItemClickListener, StrengthRecyclerViewAdapter.OnStrengthClickListener
        , SkillsDialogFragment.InsertStrength, SkillsDialogFragment.UpdateStrength {
    public static final String TAG = "SkillsFragment";
    public static final String S_PROFILE_ID = "s_profile_id";
    public static final int REQ_CODE_FOR_SKILL_CREATE = 45;
    public static final int REQ_CODE_FOR_SKILL_UPDATE = 55;
    public static final String STRENGTH_PROFILE_ID = "strength_profile_id";
    public static final int REQ_CODE_FOR_STRENGTH_CREATE = 12;
    public static final int REQ_CODE_FOR_STRENGTH_UPDATE = 15;
    private View skillsView;
    private ImageButton skillsBtn;
    private ImageButton strengthBtn;
    private SkillsRecyclerViewAdapter.OnItemClickListener listener = this;
    private StrengthRecyclerViewAdapter.OnStrengthClickListener strengthClickListener = this;
    private SkillsRecyclerViewAdapter skillsRecyclerViewAdapter;
    private RecyclerView skillsRecyclerView;
    private RecyclerView strengthsRecyclerView;
    private StrengthRecyclerViewAdapter strengthRecyclerViewAdapter;
    private int profileId;
    private Context context;
    private ResumeViewModel resumeViewModel;

    public SkillsFragment() {
        // Required empty public constructor
    }

    public SkillsFragment(int profileId, Context context) {
        this.profileId = profileId;
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        skillsView = inflater.inflate(R.layout.fragment_skills, container, false);
        resumeViewModel = ViewModelProviders.of(this).get(ResumeViewModel.class);
        skillsBtn = skillsView.findViewById(R.id.skills_add_iv);
        strengthBtn = skillsView.findViewById(R.id.strength_add_iv);

        initSkill();
        initStrength();
        getStrengthsList();
        getSkillsList();


        return skillsView;
    }

    private void getStrengthsList() {
        strengthsRecyclerView = skillsView.findViewById(R.id.strength_rv);
        resumeViewModel.getStrengthOfTheProfile(profileId).observe(this, new Observer<List<Strengths>>() {
            @Override
            public void onChanged(List<Strengths> strengths) {
                if (strengths != null) {
                    strengthRecyclerViewAdapter = new StrengthRecyclerViewAdapter(context, strengthClickListener);
                    strengthsRecyclerView.setAdapter(strengthRecyclerViewAdapter);
                    strengthsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    strengthRecyclerViewAdapter.setProfileWithStrengthsList(strengths);
                }
            }
        });
    }

    private void getSkillsList() {
        resumeViewModel.getskillsOfTheProfile(profileId).observe(this, new Observer<List<Skills>>() {
            @Override
            public void onChanged(List<Skills> skillsList) {
                if (skillsList != null) {
                    skillsRecyclerViewAdapter = new SkillsRecyclerViewAdapter(context, listener);
                    skillsRecyclerView.setAdapter(skillsRecyclerViewAdapter);
                    skillsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    skillsRecyclerViewAdapter.setProfileWithSkillsList(skillsList);
                }
            }
        });
    }


    private void initSkill() {
        skillsRecyclerView = skillsView.findViewById(R.id.skills_rv);
        skillsBtn.setOnClickListener(v -> {
            SkillsDialogFragment dialog = new SkillsDialogFragment();
            dialog.setTargetFragment(SkillsFragment.this, REQ_CODE_FOR_SKILL_CREATE);
            Bundle bundle = new Bundle();
            bundle.putInt(S_PROFILE_ID, profileId);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "SkillsDialog");
        });
    }

    private void initStrength() {

        strengthBtn.setOnClickListener(v -> {
            SkillsDialogFragment dialog = new SkillsDialogFragment();
            dialog.setTargetFragment(SkillsFragment.this, REQ_CODE_FOR_STRENGTH_CREATE);
            Bundle bundle = new Bundle();
            bundle.putInt(STRENGTH_PROFILE_ID, profileId);
            dialog.setArguments(bundle);
            dialog.show(getFragmentManager(), "StrengthDialog");
        });


    }


    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void sendUpdate(Skills skills) {
        resumeViewModel.updateskillsForProfile(skills);
    }

    @Override
    public void sendInsert(Skills skills) {
        resumeViewModel.insertSkillsForProfile(skills);


    }

    @Override
    public void onItemClicked(View view, int skillId, int skillPId, String skill) {
        Bundle bundle = new Bundle();
        bundle.putInt("skillId", skillId);
        bundle.putInt("skillProfileId", skillPId);
        bundle.putString("skill", skill);
        SkillsDialogFragment dialog = new SkillsDialogFragment();
        dialog.setTargetFragment(SkillsFragment.this, REQ_CODE_FOR_SKILL_UPDATE);
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(), "SkillUpdateDialog");
    }

    @Override
    public void onStrengthClicked(View view, int strengthId, int strengthPId, String strength) {
        Bundle updateStrengthBundle=new Bundle();
        updateStrengthBundle.putInt("strengthId",strengthId);
        updateStrengthBundle.putInt("strengthProfileId",strengthPId);
        updateStrengthBundle.putString("strength",strength);
        SkillsDialogFragment dialog = new SkillsDialogFragment();
        dialog.setTargetFragment(SkillsFragment.this, REQ_CODE_FOR_STRENGTH_UPDATE);
        dialog.setArguments(updateStrengthBundle);
        dialog.show(getFragmentManager(), "StrengthUpdateDialog");
    }

    @Override
    public void sendStrengthUpdate(Strengths strengths) {
        resumeViewModel.updateStrengthForProfile(strengths);
    }

    @Override
    public void sendStrengthInsert(Strengths strengths) {
        resumeViewModel.insertStrengthForProfile(strengths);
    }
}