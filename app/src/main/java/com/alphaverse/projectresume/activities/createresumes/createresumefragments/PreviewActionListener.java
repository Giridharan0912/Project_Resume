package com.alphaverse.projectresume.activities.createresumes.createresumefragments;

import android.os.Bundle;

public interface PreviewActionListener {
    String ACTION_KEY="action_key";
    int ACTION_VALUE=30;
    String ACTION_KEY_FOR_PREVIEW="KEY_SELECTED_PREVIEW";
    String  ACTION_KEY_FOR_PROFILE_ID = "KEY_SELECTED_PROFILE_ID";
    void onActionPerformed(Bundle bundle);
}
