package uk.breedrapps.gamerselixir.twitch;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import uk.breedrapps.gamerselixir.BuildConfig;
import uk.breedrapps.gamerselixir.twitch.api.API;
import uk.breedrapps.gamerselixir.twitch.api.WebAPI;


/**
 * Created by edgeorge on 26/07/15.
 */
public class TwitchAPI {

    private static final String TWITCH_API_URL = "https://api.twitch.tv/kraken";
    private static final String TWITCH_WEB_API_URL = "http://api.twitch.tv/api";
    private static API api;
    private static WebAPI webApi;

    public static API getAPI(){

        if(api == null){
            api = getRestAdapter(TWITCH_API_URL).create(API.class);
        }
        return api;
    }

    public static WebAPI getWebAPI(){

        if(webApi == null){
            webApi = getRestAdapter(TWITCH_WEB_API_URL).create(WebAPI.class);
        }

        return webApi;
    }

    private static RestAdapter getRestAdapter(String url){
        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("Accept", "application/vnd.twitchtv.v3+json");
                request.addHeader("Client-ID", BuildConfig.APPLICATION_ID);
            }
        };

        return new RestAdapter.Builder()
                .setEndpoint(url)
                .setRequestInterceptor(requestInterceptor).build();
    }

}
