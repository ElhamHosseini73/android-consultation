package ir.rayapars.consultation.classes;

import android.icu.text.SymbolTable;

import com.google.gson.annotations.SerializedName;

public class Advisers {

    public String image;
    @SerializedName("group_name")
    public String groupName;
    @SerializedName("reg_date")
    public String regDate;
    public String job;
    public String active;
    public String rate;
    public String name;

}
