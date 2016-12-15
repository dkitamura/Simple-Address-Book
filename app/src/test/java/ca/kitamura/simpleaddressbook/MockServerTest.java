package ca.kitamura.simpleaddressbook;

import org.junit.Test;
import org.mockito.internal.util.io.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;

import ca.kitamura.simpleaddressbook.models.randomuser.RandomUserError;
import ca.kitamura.simpleaddressbook.models.randomuser.RandomUserResponse;
import ca.kitamura.simpleaddressbook.networking.RandomUserApi;
import okhttp3.ResponseBody;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by Darren on 2016-12-14.
 */

public class MockServerTest {

    @Test
    public void validResponse() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        InputStream jsonStream = ClassLoader.getSystemResourceAsStream("validrandomuser.json");
        mockWebServer.enqueue(new MockResponse().setResponseCode(200).setBody(IOUtil.readLines(jsonStream).toString()));
        RandomUserApi userApi = new Retrofit.Builder().baseUrl(mockWebServer.url("").toString()).addConverterFactory(GsonConverterFactory.create()).build().create(RandomUserApi.class);

        Call<RandomUserResponse> randomUserResponseCall = userApi.getRandomUsers();
        randomUserResponseCall.enqueue(new Callback<RandomUserResponse>() {
            @Override
            public void onResponse(Call<RandomUserResponse> call, Response<RandomUserResponse> response) {
                assertTrue(response.code() == 201);
                assertTrue(response.body() != null);
            }

            @Override
            public void onFailure(Call<RandomUserResponse> call, Throwable t) {

            }
        });
        mockWebServer.shutdown();
    }

    /*
        This test will fail -- haven't had to mock HTTP requests before but not sure how to get it to spit the proper body instead of just "Server Error"
     */
    @Test
    public void invalidResponse() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        InputStream jsonStream = ClassLoader.getSystemResourceAsStream("invalidrandomuser.json");
        mockWebServer.enqueue(new MockResponse().setResponseCode(500).setBody(IOUtil.readLines(jsonStream).toString()));


        final Retrofit retrofit = new Retrofit.Builder().baseUrl(mockWebServer.url("").toString()).addConverterFactory(GsonConverterFactory.create()).build();
        RandomUserApi userApi = retrofit.create(RandomUserApi.class);

        Call<RandomUserResponse> randomUserResponseCall = userApi.getRandomUsers();
        Response<RandomUserResponse> response = randomUserResponseCall.execute();
        assertTrue(response.code() == 500);
        Converter<ResponseBody, RandomUserError> errorConverter = retrofit.responseBodyConverter(RandomUserError.class, new Annotation[0]);
        try {
            RandomUserError randomUserError = errorConverter.convert(response.errorBody());
            assertTrue(!randomUserError.getError().isEmpty());
            System.out.print(randomUserError.getError());

        } catch (IOException e) {
            e.printStackTrace();
            fail();
        }

        mockWebServer.shutdown();
    }
}
