package com.alphaverse.projectresume.data;

import com.alphaverse.projectresume.model.Preview;

import java.util.ArrayList;


public class PreviewData {
    private String[] previewNames = {"resone", "restwo", "resthree", "resfour", "resfive", "ressix", "resseven"};

    public ArrayList<Preview> previewArrayList() {
        ArrayList<Preview> previews = new ArrayList<>();
        for (int i = 0; i < previewNames.length; i++) {
            Preview preview = new Preview(previewNames[i], previewNames[i].toLowerCase());
            previews.add(preview);
        }
        return previews;
    }
}
