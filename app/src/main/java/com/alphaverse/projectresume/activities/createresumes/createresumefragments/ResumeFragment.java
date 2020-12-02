package com.alphaverse.projectresume.activities.createresumes.createresumefragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.model.Achievements;
import com.alphaverse.projectresume.model.IndustrialVisit;
import com.alphaverse.projectresume.model.PersonalDetails;
import com.alphaverse.projectresume.model.Projects;
import com.alphaverse.projectresume.model.Qualification;
import com.alphaverse.projectresume.model.Resume;
import com.alphaverse.projectresume.model.Skills;
import com.alphaverse.projectresume.model.Strengths;
import com.alphaverse.projectresume.viewmodel.ResumeViewModel;

import java.util.List;


public class ResumeFragment extends Fragment {
    public static final String TAG = "ResumeFragment";
    private View resumeView;
    private Bundle bundle;
    private Context context;
    private int profileId;
    private String resumeTemplate;
    private WebView resumeWebView;
    private Resume resume;
    private ResumeViewModel resumeViewModel;
    private PersonalDetails resumePersonalDetails;
    private String name;
    private ProgressBar progressBar;


    public ResumeFragment(Context context) {
        this.context = context;
    }

    public ResumeFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
        profileId = bundle.getInt(PreviewActionListener.ACTION_KEY_FOR_PROFILE_ID);
        resumeTemplate = bundle.getString(PreviewActionListener.ACTION_KEY_FOR_PREVIEW);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private void setResumeDetails() {

        resumeViewModel.getPersonalDetailForRespectiveProfile(profileId).observe(this, new Observer<PersonalDetails>() {
            @Override
            public void onChanged(PersonalDetails personalDetails) {
                if (personalDetails != null) {
                    resume.setResumePersonalDetails(personalDetails);
                }
            }
        });

        resumeViewModel.getQualificationOfTheProfile(profileId).observe(this, new Observer<List<Qualification>>() {
            @Override
            public void onChanged(List<Qualification> qualifications) {
                if (qualifications != null) {
                    resume.setResumeQualification(qualifications);
                }
            }
        });
        resumeViewModel.getskillsOfTheProfile(profileId).observe(this, new Observer<List<Skills>>() {
            @Override
            public void onChanged(List<Skills> skills) {
                resume.setResumeSkills(skills);
            }
        });
        resumeViewModel.getStrengthOfTheProfile(profileId).observe(this, new Observer<List<Strengths>>() {
            @Override
            public void onChanged(List<Strengths> strengths) {
                resume.setResumeStrength(strengths);
            }
        });
        resumeViewModel.getProjectsOfTheProfile(profileId).observe(this, new Observer<List<Projects>>() {
            @Override
            public void onChanged(List<Projects> projectsList) {
                resume.setResumeProjects(projectsList);
            }
        });
        resumeViewModel.getAchievementOfTheProfile(profileId).observe(this, new Observer<List<Achievements>>() {
            @Override
            public void onChanged(List<Achievements> achievements) {
                resume.setResumeAchievements(achievements);
            }
        });
        resumeViewModel.getIVOfTheProfile(profileId).observe(this, new Observer<List<IndustrialVisit>>() {
            @Override
            public void onChanged(List<IndustrialVisit> industrialVisits) {
                resume.setResumeIndustrialVisits(industrialVisits);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        resumeView = inflater.inflate(R.layout.fragment_resume, container, false);
        resumeWebView = resumeView.findViewById(R.id.resume_web_view);
        resumeViewModel = ViewModelProviders.of(this).get(ResumeViewModel.class);
        resume = new Resume(profileId, context);
        setResumeDetails();
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                initResume();
            }
        }, 1500);
        return resumeView;
    }

