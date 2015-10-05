package uk.breedrapps.gamerselixir.twitch.models;

import com.google.gson.annotations.Expose;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Created by edgeorge on 26/07/15.
 */
public class TwitchChannelWrapper {


    @Expose
    private TwitchChannel channel;

    /**
     *
     * @return
     * The channel
     */
    public TwitchChannel getChannel() {
        return channel;
    }

    /**
     *
     * @param channel
     * The channel
     */
    public void setChannel(TwitchChannel channel) {
        this.channel = channel;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}

