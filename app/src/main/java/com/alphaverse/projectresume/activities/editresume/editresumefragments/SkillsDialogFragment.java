package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.annotation.SuppressLint;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.model.Skills;
import com.alphaverse.projectresume.model.Strengths;

import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AIVFragment.A_PROFILE_ID;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AIVFragment.IV_PROFILE_ID;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AIVFragment.REQ_CODE_FOR_ACHIEVEMENT_CREATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AIVFragment.REQ_CODE_FOR_ACHIEVEMENT_UPDATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AIVFragment.REQ_CODE_FOR_IV_CREATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AIVFragment.REQ_CODE_FOR_IV_UPDATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.SkillsFragment.REQ_CODE_FOR_SKILL_CREATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.SkillsFragment.REQ_CODE_FOR_SKILL_UPDATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.SkillsFragment.REQ_CODE_FOR_STRENGTH_CREATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.SkillsFragment.REQ_CODE_FOR_STRENGTH_UPDATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.SkillsFragment.STRENGTH_PROFILE_ID;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.SkillsFragment.S_PROFILE_ID;

;

public class SkillsDialogFragment extends DialogFragment {

    private static final String TAG = "SkillsDialogFragment";
    public InsertSkill insertSkill;
    public UpdateSkill updateSkill;
    public InsertStrength insertStrength;
    public UpdateStrength updateStrength;
    public InsertAchievement insertAchievement;
    public UpdateAchievement updateAchievement;
    public InsertIV insertIV;
    public UpdateIV updateIV;
    private int profileId;
    private EditText editText;
    private TextView headTextView;

    @SuppressLint("LongLogTag")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