    @SuppressLint("DefaultLocale")
    private void initResume() {
        switch (resumeTemplate) {
            case "resOne":
                Toast.makeText(context, "Resume One", Toast.LENGTH_SHORT).show();
                break;
            case "resTwo":
                Toast.makeText(context, "Resume Two", Toast.LENGTH_SHORT).show();
                break;
            case "resThree":
                Toast.makeText(context, "Resume Three", Toast.LENGTH_SHORT).show();
                break;
            case "resFour":
                Toast.makeText(context, "Resume Four", Toast.LENGTH_SHORT).show();
                break;
            case "resFive":
                Toast.makeText(context, "Resume Five", Toast.LENGTH_SHORT).show();
                break;
            case "resSix":
                Toast.makeText(context, "Resume Six", Toast.LENGTH_SHORT).show();
                break;
        }

        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<!DOCTYPE html>\n" +
                "                       <html lang=\"en\">\n" +
                "                        <head>\n" +
                "                          <style >\n" +
                "                           @import url(https://fonts.googleapis.com/css?family=Work+Sans:200,300,400,500);\n" +
                "                      * {\n" +
                "                          box-sizing: border-box;\n" +
                "                           font-family: \\\"Work Sans\\\", sans-serif;\n" +
                "                       }\n" +
                "                       h1,h2,h3,h4,h5,p{\n" +
                "                        color: #444;\n" +
                "                       }\n" +
                "                       h1 {\n" +
                "                         font-size: 30px;\n" +
                "                          font-weight: 400;\n" +
                "                           margin: 0;\n" +
                "                       }\n" +
                "                       h2 {\n" +
                "                           font-size: 20px;\n" +
                "                           font-weight: 400;\n" +
                "                           margin: 0;\n" +
                "                       }\n" +
                "                       h3 {\n" +
                "                           font-size: 15px;\n" +
                "                           font-weight: 400;\n" +
                "                           margin: 0 0 5px 0;\n" +
                "                       }\n" +
                "                       h4 {\n" +
                "                       font-size: 15px;\n" +
                "                       font-weight: 300;\n" +
                "                       margin: 0 0 5px 0;\n" +
                "                       }\n" +
                "                       p {\n" +
                "                           font-size: 13px;\n" +
                "                           font-weight: 300;\n" +
                "                           line-height: 120%;\n" +
                "                          margin: 0 0 5px 0;\n" +
                "                       }\n" +
                "                       p > span{\n" +
                "                       color: #444;\n" +
                "                       font-weight: 400;\n" +
                "                       font-size:13px;\n" +
                "                       }\n" +
                "                       li {\n" +
                "                        width: 100%;\n" +
                "                       font-size: 14px;\n" +
                "                       font-weight: 300;\n" +
                "                      color: #444;\n" +
                "                       }\n" +
                "                       .container{\n" +
                "                       width:100%;\n" +
                "                       padding-top:4%;\n" +
                "                        padding-bottom:4%;\n" +
                "                        padding-right:7%;\n" +
                "                      padding-left:7%;\n" +
                "                       margin-right:auto;\n" +
                "                       margin-left:auto;\n" +
                "                       }\n" +
                "                       .heading{\n" +
                "                       padding-top: 10px;\n" +
                "                       padding-bottom:10px;\n" +
                "                       border-bottom: 2px solid rgb(173, 168, 168);\n" +
                "                       }\n" +
                "                       .row{\n" +
                "                        padding: 10px 0;\n" +
                "                       flex-direction:row;\n" +
                "                       }\n" +
                "                       a{\n" +
                "                       text-decoration: none;\n" +
                "                       padding-left: 10px;\n" +
                "                       }\n" +

                "                        </style>\n" +
                "                       <title>Resume</title>\n" +
                "                       </head>\n" +
                "                       <body>\n" +
                "                      <div class=\"container\">\n" +
                "                         <div class=\"heading\">\n" +
                "                       <h1>" + resume.getResumePersonalDetails().getFullName() + "</h1>\n" +
                "                        </div>\n" +
                "                        <div class=\"row\">\n" +
                "                                 <p><span>D.O.B</span><a href=\"#\">" + resume.getResumePersonalDetails().getDate() + "</a></p>\n" +
                "                                 <p><span>Address</span><a href=\"maps.google.com\">" + resume.getResumePersonalDetails().getAddress() + "</a></p>\n" +
                "                               <p><span>Phone No</span><a href=\"tel:9150137932\">" + resume.getResumePersonalDetails().getPhoneNumber() + "</a></p>\n" +
                "                                <p><span>Email ID</span><a href=\"mailto:hariprasad@gmail.com\">" + resume.getResumePersonalDetails().getEmailId() + "</a></p>\n" +
                "                                 <p><span>LinkedIn ID</span><a href=\"https://linkedin.com\">" + resume.getResumePersonalDetails().getLinkedIn() + "</a></p>\n" +
                "                             </div>");

