package com.caxcot.imooclistviewdemo.model;

import java.util.List;

public class LessonResult {

    private String mMessage;
    private int mStatus;
    private List<LessonInfo> mLessonInfoList;

    public String getmMessage() {
        return mMessage;
    }

    public void setmMessage(String mMessage) {
        this.mMessage = mMessage;
    }

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public List<LessonInfo> getmLessonInfoList() {
        return mLessonInfoList;
    }

    public void setmLessonInfoList(List<LessonInfo> mLessonInfoList) {
        this.mLessonInfoList = mLessonInfoList;
    }

    public static class LessonInfo {
        String mName;
        String mDescription;

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmDescription() {
            return mDescription;
        }

        public void setmDescription(String mDescription) {
            this.mDescription = mDescription;
        }
    }
}
