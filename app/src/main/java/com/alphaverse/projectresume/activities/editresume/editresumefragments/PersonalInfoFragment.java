package com.alphaverse.projectresume.activities.editresume.editresumefragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.PersonalDetails;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import static com.alphaverse.projectresume.activities.profilelist.ProfileListAdapter.PROFILE_ID;


public class PersonalInfoFragment extends Fragment {
    private static final String TAG ="PersonalInfoFragment" ;
    private View personalInfoView;
    private int profileId;
    private ResumeViewModel resumeViewModel;
    private Context context;
    private TextInputEditText etName;
    private ImageButton saveBtn;
    private ImageButton dateBtn;
    private TextInputEditText etEmail;
    private TextInputEditText etPhoneNo;
    private TextInputEditText etAddress;
    private TextInputEditText etLinkedIn;
    private TextInputEditText etDOB;
    private int personalDetailId;
    private FragmentManager manager;
    private DatePickerDialog.OnDateSetListener onDateSetListener;


    public PersonalInfoFragment(Context context, int profileId, FragmentManager manager) {
        this.context = context;
        this.profileId=profileId;
        this.manager=manager;
    }

    public PersonalInfoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        personalInfoView=inflater.inflate(R.layout.fragment_personal_info,container,false);

        etName = personalInfoView.findViewById(R.id.personal_details_name);
        etEmail = personalInfoView.findViewById(R.id.personal_details_emailId);
        etPhoneNo = personalInfoView.findViewById(R.id.personal_details_phoneNo);
        etAddress = personalInfoView.findViewById(R.id.personal_details_address);
        etLinkedIn = personalInfoView.findViewById(R.id.personal_details_linkedIn);
        etDOB=personalInfoView.findViewById(R.id.personal_details_dob);
        saveBtn = personalInfoView.findViewById(R.id.personal_detail_save);
        dateBtn=personalInfoView.findViewById(R.id.personal_info_datePicker);

        resumeViewModel= ViewModelProviders.of(this).get(ResumeViewModel.class);
        resumeViewModel.getPersonalDetailForRespectiveProfile(profileId).observe(this,personalDetails -> {
            if (personalDetails!=null){
                personalDetailId=personalDetails.getPersonalDetailsId();
                etName.setText(personalDetails.getFullName());
                etEmail.setText(personalDetails.getEmailId());
                etPhoneNo.setText(personalDetails.getPhoneNumber());
                etAddress.setText(personalDetails.getAddress());
                etLinkedIn.setText(personalDetails.getLinkedIn());
                etDOB.setText(personalDetails.getDate());          }
        });
        return personalInfoView;
    }

    @Override
    public void onResume() {
        super.onResume();
        insertAndUpdatePersonalDetails();
    }

    private void insertAndUpdatePersonalDetails() {
        resumeViewModel.getPersonalDetailForRespectiveProfile(profileId).observe(this, new Observer<PersonalDetails>() {
            @Override
            public void onChanged(PersonalDetails personalDetails) {
                if (personalDetails!=null){
                    saveBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            PersonalDetails updatePersonalDetails = new PersonalDetails(personalDetailId, profileId, etName.getText().toString(), etEmail.getText().toString(), etPhoneNo.getText().toString(),
                                    etAddress.getText().toString(), etLinkedIn.getText().toString(),etDOB.getText().toString());
                            resumeViewModel.updatePersonalDetailForProfile(updatePersonalDetails);
                            Toast.makeText(context, "updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    saveBtn.setOnClickListener(v -> {
                        if (!etName.getText().toString().isEmpty() &&
                                !etPhoneNo.getText().toString().isEmpty() &&
                                !etEmail.getText().toString().isEmpty() &&
                                !etAddress.getText().toString().isEmpty() &&
                                !etLinkedIn.getText().toString().isEmpty()&&
                        !etDOB.getText().toString().isEmpty()) {
                            PersonalDetails insertPersonalDetails = new PersonalDetails(profileId, etName.getText().toString(), etEmail.getText().toString(), etPhoneNo.getText().toString(),
                                    etAddress.getText().toString(), etLinkedIn.getText().toString(),etDOB.getText().toString());
                            resumeViewModel.insertPersonalDetailForProfile(insertPersonalDetails);
                            Toast.makeText(context, "saved", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Empty field is not allowed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

}