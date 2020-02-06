package com.imooc.model;

public class Position {
    private String positionNo;
    private String positionName;

    public Position() {
    }

    public Position(String positionNo, String positionName) {
        this.setPositionNo(positionNo);
        this.setPositionName(positionName);
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }
}
