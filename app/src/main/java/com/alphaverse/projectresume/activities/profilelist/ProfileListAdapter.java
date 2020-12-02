package com.alphaverse.projectresume.activities.profilelist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.activities.editresume.EditResumeActivity;
import com.alphaverse.projectresume.model.Profile;
import com.alphaverse.projectresume.model.Strengths;

import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileListViewHolder> {
    public static final String PROFILE_ID ="profileId" ;
    public static final int UPDATE_PERSONAL_DETAIL_REQ_CODE = 1;
    public static final String PROFILE_NAME = "profileName";
    private Context context;
    private LayoutInflater inflater;
    private List<Profile> profileList;

    public ProfileListAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ProfileListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.profile_item, parent, false);
        return new ProfileListViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListViewHolder holder, int position) {
        if (profileList != null) {
            Profile current = profileList.get(position);
            holder.setProfileData(current.getProfileName(), current.getProfileId());

        }
    }

    public void setProfileList(List<Profile> profiles) {
        profileList = profiles;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (profileList != null) {
            return profileList.size();
        } else return 0;
    }

    public class ProfileListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvProfileName;
        private Context context;
        private int profileId;
        private String profileName;
        private RelativeLayout relativeLayout;

        public ProfileListViewHolder(@NonNull View itemView,Context ctx) {
            super(itemView);
            this.context = ctx;
            tvProfileName=itemView.findViewById(R.id.pl_tv);
            relativeLayout=itemView.findViewById(R.id.profile_relative_layout);
            relativeLayout.setOnClickListener(this);

        }
        public void setProfileData(String profileName,int profileId) {
            tvProfileName.setText(profileName);
            this.profileId = profileId;
            this.profileName=profileName;
        }

        @Override
        public void onClick(View v) {
//                    todo: go to editResumeActivity and open personalDetailFragment to save personal details of the respective profile clicked
            Intent intent=new Intent(context, EditResumeActivity.class);
            intent.putExtra(PROFILE_ID,profileId);
            intent.putExtra(PROFILE_NAME,profileName);
            ((Activity)context).startActivity(intent);
        }
    }
}
