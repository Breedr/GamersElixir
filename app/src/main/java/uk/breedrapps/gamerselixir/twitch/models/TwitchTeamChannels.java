package uk.breedrapps.gamerselixir.twitch.models;

import com.google.gson.annotations.Expose;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by edgeorge on 26/07/15.
 */
public class TwitchTeamChannels {

    @Expose
    private List<TwitchChannelWrapper> channels = new ArrayList<>();

    /**
     *
     * @return
     * The channels
     */
    public List<TwitchChannelWrapper> getChannelsWrapper() {
        return channels;
    }

    public List<TwitchChannel> getAllChannels() {
        List<TwitchChannel> channelList = new ArrayList<>();
        for(TwitchChannelWrapper wrapper : channels){
           channelList.add(wrapper.getChannel());
        }
        return channelList;
    }

    /**
     *
     * @param channels
     * The channels
     */
    public void setChannels(List<TwitchChannelWrapper> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
