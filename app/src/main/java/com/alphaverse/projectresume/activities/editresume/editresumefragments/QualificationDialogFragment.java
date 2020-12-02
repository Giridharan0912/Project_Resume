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

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Qualification;


import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AcademicFragment.Q_PROFILE_ID;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AcademicFragment.REQ_CODE_TO_CREATE;
import static com.alphaverse.projectresume.activities.editresume.editresumefragments.AcademicFragment.REQ_CODE_UPDATE;


public class QualificationDialogFragment extends DialogFragment {

    private static final String TAG = "QualificationDialogFragment";
    public InsertQ insertQ;
    public UpdateQ updateQ;
    private int profileId;

    @SuppressLint("LongLogTag")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {

            insertQ = (InsertQ) getTargetFragment();

        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }
        try {

            updateQ = (UpdateQ) getTargetFragment();

        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage());
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_qualification_popup, container, false);
        EditText etDegree = view.findViewById(R.id.et_degree);
        EditText etInstitution = view.findViewById(R.id.et_institution);
        EditText etYOP = view.findViewById(R.id.et_YOP);
        EditText etPercentage = view.findViewById(R.id.et_percentage);
        ImageButton saveBtn = view.findViewById(R.id.save_qualification_iv);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        switch (getTargetRequestCode()) {
            case REQ_CODE_TO_CREATE:
                Bundle bundle = getArguments();
                profileId = bundle.getInt(Q_PROFILE_ID);
                saveBtn.setOnClickListener(view1 -> {
                    Qualification qualification = new Qualification(profileId, etDegree.getText().toString(), etInstitution.getText().toString(), etYOP.getText().toString()
                            , etPercentage.getText().toString());
                    insertQ.sendInsert(qualification);
                    getDialog().dismiss();
                    bundle.clear();
                });
                break;
            case REQ_CODE_UPDATE:
                Bundle bundle1 = getArguments();
                int qId = bundle1.getInt("QId");
                int qProfileId = bundle1.getInt("QProfileId");
                String degree = bundle1.getString("Degree");
                String institution = bundle1.getString("Institution");
                String YOP = bundle1.getString("YOP");
                String percentage = bundle1.getString("Percentage");
                etDegree.setText(degree);
                etInstitution.setText(institution);
                etYOP.setText(YOP);
                etPercentage.setText(percentage);
                saveBtn.setOnClickListener(view12 -> {
                    Qualification updateQualification = new Qualification(qId, qProfileId, etDegree.getText().toString(), etInstitution.getText().toString(), etYOP.getText().toString()
                            , etPercentage.getText().toString());
                    updateQ.sendUpdate(updateQualification);
                    getDialog().dismiss();
                    bundle1.clear();
                });
                break;
        }


        return view;
    }

    public interface UpdateQ {
        void sendUpdate(Qualification inputFromQualification);
    }

    public interface InsertQ {
        void sendInsert(Qualification inputFromQualification);
    }


}
