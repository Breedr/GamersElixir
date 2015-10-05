package uk.breedrapps.gamerselixir.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import tr.xip.errorview.ErrorView;
import uk.breedrapps.gamerselixir.R;
import uk.breedrapps.gamerselixir.adapters.StreamerInfoAdapter;
import uk.breedrapps.gamerselixir.common.Constants;
import uk.breedrapps.gamerselixir.twitch.TwitchAPI;
import uk.breedrapps.gamerselixir.twitch.models.TwitchTeamChannels;


/**
 * Created by edgeorge on 26/07/15.
 */
public class StreamersFragment extends Fragment implements Callback<TwitchTeamChannels>, SwipeRefreshLayout.OnRefreshListener, ErrorView.RetryListener {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ErrorView errorView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.feed_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setOnRefreshListener(this);
        errorView = (ErrorView) view.findViewById(R.id.error_view);
        errorView.setOnRetryListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadItems();
    }


    @Override
    public void success(TwitchTeamChannels twitchTeamChannels, Response response) {
        recyclerView.setAdapter(new StreamerInfoAdapter(getActivity(), twitchTeamChannels.getAllChannels()));
        onItemsLoadComplete();
    }

    @Override
    public void failure(RetrofitError error) {
        errorView.setVisibility(View.VISIBLE);
        refreshLayout.setVisibility(View.GONE);
        onItemsLoadComplete();
    }

    @Override
    public void onRefresh() {
        loadItems();
    }

    @Override
    public void onRetry() {
        loadItems();
    }

    private void loadItems() {
        errorView.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(true);
        TwitchAPI.getWebAPI().getTeamMembers(Constants.APP_STREAM_TEAM, this);
    }

    private void onItemsLoadComplete() {
        refreshLayout.setRefreshing(false);
    }


}
