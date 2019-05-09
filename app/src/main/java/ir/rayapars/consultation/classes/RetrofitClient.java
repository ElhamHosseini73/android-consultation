package ir.rayapars.consultation.classes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitClient {

    @FormUrlEncoded
    @POST("adviser_list")
    Call<AdviserList> AdviserList(@Field("KEY") String KEY, @Field("page") String page, @Field("pre_page") String prePage, @Field("simple") String simple, @Field("cat") String cat, @Field("city") String city);

    @FormUrlEncoded
    @POST("adviser_details")
    Call<AdviserDetails> adviserDetails(@Field("KEY") String KEY, @Field("adv_id") String advId, @Field("info") String info);

    @FormUrlEncoded
    @POST("appointment")
    Call<AboutMessage> appointment(@Field("KEY") String KEY, @Field("id") String id, @Field("date") String date, @Field("type") String type, @Field("dtype") String dtype, @Field("fname") String fname, @Field("lname") String lname, @Field("mobile") String mobile, @Field("email") String email);

    @FormUrlEncoded
    @POST("report")
    Call<AboutMessage> report(@Field("KEY") String KEY, @Field("first_name") String first_name, @Field("last_name") String last_name, @Field("text") String text, @Field("mobile") String mobile, @Field("adviser_name") String adviser_name);

    @FormUrlEncoded
    @POST("suggest")
    Call<AboutMessage> suggest(@Field("KEY") String KEY, @Field("text") String text, @Field("type") String type);

    @FormUrlEncoded
    @POST("get_cities")
    Call<cities> getCities(@Field("KEY") String KEY);

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
    Call<AboutMessage> aboutUs(@Field("KEY") String KEY, @Field("terms") String terms, @Field("contact") String contact);

}
