package com.example.elijahalpha;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OptionApi {

    @GET("options")
    Call<List<Option>> getOptions();

    @GET("options/{id}")
    Call<Option> getOptionById(@Path("id") Long id);
}
