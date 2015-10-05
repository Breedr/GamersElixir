package uk.breedrapps.gamerselixir.common;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;

import com.pkmmte.pkrss.Article;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.breedrapps.gamerselixir.R;
import uk.breedrapps.gamerselixir.classes.MenuListItem;


/**
 * Created by edgeorge on 26/07/15.
 */
public class Utils {

    private Utils(){}

    public static Intent newFacebookIntent(PackageManager pm, String url) {
        Uri uri;
        try {
            pm.getPackageInfo("com.facebook.katana", 0);
            //See: http://stackoverflow.com/a/24547437/1048340
            uri = Uri.parse("fb://facewebmodal/f?href=" + url);
        } catch (PackageManager.NameNotFoundException e) {
            uri = Uri.parse(url);
        }
        return new Intent(Intent.ACTION_VIEW, uri);
    }

    public static Intent newTwitterIntent(PackageManager pm, String url, String id) {

        Uri uri;
        try {
            pm.getPackageInfo("com.twitter.android", 0);
            uri = Uri.parse("twitter://user?user_id=" + id);
            Intent intent;
            intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            return intent;
        } catch (PackageManager.NameNotFoundException e) {
            uri = Uri.parse(url);
            return new Intent(Intent.ACTION_VIEW, uri);
        }
    }

    public static Intent openURLIntent(String url) {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(url));
    }

    public static Intent getShareDialogIntent(String url, String content, String dialogText){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, url);
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, content);
        return Intent.createChooser(intent, dialogText);
    }

    public static List<MenuListItem> getMenuListItems(){

        List<MenuListItem> temp = new ArrayList<>();

        temp.add(new MenuListItem(R.string.menu_feed, R.drawable.ic_newspaper, false));
        temp.add(new MenuListItem(R.string.menu_favourites, R.drawable.ic_star_white, false));
        temp.add(new MenuListItem(R.string.menu_streamers, R.drawable.ic_gamepad, false));
        temp.add(new MenuListItem(R.string.menu_forum, R.drawable.ic_account_multiple, true));

        return temp;
    }

    public static int dpToPx(int dp) {
        DisplayMetrics displayMetrics = Resources.getSystem().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static void trackView(String viewName){
        Map<String, String> dimensions = new HashMap<>();
        dimensions.put("view", viewName);
        //ParseAnalytics.trackEventInBackground("read", dimensions);
    }

    public static void trackArticle(Article article){
        Map<String, String> dimensions = new HashMap<>();
        dimensions.put("view", Constants.VIEW_NEWS_POST);
        dimensions.put("article_id", Integer.toString(article.getId()));
        dimensions.put("article_author", article.getAuthor());
        //.trackEventInBackground("read", dimensions);
    }

    public static void trackEvents(String... events){

        if(events == null)
            return;

        Map<String, String> dimensions = new HashMap<>();
        for(String event : events){
            dimensions.put("event", event);
        }
        //ParseAnalytics.trackEventInBackground("read", dimensions);
    }

}
