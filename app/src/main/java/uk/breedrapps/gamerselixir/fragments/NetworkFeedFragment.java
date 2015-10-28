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

import com.pkmmte.pkrss.Article;
import com.pkmmte.pkrss.Callback;
import com.pkmmte.pkrss.PkRSS;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tr.xip.errorview.ErrorView;
import uk.breedrapps.gamerselixir.R;
import uk.breedrapps.gamerselixir.adapters.NetworkFeedAdapter;
import uk.breedrapps.gamerselixir.common.Constants;


/**
 * Created by edgeorge on 26/07/15.
 */
public class NetworkFeedFragment extends Fragment implements Callback, SwipeRefreshLayout.OnRefreshListener, ErrorView.RetryListener {

    @InjectView(R.id.feed_recycler_view)
    RecyclerView recyclerView;
    @InjectView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @InjectView(R.id.error_view)
    ErrorView errorView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        ButterKnife.inject(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setRefreshing(true);
        errorView.setOnRetryListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getFeed();
    }

    public void getFeed() {
        errorView.setVisibility(View.GONE);
        refreshLayout.setVisibility(View.VISIBLE);
        PkRSS.with(getActivity()).load(Constants.APP_RSS_FEED_URL).callback(this).page(0).async();
    }

    @Override
    public void OnPreLoad() {
        if(getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    refreshLayout.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void OnLoaded(final List<Article> list) {
        if(getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setAdapter(new NetworkFeedAdapter(getActivity(), list));
                    onItemsLoaded();
                }
            });
        }

    }

    @Override
    public void OnLoadFailed() {
        if(getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    errorView.setVisibility(View.VISIBLE);
                    refreshLayout.setVisibility(View.GONE);
                    onItemsLoaded();
                }
            });
        }
    }

    @Override
    public void onRefresh() {
        getFeed();
    }

    @Override
    public void onRetry() {
        getFeed();
    }

    public void onItemsLoaded(){
        if(refreshLayout.isRefreshing())
            refreshLayout.setRefreshing(false);
    }

}
