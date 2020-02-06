package cn.imooc.imoochomework_android05.entity;

public class News {

    private String content;
    private String newsFrom;
    private int seeNumber;
    private int dianNumber;
    private int newsPic;

    public News(String content, String newsFrom, int seeNumber, int dianNumber, int newsPic) {
        this.content = content;
        this.newsFrom = newsFrom;
        this.seeNumber = seeNumber;
        this.dianNumber = dianNumber;
        this.newsPic = newsPic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsFrom() {
        return "源自：" + newsFrom;
    }

    public void setNewsFrom(String newsFrom) {
        this.newsFrom = newsFrom;
    }

    public int getSeeNumber() {
        return seeNumber;
    }

    public void setSeeNumber(int seeNumber) {
        this.seeNumber = seeNumber;
    }

    public int getDianNumber() {
        return dianNumber;
    }

    public void setDianNumber(int dianNumber) {
        this.dianNumber = dianNumber;
    }

    public int getNewsPic() {
        return newsPic;
    }

    public void setNewsPic(int newsPic) {
        this.newsPic = newsPic;
    }
}
