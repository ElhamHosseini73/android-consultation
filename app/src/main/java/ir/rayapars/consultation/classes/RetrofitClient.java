package ir.rayapars.consultation.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitClient {

    @FormUrlEncoded
    @POST("adviser_list")
    Call<AdviserList> AdviserList(@Field("KEY") String KEY, @Field("page") String page, @Field("pre_page") String prePage, @Field("simple") String simple, @Field("cat") String cat);

    @FormUrlEncoded
    @POST("adviser_details")
    Call<AdviserDetails> adviserDetails(@Field("KEY") String KEY, @Field("adv_id") String advId, @Field("info") String info);

    @FormUrlEncoded
    @POST("blog_cats")
    Call<BlogCatList> blogCatsList(@Field("KEY") String KEY);

    @FormUrlEncoded
    @POST("blog_list")
    Call<BlogList> BlogList(@Field("KEY") String KEY, @Field("section_id") String sectionId, @Field("page") String page, @Field("pre_page") String prePage);

    @FormUrlEncoded
    @POST("post_details")
    Call<PostDetails> postDetails(@Field("KEY") String KEY, @Field("id") String id);


    @FormUrlEncoded
    @POST("about_us")
    Call<AboutMessage> aboutUs(@Field("KEY") String KEY);

}
