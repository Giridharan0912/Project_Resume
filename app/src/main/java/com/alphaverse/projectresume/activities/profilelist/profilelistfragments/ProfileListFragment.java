package com.alphaverse.projectresume.activities.profilelist.profilelistfragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.activities.profilelist.ProfileListAdapter;
import com.alphaverse.projectresume.model.Profile;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

public class ProfileListFragment extends Fragment implements ProfileListDialogFragment.InsertProfile {
 private View profileListView;
 private RecyclerView recyclerView;
    public static final int REQ_CODE_PROFILE_CREATE = 2;
private ProfileListAdapter profileListAdapter;
private ResumeViewModel resumeViewModel;
private Context context;
    public ProfileListFragment() {
        // Required empty public constructor
    }
    public ProfileListFragment(Context applicationContext){this.context=applicationContext;}



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileListView= inflater.inflate(R.layout.fragment_profile_list, container, false);
        resumeViewModel= ViewModelProviders.of(this).get(ResumeViewModel.class);
        recyclerView=profileListView.findViewById(R.id.profile_list_rv);
        profileListAdapter = new ProfileListAdapter(getContext());
        recyclerView.setAdapter(profileListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        initUI();
        getProfileList();
        return profileListView;
    }

    private void initUI() {
        FloatingActionButton floatingActionButton=profileListView.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(v -> {
            ProfileListDialogFragment profileListDialogFragment=new ProfileListDialogFragment();
            profileListDialogFragment.setTargetFragment(ProfileListFragment.this,REQ_CODE_PROFILE_CREATE);
            profileListDialogFragment.show(getFragmentManager(), "ProfileListDialog");
        });
    }

    private void getProfileList() {
        resumeViewModel.getAllProfilesList().observe(this, new Observer<List<Profile>>() {
            @Override
            public void onChanged(List<Profile> profiles) {
                profileListAdapter.setProfileList(profiles);
            }
        });
    }


    @Override
    public void sendInsert(Profile profile) {
        resumeViewModel.insertProfile(profile);
    }
}