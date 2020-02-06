package com.caxcot.imoocdemo1;

import java.util.List;

public class LeesonResult {
    private int mStatus;
    private List<Lesson> mLessons;

    public int getmStatus() {
        return mStatus;
    }

    public void setmStatus(int mStatus) {
        this.mStatus = mStatus;
    }

    public List<Lesson> getmLessons() {
        return mLessons;
    }

    public void setmLessons(List<Lesson> mLessons) {
        this.mLessons = mLessons;
    }

    @Override
    public String toString() {
        return "LeesonResult{" +
                "mStatus=" + mStatus +
                ", mLessons=" + mLessons +
                '}';
    }

    public static class Lesson {
        private int mID;
        private int mLearnerNumber;
        private String mName;
        private String mSmallPicture;
        private String mBigPicture;
        private String mDescription;

        public int getmID() {
            return mID;
        }

        public void setmID(int mID) {
            this.mID = mID;
        }

        public int getmLearnerNumber() {
            return mLearnerNumber;
        }

        public void setmLearnerNumber(int mLearnerNumber) {
            this.mLearnerNumber = mLearnerNumber;
        }

        public String getmName() {
            return mName;
        }

        public void setmName(String mName) {
            this.mName = mName;
        }

        public String getmSmallPicture() {
            return mSmallPicture;
        }

        public void setmSmallPicture(String mSmallPicture) {
            this.mSmallPicture = mSmallPicture;
        }

        public String getmBigPicture() {
            return mBigPicture;
        }

        public void setmBigPicture(String mBigPicture) {
            this.mBigPicture = mBigPicture;
        }

        public String getmDescription() {
            return mDescription;
        }

        public void setmDescription(String mDescription) {
            this.mDescription = mDescription;
        }

        @Override
        public String toString() {
            return "Lesson{" +
                    "mID=" + mID +
                    ", mLearnerNumber=" + mLearnerNumber +
                    ", mName='" + mName + '\'' +
                    ", mSmallPicture='" + mSmallPicture + '\'' +
                    ", mBigPicture='" + mBigPicture + '\'' +
                    ", mDescription='" + mDescription + '\'' +
                    '}';
        }
    }
}
