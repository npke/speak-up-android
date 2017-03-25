package vn.coderschool.speakup.util;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.model.User;

/**
 * Created by kenp on 23/03/2017.
 */

public interface SpeakUpApi {
    @GET("/api/matching/{userId}")
    Call<MatchingResult> findPartner(@Path("userId") String userId);

    class Creator {
        public static final String baseApiUrl = "https://speak-up.herokuapp.com/";

        public static Retrofit retrofit;
        public static SpeakUpApi service;

        public synchronized static SpeakUpApi getService() {
            if (retrofit == null)
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseApiUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

            if (service == null)
                service = retrofit.create(SpeakUpApi.class);

            return service;
        }
    }
}
