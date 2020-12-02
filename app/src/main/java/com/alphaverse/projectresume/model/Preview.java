package com.alphaverse.projectresume.model;

import android.content.Context;

public class Preview {
private String previewName;
private String preViewImage;

    public Preview(String previewName, String preViewImage) {
        this.previewName = previewName;
        this.preViewImage = preViewImage;
    }

    public String getPreviewName() {
        return previewName;
    }

    public void setPreviewName(String previewName) {
        this.previewName = previewName;
    }

    public int getPreViewImage(Context context) {
        return context.getResources().getIdentifier(this.previewName,"drawable",context.getPackageName());
    }

    public void setPreViewImage(String preViewImage) {
        this.preViewImage = preViewImage;
    }
}
