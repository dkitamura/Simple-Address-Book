package ca.kitamura.simpleaddressbook.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Darren on 2016-12-13.
 */

//Retrofit creation is costly, make it static and do it once...
public final class NetworkingService {

    private NetworkingService(){
    }

    static RandomUserApi randomUserApi;
    private static String BASE_URL = "https://randomuser.me/api/";

    private static RandomUserApi getRandomUserApi() {
        RandomUserApi retrofit = new Retrofit.Builder()
                .client(getClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RandomUserApi.class);
        return retrofit;
    }

    private static OkHttpClient getClient () {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        return client;
    }


    public static RandomUserApi getRandomUserService() {
        if(randomUserApi == null) {
            randomUserApi = getRandomUserApi();
        }
        return randomUserApi;
    }
}
