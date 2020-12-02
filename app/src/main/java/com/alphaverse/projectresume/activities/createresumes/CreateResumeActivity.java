package com.alphaverse.projectresume.activities.createresumes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.activities.createresumes.createresumefragments.PreviewActionListener;
import com.alphaverse.projectresume.activities.createresumes.createresumefragments.PreviewFragment;
import com.alphaverse.projectresume.activities.createresumes.createresumefragments.ResumeFragment;

public class CreateResumeActivity extends AppCompatActivity implements PreviewActionListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;;
    private int profileId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_resume);
        fragmentManager=getSupportFragmentManager();
        if (savedInstanceState==null){
            profileId=getIntent().getIntExtra("ProfileIdValue",0);
        }
            initPreview();
        }

    private void initPreview() {
    fragmentTransaction=fragmentManager.beginTransaction();
        PreviewFragment previewFragment=new PreviewFragment(getApplicationContext(),profileId);
         previewFragment.setPreviewActionListener(this);
            fragmentTransaction.add(R.id.resume_container,previewFragment);
            fragmentTransaction.commit();

    }
    private void initResume(Bundle bundle){
        fragmentTransaction=fragmentManager.beginTransaction();
        ResumeFragment resumeFragment=new ResumeFragment(getApplicationContext());
        resumeFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.resume_container,resumeFragment);
        fragmentTransaction.commit();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    @Override
    public void onActionPerformed(Bundle bundle) {
        int actionPerformed = bundle.getInt(PreviewActionListener.ACTION_KEY);
        switch (actionPerformed){
            case PreviewActionListener.ACTION_VALUE:
                    initResume(bundle);
                break;
        }
    }
}
