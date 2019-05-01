package ir.rayapars.consultation.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Advisers {

    public String image;
    @SerializedName("group_name")
    public String groupName;
    @SerializedName("reg_date")
    public String regDate;
    public String job;
    public String text;
    public String active;
    public String rate;
    public String name;
    public String id;
    public String facebook;
    public String instagram;
    public String site;
    public String telegram;
    public String email;
    public String twitter;
    public List<Education> education;

}
