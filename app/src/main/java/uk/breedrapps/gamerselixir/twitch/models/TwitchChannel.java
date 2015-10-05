package uk.breedrapps.gamerselixir.twitch.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by edgeorge on 26/07/15.
 */
public class TwitchChannel implements Comparable {

    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String title;
    @SerializedName("meta_game")
    @Expose
    private String metaGame;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @Expose
    private String link;
    @Expose
    private TwitchImage image;
    @Expose
    private String status;
    @SerializedName("followers_count")
    @Expose
    private Long followersCount;
    @SerializedName("total_views")
    @Expose
    private Long totalViews;
    @SerializedName("current_viewers")
    @Expose
    private Long currentViewers;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The metaGame
     */
    public String getMetaGame() {
        return metaGame;
    }

    /**
     *
     * @param metaGame
     * The meta_game
     */
    public void setMetaGame(String metaGame) {
        this.metaGame = metaGame;
    }

    /**
     *
     * @return
     * The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     *
     * @param displayName
     * The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     *
     * @return
     * The link
     */
    public String getLink() {
        return link;
    }

    /**
     *
     * @param link
     * The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     *
     * @return
     * The image
     */
    public TwitchImage getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(TwitchImage image) {
        this.image = image;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The followersCount
     */
    public Long getFollowersCount() {
        return followersCount;
    }

    /**
     *
     * @param followersCount
     * The followers_count
     */
    public void setFollowersCount(Long followersCount) {
        this.followersCount = followersCount;
    }

    /**
     *
     * @return
     * The totalViews
     */
    public Long getTotalViews() {
        return totalViews;
    }

    /**
     *
     * @param totalViews
     * The total_views
     */
    public void setTotalViews(Long totalViews) {
        this.totalViews = totalViews;
    }

    /**
     *
     * @return
     * The currentViewers
     */
    public Long getCurrentViewers() {
        return currentViewers;
    }

    /**
     *
     * @param currentViewers
     * The current_viewers
     */
    public void setCurrentViewers(Long currentViewers) {
        this.currentViewers = currentViewers;
    }

    public boolean isLive(){
        return getStatus().equals("live");
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int compareTo(@NonNull Object another) {
        return status.compareTo(((TwitchChannel)another).getStatus());
    }
}