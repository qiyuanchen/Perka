package com.android.flamingo.perka;

import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by Qiyuan on 2/25/2016.
 */
public interface perka_back_end {
    static final String API_VERSION = "1.5";
    @Headers("Content-Type: application/json")
    @POST("/1/communication/job/apply")
    String res(@Body request req)throws Exception;
}
