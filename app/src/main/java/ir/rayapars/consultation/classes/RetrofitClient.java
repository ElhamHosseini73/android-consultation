package ir.rayapars.consultation.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitClient {

    @FormUrlEncoded
    @POST("adviser_list")
    Call<AdviserList> AdviserList( @Field("KEY") String KEY ,@Field("page") String page, @Field("pre_page") String prePage);

    @FormUrlEncoded
    @POST("adviser_details")
    Call<AdviserDetails> adviserDetails( @Field("KEY") String KEY ,@Field("adv_id") String advId);

}
