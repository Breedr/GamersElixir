package uk.breedrapps.gamerselixir.twitch.models;

import com.google.gson.annotations.Expose;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by edgeorge on 26/07/15.
 */
public class TwitchImage {
    @Expose
    private String size600;
    @Expose
    private String size300;
    @Expose
    private String size150;
    @Expose
    private String size70;
    @Expose
    private String size50;
    @Expose
    private String size28;

    /**
     *
     * @return
     * The size600
     */
    public String getSize600() {
        return size600;
    }

    /**
     *
     * @param size600
     * The size600
     */
    public void setSize600(String size600) {
        this.size600 = size600;
    }

    /**
     *
     * @return
     * The size300
     */
    public String getSize300() {
        return size300;
    }

    /**
     *
     * @param size300
     * The size300
     */
    public void setSize300(String size300) {
        this.size300 = size300;
    }

    /**
     *
     * @return
     * The size150
     */
    public String getSize150() {
        return size150;
    }

    /**
     *
     * @param size150
     * The size150
     */
    public void setSize150(String size150) {
        this.size150 = size150;
    }

    /**
     *
     * @return
     * The size70
     */
    public String getSize70() {
        return size70;
    }

    /**
     *
     * @param size70
     * The size70
     */
    public void setSize70(String size70) {
        this.size70 = size70;
    }

    /**
     *
     * @return
     * The size50
     */
    public String getSize50() {
        return size50;
    }

    /**
     *
     * @param size50
     * The size50
     */
    public void setSize50(String size50) {
        this.size50 = size50;
    }

    /**
     *
     * @return
     * The size28
     */
    public String getSize28() {
        return size28;
    }

    /**
     *
     * @param size28
     * The size28
     */
    public void setSize28(String size28) {
        this.size28 = size28;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
