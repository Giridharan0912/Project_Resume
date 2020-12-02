package com.alphaverse.projectresume.activities.profilelist.profilelistfragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Profile;
import com.google.android.material.textfield.TextInputEditText;

public class ProfileListDialogFragment extends DialogFragment {
    private static final String TAG = "ProfileListDialogFragment";
    public InsertProfile insertProfile;

    public interface InsertProfile {
        void sendInsert(Profile profile);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            insertProfile = (InsertProfile) getTargetFragment();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_profile_popup, container, false);
        TextInputEditText etProfilename=view.findViewById(R.id.profile_name_et);
        ImageButton saveProfile=view.findViewById(R.id.save_profile_iv);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            saveProfile.setOnClickListener(v -> {
                Profile profile = new Profile(etProfilename.getText().toString());
                insertProfile.sendInsert(profile);
                getDialog().dismiss();
            });
        return view;
    }
}
