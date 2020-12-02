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
import android.widget.ImageButton;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;

import java.util.List;


public class AIVFragment extends Fragment implements AchievementRecyclerViewAdapter.OnAchievementClickListener,IVRecyclerViewAdapter.OnIVClickListener,SkillsDialogFragment.InsertAchievement
,SkillsDialogFragment.UpdateAchievement,SkillsDialogFragment.InsertIV,SkillsDialogFragment.UpdateIV{

   private View AIVView;
   private Context context;
   private int profileId;
   private RecyclerView achievementRecyclerView;
   private RecyclerView IVRecyclerView;
   private ImageButton addAchievement;
   private ImageButton addIV;
    public static final String TAG = "AIVFragment";
    public static final String A_PROFILE_ID = "a_profile_id";
    public static final int REQ_CODE_FOR_ACHIEVEMENT_CREATE = 8;
    public static final int REQ_CODE_FOR_ACHIEVEMENT_UPDATE = 9;
    public static final String IV_PROFILE_ID = "iv_profile_id";
    public static final int REQ_CODE_FOR_IV_CREATE = 23;
    public static final int REQ_CODE_FOR_IV_UPDATE = 56;
    private AchievementRecyclerViewAdapter achievementRecyclerViewAdapter;
    private IVRecyclerViewAdapter ivRecyclerViewAdapter;
    private ResumeViewModel resumeViewModel;
    private AchievementRecyclerViewAdapter.OnAchievementClickListener achievementClickListener=this;
    private IVRecyclerViewAdapter.OnIVClickListener ivClickListener=this;

    public AIVFragment(Context context, int profileId) {
        this.context = context;
        this.profileId = profileId;
    }

    public AIVFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        AIVView=inflater.inflate(R.layout.fragment_a_i_v, container, false);
        resumeViewModel= ViewModelProviders.of(this).get(ResumeViewModel.class);
        addAchievement=AIVView.findViewById(R.id.achievement_add_iv);
        addIV=AIVView.findViewById(R.id.IV_add_iv);

        initAchievement();
        initIV();
        getAchievement();
        getIV();
        return AIVView ;
    }

    private void getIV() {
        resumeViewModel.getIVOfTheProfile(profileId).observe(this, new Observer<List<IndustrialVisit>>() {
            @Override
            public void onChanged(List<IndustrialVisit> industrialVisits) {
                if (industrialVisits!=null){
                    ivRecyclerViewAdapter=new IVRecyclerViewAdapter(context,ivClickListener);
                    IVRecyclerView.setAdapter(ivRecyclerViewAdapter);
                    IVRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    ivRecyclerViewAdapter.setProfileWithIVList(industrialVisits);
                }
            }
        });

    }

    private void getAchievement() {
        resumeViewModel.getAchievementOfTheProfile(profileId).observe(this, new Observer<List<Achievements>>() {
            @Override
            public void onChanged(List<Achievements> achievements) {
                if (achievements!=null){
                    achievementRecyclerViewAdapter=new AchievementRecyclerViewAdapter(context,achievementClickListener);
                    achievementRecyclerView.setAdapter(achievementRecyclerViewAdapter);
                    achievementRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    achievementRecyclerViewAdapter.setProfileWithAchievementList(achievements);
                }
            }
        });

    }

    private void initIV() {
        IVRecyclerView=AIVView.findViewById(R.id.IV_rv);
        addIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkillsDialogFragment dialog = new SkillsDialogFragment();
                dialog.setTargetFragment(AIVFragment.this, REQ_CODE_FOR_IV_CREATE);
                Bundle bundle = new Bundle();
                bundle.putInt(IV_PROFILE_ID, profileId);
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), "IVDialog");
            }
        });

    }

    private void initAchievement() {
        achievementRecyclerView=AIVView.findViewById(R.id.achievement_rv);
        addAchievement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SkillsDialogFragment dialog = new SkillsDialogFragment();
                dialog.setTargetFragment(AIVFragment.this, REQ_CODE_FOR_ACHIEVEMENT_CREATE);
                Bundle bundle = new Bundle();
                bundle.putInt(A_PROFILE_ID, profileId);
                dialog.setArguments(bundle);
                dialog.show(getFragmentManager(), "AchievementDialog");
            }
        });

    }

    @Override
    public void onAchievementClicked(View view, int achievementId, int achievementPId, String achievement) {
        Bundle bundle = new Bundle();
        bundle.putInt("achievementId", achievementId);
        bundle.putInt("achievementProfileId", achievementPId);
        bundle.putString("achievement", achievement);
        SkillsDialogFragment dialog = new SkillsDialogFragment();
        dialog.setTargetFragment(AIVFragment.this, REQ_CODE_FOR_ACHIEVEMENT_UPDATE);
        dialog.setArguments(bundle);

        dialog.show(getFragmentManager(), "AchievementUpdateDialog");

    }

    @Override
    public void onIVClicked(View view, int IVId, int IVPId, String IV) {
        Bundle bundle = new Bundle();
        bundle.putInt("IVId",IVId);
        bundle.putInt("IVProfileId",IVPId);
        bundle.putString("IV",IV);
        SkillsDialogFragment dialog = new SkillsDialogFragment();
        dialog.setTargetFragment(AIVFragment.this,REQ_CODE_FOR_IV_UPDATE);
        dialog.setArguments(bundle);
        dialog.show(getFragmentManager(),"IVUpdateDialog");
    }

    @Override
    public void sendAchievementUpdate(Achievements achievements) {
        resumeViewModel.updateAchievementForProfile(achievements);
    }

    @Override
    public void sendAchievementInsert(Achievements achievements) {
resumeViewModel.insertAchievementForProfile(achievements);
    }

    @Override
    public void sendIVUpdate(IndustrialVisit industrialVisit) {
resumeViewModel.updateIVForProfile(industrialVisit);
    }

    @Override
    public void sendIVInsert(IndustrialVisit industrialVisit) {
resumeViewModel.insertIVForProfile(industrialVisit);
    }
}