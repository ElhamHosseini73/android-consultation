package ir.rayapars.consultation.classes;

import com.orm.SugarRecord;

public class UserInfo extends SugarRecord {

    public String uid;
    public String MDU;
    public String active;
    public String image_url;
    public String first_name;
    public String last_name;
    public String mobile;
    public String email;
    public String address;
    public String reg_date;

    public UserInfo() {

    }
}
