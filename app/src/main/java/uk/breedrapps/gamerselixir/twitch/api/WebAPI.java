package uk.breedrapps.gamerselixir.twitch.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import uk.breedrapps.gamerselixir.twitch.models.TwitchTeam;
import uk.breedrapps.gamerselixir.twitch.models.TwitchTeamChannels;


/**
 * Created by edgeorge on 26/07/15.
 */
public interface WebAPI {

    @GET("/team/{team}/all_channels.json")
    void getTeamMembers(
            @Path("team") String team,
            Callback<TwitchTeamChannels> cb
    );

    @GET("/team/{team}/live_channels.json")
    void getLiveTeamMembers(
            @Path("team") String team,
            Callback<TwitchTeam> cb
    );

}