        if (!resume.getResumeQualification().isEmpty()) {
            htmlContent.append(" <div class=\"heading\">\n" +
                    "            <h2>Academic credentials</h2>\n" +
                    "        </div>");
            for (Qualification qualification : resume.getResumeQualification()) {
                htmlContent.append("<div class=\"row\">\n" +
                        "            <div>\n" +
                        "                <h3>" + qualification.getDegree() + "</h3>\n" +
                        "                <h4>" + qualification.getInstitution() + "- <span>" + qualification.getPercentage() + "</span></h4>\n" +
                        "                <p>" + qualification.getYOP() + "</p>\n" +
                        "            </div>\n" +
                        "        </div>");
            }
            if (!resume.getResumeSkills().isEmpty()) {
                htmlContent.append("<div class=\"heading\">\n" +
                        "            <h2>Skills</h2>\n" +
                        "        </div>");
                for (Skills skills : resume.getResumeSkills()) {
                    htmlContent.append("<div class=\"row\">\n" +
                            "            <div>\n" +
                            "                <ul>\n" +
                            "                    <li>" + skills.getSkill() + "</li>\n" +
                            "                </ul>\n" +
                            "            </div>\n" +
                            "        </div>");
                }
                if (!resume.getResumeStrength().isEmpty()) {
                    htmlContent.append("<div class=\"heading\">\n" +
                            "            <h2>Strengths</h2>\n" +
                            "        </div>");
                    for (Strengths strengths : resume.getResumeStrength()) {
                        htmlContent.append("<div class=\"row\">\n" +
                                "            <div>\n" +
                                "                <ul>\n" +
                                "                    <li>" + strengths.getStrength() + "</li>\n" +
                                "                </ul>\n" +
                                "            </div>\n" +
                                "        </div>");
                    }

                    if (!resume.getResumeProjects().isEmpty()) {
                        htmlContent.append("<div class=\"heading\">\n" +
                                "            <h2>Projects</h2>\n" +
                                "        </div>");
                        for (Projects projects : resume.getResumeProjects()) {
                            htmlContent.append(" <div class=\"row\">\n" +
                                    "            <h3>" + projects.getProjectName() + "</h3>\n" +
                                    "            <h4>" + projects.getProjectDescription() + "</h4>\n" +
                                    "<h5>" + projects.getProjectDuration() + "</h5>\n" +
                                    "        </div>\n" +
                                    "        ");
                        }
                        if (!resume.getResumeAchievements().isEmpty()) {
                            htmlContent.append("<div class=\"heading\">\n" +
                                    "            <h2>Achievements</h2>\n" +
                                    "        </div>");
                            for (Achievements achievements : resume.getResumeAchievements()) {
                                htmlContent.append("<div class=\"row\">\n" +
                                        "            <div>\n" +
                                        "                <ul>\n" +
                                        "                    <li>" + achievements.getAchievement() + "</li>\n" +
                                        "                </ul>\n" +
                                        "            </div>\n" +
                                        "        </div>");
                            }

                        }


                        resumeWebView.loadDataWithBaseURL(null, htmlContent.toString(), "text/html", "utf-8", null);
                    }


                }
            }
        }
    }
}

