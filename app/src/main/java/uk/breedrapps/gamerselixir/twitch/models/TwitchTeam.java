package uk.breedrapps.gamerselixir.twitch.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by edgeorge on 26/07/15.
 */
public class TwitchTeam {

    @Expose
    private String info;
    @Expose
    private String background;
    @Expose
    private String banner;
    @Expose
    private String name;
    @SerializedName("_id")
    @Expose
    private int id;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @Expose
    private String logo;

    /**
     *
     * @return
     * The info
     */
    public String getInfo() {
        return info;
    }

    /**
     *
     * @param info
     * The info
     */
    public void setInfo(String info) {
        this.info = info;
    }


    /**
     *
     * @return
     * The background
     */
    public String getBackground() {
        return background;
    }

    /**
     *
     * @param background
     * The background
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     *
     * @return
     * The banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     *
     * @param banner
     * The banner
     */
    public void setBanner(String banner) {
        this.banner = banner;
    }

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
     * The id
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param Id
     * The _id
     */
    public void setId(Integer Id) {
        this.id = Id;
    }

    /**
     *
     * @return
     * The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     *
     * @param updatedAt
     * The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
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
     * The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     *
     * @param createdAt
     * The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     *
     * @return
     * The logo
     */
    public String getLogo() {
        return logo;
    }

    /**
     *
     * @param logo
     * The logo
     */
    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
