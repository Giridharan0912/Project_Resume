package com.alphaverse.projectresume.activities.editresume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.activities.createresumes.CreateResumeActivity;
import com.alphaverse.projectresume.activities.editresume.editresumefragments.AIVFragment;
import com.alphaverse.projectresume.activities.editresume.editresumefragments.AcademicFragment;
import com.alphaverse.projectresume.activities.editresume.editresumefragments.CareerObjectiveFragment;
import com.alphaverse.projectresume.activities.editresume.editresumefragments.PersonalInfoFragment;
import com.alphaverse.projectresume.activities.editresume.editresumefragments.ProjectFragment;
import com.alphaverse.projectresume.activities.editresume.editresumefragments.SkillsFragment;
import com.alphaverse.projectresume.activities.editresume.editresumefragments.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import static com.alphaverse.projectresume.activities.profilelist.ProfileListAdapter.PROFILE_ID;
import static com.alphaverse.projectresume.activities.profilelist.ProfileListAdapter.PROFILE_NAME;

public class EditResumeActivity extends AppCompatActivity {
    private static final String P_ID ="pId" ;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Bundle bundle;
    private int profileId;
    private String profileName;
    private FragmentManager manager;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle=getIntent().getExtras();
        profileId= bundle.getInt(PROFILE_ID);
        profileName=bundle.getString(PROFILE_NAME);
        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

        setContentView(R.layout.activity_edit_resume);
        manager=getSupportFragmentManager();
        toolbar=findViewById(R.id.toolbar_edit_resume);
        viewPager=findViewById(R.id.viewpager_edit_resume);
        tabLayout=findViewById(R.id.tablayout_edit_resume);
        setSupportActionBar(toolbar);
        setUpViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.preview_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.preview:
                Toast.makeText(EditResumeActivity.this,"Preview",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(EditResumeActivity.this, CreateResumeActivity.class);
                intent.putExtra("ProfileIdValue",profileId);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    private void setUpViewPager(ViewPager viewPager){
        ViewPagerAdapter viewPagerAdapter=new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        PersonalInfoFragment personalInfoFragment=new PersonalInfoFragment(getApplicationContext(),profileId,manager);
        viewPagerAdapter.addFragemnt(personalInfoFragment,"PERSONAL INFO");
        AcademicFragment academicFragment=new AcademicFragment(getApplicationContext(),profileId);
        viewPagerAdapter.addFragemnt(academicFragment,"ACADEMIC CREDENTIALS");
        SkillsFragment skillsFragment=new SkillsFragment(profileId,getApplicationContext());
        viewPagerAdapter.addFragemnt(skillsFragment,"SKILLS");
        ProjectFragment projectFragment=new ProjectFragment(getApplicationContext(),profileId);
        viewPagerAdapter.addFragemnt(projectFragment,"PROJECT DETAILS");
        AIVFragment aivFragment=new AIVFragment(getApplicationContext(),profileId);
        viewPagerAdapter.addFragemnt(aivFragment,"ACHIEVEMENTS");
        CareerObjectiveFragment careerObjectiveFragment=new CareerObjectiveFragment();
        viewPagerAdapter.addFragemnt(careerObjectiveFragment,"CAREER OBJECTIVE");
        viewPager.setAdapter(viewPagerAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportActionBar().setTitle(profileName);
    }
}