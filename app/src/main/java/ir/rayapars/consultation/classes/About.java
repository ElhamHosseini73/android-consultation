package ir.rayapars.consultation.classes;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class About {

    public String title;
    public String about;
    public String image;
    public String instagram;
    public String email;
    public String telegram;
    public String fb;
    public String twitter;
    public String linkedin;
    public String pinterest;
    public String terms;
    @SerializedName("contact_info")
    public List<Education> contactInfo;
}
