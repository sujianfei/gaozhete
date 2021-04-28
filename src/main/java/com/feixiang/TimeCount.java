package com.feixiang;


import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCount {
    private String timeStr;

    private int count;

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public static TimeCount newTimeCount(){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        TimeCount timeCount=new TimeCount();
        String timeStr=  simpleDateFormat.format(new Date());
        timeCount.setCount(1);
        timeCount.setTimeStr(timeStr);
        return timeCount;
    }

    public boolean equalsCurrrentTime(){

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String timeStr=  simpleDateFormat.format(new Date());
        return StringUtils.equals(timeStr,this.timeStr);

    }
}
