package uk.breedrapps.gamerselixir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pkmmte.pkrss.Article;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import uk.breedrapps.gamerselixir.common.Constants;
import uk.breedrapps.gamerselixir.common.Utils;
import uk.breedrapps.gamerselixir.view.TagView;

/**
 * Created by edgeorge on 28/07/15.
 */
public class PostActivity extends AppCompatActivity {

    private Article article;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        article = intent.getParcelableExtra("article");

        if(intent.getBooleanExtra("favourite", false))
            setTheme(R.style.AppThemeTwoFavourite);

        setContentView(R.layout.activity_news_article);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton mShareFab = (FloatingActionButton) findViewById(R.id.fab_share);
        mShareFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareDialog();
            }
        });

        ((TextView) findViewById(R.id.postContent)).setText(getFormattedDescription());

        findViewById(R.id.read_full_article).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.trackEvents(Constants.READ_FULL_ARTICLE_EVENT);
                startActivity(Utils.openURLIntent(article.getSource().toString()));
            }
        });

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(article.getTitle());

        loadBackdrop((ImageView) findViewById(R.id.backdrop));
        loadTags(article.getTags());
        loadAuthorInfo();

        Utils.trackArticle(article);

    }

    private String getFormattedDescription() {
        String desc = article.getDescription();
        // If there is no description - Show no description text
        if(desc == null || desc.isEmpty()){
            return getString(R.string.no_description);
        }
        // If there is long description, cut off at ellipses
        String fin_desc = desc.substring(0, desc.indexOf("…") + 1);
        // Return full description if cut off doesnt exist
        return fin_desc.isEmpty() ? desc : fin_desc;
    }

    private void loadTags(List<String> tagStrings) {
        TagView tagView = (TagView) findViewById(R.id.tagView);

        TagView.Tag[] tags = new TagView.Tag[tagStrings.size()];
        int i = 0;
        for(String tag : article.getTags()){
            tags[i++] = new TagView.Tag(tag, ContextCompat.getColor(this, R.color.app_primary));
        }
        tagView.setTags(tags ," ");
    }

    private void loadAuthorInfo() {
        TextView authorInfo = (TextView) findViewById(R.id.author_info);
        SimpleDateFormat formatter = new SimpleDateFormat(getString(R.string.post_date_format), Locale.getDefault());
        String formattedDate = formatter.format(new Date(article.getDate()));
        authorInfo.setText(String.format(getString(R.string.written_by), article.getAuthor(), formattedDate));
    }

    private void loadBackdrop(ImageView imageView) {
        String imageUrl = article.getImage().toString();
        if(imageUrl.isEmpty()){
            Picasso.with(this).load(R.drawable.feed_default).into(imageView);
        }else{
            Picasso.with(this).load(imageUrl)
                    .error(R.drawable.feed_default).into(imageView);
        }
    }

    private void shareDialog(){
        startActivity(
                Utils.getShareDialogIntent(
                        article.getSource().toString(),
                        getString(R.string.share_prefix) + " " + article.getTitle(),
                        getString(R.string.share_post)
                ));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
