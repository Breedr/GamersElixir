package uk.breedrapps.gamerselixir.twitch.api;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import uk.breedrapps.gamerselixir.twitch.models.TwitchTeam;

/**
 * Created by edgeorge on 26/07/15.
 */
public interface API {
    @GET("/teams/{team}")
    void getTeamInfo(
            @Path("team") String team,
            Callback<TwitchTeam> cb
    );

}
