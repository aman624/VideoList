package com.aman.videolist;

import android.app.ProgressDialog;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aman.videolist.adapter.VideoListAdapter;
import com.aman.videolist.model.Video;
import com.aman.videolist.loader.VideoListLoader;

import java.util.List;

/**
 * Activity to display a list a videos from a given URL
 */
public class VideoListActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<List<Video>> {

    private RecyclerView mRecyclerView;
    private VideoListAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private LinearLayout mEmptyView;
    private TextView mErrorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        // Initialize RecyclerView and adapter
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new VideoListAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // Initialize empty view which is used if the list is empty
        mEmptyView = (LinearLayout) findViewById(R.id.empty_view);
        mErrorTextView = (TextView) findViewById(R.id.error_text_view);

        // Initialize ProgressDialog to be displayed when data is loaded
        mProgressDialog = new ProgressDialog(this);

        startLoading(null);
    }

    /**
     * Start loading the data from the network
     * @param view Clicked view
     */
    public void startLoading(View view) {
        if (!Utility.isNetworkConnected(this)) {
            // If network is not connected, show emoty view
            mEmptyView.setVisibility(View.VISIBLE);
            mErrorTextView.setText(getString(R.string.error_no_network));
            return;
        }

        // Show ProgressDialog and start loading
        mProgressDialog.setMessage(getString(R.string.loading));
        mProgressDialog.show();
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<List<Video>> onCreateLoader(int id, Bundle args) {
        return new VideoListLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<Video>> loader, List<Video> data) {
        // Loading finished. Dismiss Dialog
        mProgressDialog.dismiss();

        if (data.size() == 0) {
            // Show empty view if there is no data to display
            mEmptyView.setVisibility(View.VISIBLE);
            mErrorTextView.setText(getString(R.string.error_load_failed));
            return;
        }

        // Update UI
        mEmptyView.setVisibility(View.GONE);
        mAdapter.setVideoList(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(Loader<List<Video>> loader) {
    }
}
