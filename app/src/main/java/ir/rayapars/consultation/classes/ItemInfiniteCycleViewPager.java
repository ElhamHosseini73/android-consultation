package ir.rayapars.consultation.classes;

public class ItemInfiniteCycleViewPager {

    int img;
    String title;

    public ItemInfiniteCycleViewPager(int img, String title) {

        this.title = title;
        this.img = img;

    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
