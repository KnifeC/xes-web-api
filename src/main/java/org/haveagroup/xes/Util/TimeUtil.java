package org.haveagroup.xes.Util;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TimeUtil {

    public Date stringToDate(String inputDate){//字符串转为时间格式
        String[] dateArray = inputDate.split("T|\\.");
        String stringDate = dateArray[0]+" "+dateArray[1];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date outputDate = sdf.parse(stringDate);
            return outputDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean dateCompare(String inputDate){
        return true;
    }



    public SimpleDateFormat nowDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        sdf.format(new Date());     // new Date()为获取当前系统时间
        return sdf;
    }



}
