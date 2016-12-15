package ca.kitamura.simpleaddressbook.models.randomuser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Darren on 2016-12-14.
 */

public class RandomUserError {

    @Expose
    @SerializedName("error")
    private String error;

    public RandomUserError() {
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