            insertSkill = (InsertSkill) getTargetFragment();

        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {

            updateSkill = (UpdateSkill) getTargetFragment();

        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {
            insertStrength = (InsertStrength) getTargetFragment();

        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {
            updateStrength = (UpdateStrength) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {
            insertAchievement = (InsertAchievement) getTargetFragment();

        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {
            updateAchievement = (UpdateAchievement) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {
            insertIV = (InsertIV) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {
            updateIV = (UpdateIV) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_skills_popup, container, false);
        headTextView = view.findViewById(R.id.create_skills_tv);
        editText = view.findViewById(R.id.et_skills);
        ImageButton saveSkill = view.findViewById(R.id.save_skills_iv);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        switch (getTargetRequestCode()) {
            case REQ_CODE_FOR_SKILL_CREATE:
                Bundle bundle = getArguments();
                profileId = bundle.getInt(S_PROFILE_ID);
                saveSkill.setOnClickListener(view1 -> {

                    Skills skills = new Skills(profileId, editText.getText().toString());
                    insertSkill.sendInsert(skills);
                    getDialog().dismiss();
                    bundle.clear();
                });
                break;
            case REQ_CODE_FOR_SKILL_UPDATE:
                Bundle bundle1 = getArguments();
                int sId = bundle1.getInt("skillId");
                int sProfileId = bundle1.getInt("skillProfileId");
                String skill = bundle1.getString("skill");
                editText.setText(skill);
                saveSkill.setOnClickListener(v -> {
                    Skills updateSkills = new Skills(sId, sProfileId, editText.getText().toString());
                    updateSkill.sendUpdate(updateSkills);
                    getDialog().dismiss();
                    bundle1.clear();
                });

                break;
            case REQ_CODE_FOR_STRENGTH_CREATE:
                headTextView.setText(R.string.strength);
                editText.setHint(R.string.strength);
                Bundle strengthCreateBundle = getArguments();
                profileId = strengthCreateBundle.getInt(STRENGTH_PROFILE_ID);
                saveSkill.setOnClickListener(v -> {
                    Strengths strengths = new Strengths(profileId, editText.getText().toString());
                    insertStrength.sendStrengthInsert(strengths);
                    getDialog().dismiss();
                    strengthCreateBundle.clear();
                });

                break;

            case REQ_CODE_FOR_STRENGTH_UPDATE:
                headTextView.setText(R.string.strength);
                Bundle strengthUpdateBundle = getArguments();
                int strengthId = strengthUpdateBundle.getInt("strengthId");
                int strengthPId = strengthUpdateBundle.getInt("strengthProfileId");
                String strength = strengthUpdateBundle.getString("strength");
                editText.setText(strength);
                saveSkill.setOnClickListener(v -> {
                    Strengths updateStrengths = new Strengths(strengthId, strengthPId, editText.getText().toString());
                    updateStrength.sendStrengthUpdate(updateStrengths);
                    getDialog().dismiss();
                    strengthUpdateBundle.clear();

                });

            case REQ_CODE_FOR_ACHIEVEMENT_CREATE:
                headTextView.setText(R.string.achievement);
                editText.setHint(R.string.achievement);
                Bundle createAchievementBundle=getArguments();
                profileId=createAchievementBundle.getInt(A_PROFILE_ID);
                saveSkill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Achievements achievements=new Achievements(profileId,editText.getText().toString());
                        insertAchievement.sendAchievementInsert(achievements);
                        getDialog().dismiss();
                        createAchievementBundle.clear();
                    }
                });

                break;

            case REQ_CODE_FOR_ACHIEVEMENT_UPDATE:
                headTextView.setText(R.string.achievement);
                Bundle updateAchievementBundle=getArguments();
               int achievementId= updateAchievementBundle.getInt("achievementId");
               int achievementProfileId= updateAchievementBundle.getInt("achievementProfileId");
               String achievement= updateAchievementBundle.getString("achievement");
                editText.setText(achievement);
                saveSkill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Achievements updateAchievements=new Achievements(achievementId,achievementProfileId,editText.getText().toString());
                        updateAchievement.sendAchievementUpdate(updateAchievements);
                        getDialog().dismiss();
                        updateAchievementBundle.clear();
                    }
                });
                break;
            case REQ_CODE_FOR_IV_CREATE:
                headTextView.setText(R.string.iv);
                editText.setHint(R.string.iv);
                Bundle createIVBundle=getArguments();
                profileId=createIVBundle.getInt(IV_PROFILE_ID);
                saveSkill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IndustrialVisit industrialVisit=new IndustrialVisit(profileId,editText.getText().toString());
                        insertIV.sendIVInsert(industrialVisit);
                        getDialog().dismiss();
                        createIVBundle.clear();
                    }
                });
                break;

            case REQ_CODE_FOR_IV_UPDATE:
                headTextView.setText(R.string.iv);
                Bundle updateIVBundle=getArguments();
                int IVId=updateIVBundle.getInt("IVId");
                int IVProfileId=updateIVBundle.getInt("IVProfileId");
                String IV=updateIVBundle.getString("IV");
                editText.setText(IV);
                saveSkill.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        IndustrialVisit updateIndustrialVisit=new IndustrialVisit(IVId,IVProfileId,editText.getText().toString());
                        updateIV.sendIVUpdate(updateIndustrialVisit);
                        getDialog().dismiss();
                        updateIVBundle.clear();
                    }
                });

                break;

        }

        return view;
    }

    public interface UpdateSkill {
        void sendUpdate(Skills skills);
    }

    public interface InsertSkill {
        void sendInsert(Skills skills);
    }

    public interface UpdateStrength {
        void sendStrengthUpdate(Strengths strengths);
    }

    public interface InsertStrength {
        void sendStrengthInsert(Strengths strengths);
    }

    public interface UpdateAchievement {
        void sendAchievementUpdate(Achievements achievements);
    }

    public interface InsertAchievement {
        void sendAchievementInsert(Achievements achievements);
    }

    public interface UpdateIV {
        void sendIVUpdate(IndustrialVisit industrialVisit);
    }

    public interface InsertIV {
        void sendIVInsert(IndustrialVisit industrialVisit);
    }

}
