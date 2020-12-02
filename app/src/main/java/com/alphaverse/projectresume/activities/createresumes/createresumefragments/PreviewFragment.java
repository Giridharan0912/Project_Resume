package com.alphaverse.projectresume.activities.createresumes.createresumefragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alphaverse.projectresume.R;
import com.alphaverse.projectresume.data.PreviewData;
import com.alphaverse.projectresume.model.Preview;

import java.util.List;

public class PreviewFragment extends Fragment implements PreviewRVAdapter.OnPreviewListener {
    private RecyclerView ResumeRecyclerView;
    private View preView;
    private PreviewRVAdapter previewRVAdapter;
    private Context context;
    private int profileId;
    PreviewActionListener previewActionListener;
    private List<Preview> previewList = new PreviewData().previewArrayList();
    private PreviewRVAdapter.OnPreviewListener onPreviewListener = this;

    public PreviewFragment(Context context, int profileId) {
        this.context = context;
        this.profileId = profileId;
    }


    public PreviewFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preView = inflater.inflate(R.layout.fragment_preview, container, false);
        ResumeRecyclerView = preView.findViewById(R.id.resume_grid_view);
        previewRVAdapter = new PreviewRVAdapter(context, previewList, onPreviewListener);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false);
        int spanCount = 2; // 3 columns
        int spacing = 50; // 50px
        ResumeRecyclerView.setLayoutManager(gridLayoutManager);
        ResumeRecyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount,spacing, true));
        ResumeRecyclerView.setAdapter(previewRVAdapter);
        return preView;
    }

    public void setPreviewActionListener(Context context) {
        this.previewActionListener = (PreviewActionListener) context;
    }

    @Override
    public void onPreviewClicked(String preview) {
        if (previewActionListener != null) {
            Bundle bundle = new Bundle();
            bundle.putInt(PreviewActionListener.ACTION_KEY, PreviewActionListener.ACTION_VALUE);
            bundle.putInt(PreviewActionListener.ACTION_KEY_FOR_PROFILE_ID, profileId);
            bundle.putString(PreviewActionListener.ACTION_KEY_FOR_PREVIEW, preview);
            previewActionListener.onActionPerformed(bundle);
        }

    }
}