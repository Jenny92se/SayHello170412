package ideallions.sayhello;

public class SingleItem {
    private String ncodeStr;
    private int iconDrawable;
    private String titleStr;

    public void setNcode(String ncode) {
        ncodeStr = ncode;
    }

    public void setIcon(int icon) {
        iconDrawable = icon;
    }

    public void setTitle(String title) {
        titleStr = title;
    }

    public String getNcode() {
        return this.ncodeStr;
    }

    public int getIcon() {
        return this.iconDrawable;
    }

    public String getTitle() {
        return this.titleStr;
    }
}