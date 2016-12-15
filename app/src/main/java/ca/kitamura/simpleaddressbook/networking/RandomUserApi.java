package ca.kitamura.simpleaddressbook.networking;

import ca.kitamura.simpleaddressbook.models.randomuser.RandomUserResponse;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Darren on 2016-12-13.
 */

public interface RandomUserApi {
    //Generate 10 users and only required fields. We don't need Registered, Login, Edit, Nationality
    @GET("?results=20&inc=gender,name,location,email,dob,phone,cell,picture")
    Call<RandomUserResponse> getRandomUsers();
}
