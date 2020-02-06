package cn.imooc.imoochomework_android03;

public class Book {

    private String bookName;
    private String bookCategory;
    private int fitAge;
    private int bookPic;
    private boolean isHistory;
    private boolean isSuspense;
    private boolean isLiterary;

    public Book(String bookName, String bookCategory, int fitAge, int bookPic, boolean isHistory, boolean isSuspense, boolean isLiterary) {
        this.bookName = bookName;
        this.bookCategory = bookCategory;
        this.fitAge = fitAge;
        this.bookPic = bookPic;
        this.isHistory = isHistory;
        this.isSuspense = isSuspense;
        this.isLiterary = isLiterary;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(String bookCategory) {
        this.bookCategory = bookCategory;
    }

    public int getFitAge() {
        return fitAge;
    }

    public void setFitAge(int fitAge) {
        this.fitAge = fitAge;
    }

    public int getBookPic() {
        return bookPic;
    }

    public void setBookPic(int bookPic) {
        this.bookPic = bookPic;
    }

    public boolean isHistory() {
        return isHistory;
    }

    public void setHistory(boolean history) {
        isHistory = history;
    }

    public boolean isSuspense() {
        return isSuspense;
    }

    public void setSuspense(boolean suspense) {
        isSuspense = suspense;
    }

    public boolean isLiterary() {
        return isLiterary;
    }

    public void setLiterary(boolean literary) {
        isLiterary = literary;
    }
}
