package uk.breedrapps.gamerselixir.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.pkrss.Article;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Period;

import java.text.MessageFormat;
import java.util.List;

import uk.breedrapps.gamerselixir.PostActivity;
import uk.breedrapps.gamerselixir.R;


/**
 * Created by edgeorge on 28/01/15.
 */
public class NetworkFeedAdapter extends RecyclerView.Adapter<NetworkFeedAdapter.ViewHolder> {
    private List<Article> articleList;
    private Activity activity;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTitleText;
        public TextView mCommentText;
        public TextView mCreatedDate;
        public ImageView mMainImage;
        public ImageView mCommentImage;
        public TextView mFeaturedView;
        public ViewHolder(View v) {
            super(v);
            mTitleText = (TextView) v.findViewById(R.id.info_text);
            mCommentText = (TextView) v.findViewById(R.id.comment_number_text);
            mCreatedDate = (TextView) v.findViewById(R.id.created_text);
            mMainImage = (ImageView) v.findViewById(R.id.feature_image);
            mCommentImage = (ImageView) v.findViewById(R.id.comment_image);
            mFeaturedView = (TextView) v.findViewById(R.id.featured_layout);
            v.findViewById(R.id.informationView).setVisibility(View.INVISIBLE);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NetworkFeedAdapter(Activity activity, List<Article> articleList) {
        this.activity = activity;
        this.articleList = articleList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NetworkFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.feed_card_view, parent, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Article article = articleList.get(position);

        holder.mFeaturedView.setText((article.getTags() != null ? article.getTags().get(0) : activity.getString(R.string.app_name)));
        holder.mTitleText.setText(article.getTitle());
        holder.mTitleText.setSelected(true);
        Uri image = article.getImage();
        String url = image.toString().replace("-150x150", "");

        if(url.isEmpty()){
            Picasso.with(activity).load(R.drawable.feed_default).fit().into(holder.mMainImage);
        }else{
            Picasso.with(activity).load(url).error(R.drawable.feed_default).fit().into(holder.mMainImage);
        }

        holder.mMainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context ctx = v.getContext();
                Intent intent = new Intent(ctx, PostActivity.class);
                intent.putExtra("article", article);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(activity, holder.mMainImage, "post");
                ctx.startActivity(intent, options.toBundle());
            }
        });

        holder.mCommentText.setText(String.format(activity.getString(R.string.posted_by), article.getAuthor()));

        DateTime articleTime = new DateTime(article.getDate());
        DateTime now = new DateTime();
        Period period = new Period(articleTime, now);

        holder.mCreatedDate.setText(MessageFormat.format(activity.getString(R.string.day_number), period.getDays()));
        
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

}
