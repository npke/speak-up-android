package vn.coderschool.speakup.util;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import vn.coderschool.speakup.model.MatchingResult;
import vn.coderschool.speakup.model.User;

/**
 * Created by kenp on 23/03/2017.
 */

public interface SpeakUpApi {
    @FormUrlEncoded
    @POST("/matching")
    Call<MatchingResult> findPartner(@Field("userId") String userId);

    class Creator {
        public static final String baseApiUrl = "http://192.168.1.112:8080/api/";

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
