/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.elasticsearch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author foresee@foresee.com.cn
 * @date 2017年10月25日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class Main2 {
    public static  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");;
    public static void main(String[] args) throws Exception {
       System.out.println(Calendar.getInstance().get(Calendar.MONTH));;
    }
    public static String test(int type)throws Exception {
        Calendar beginCalendar=Calendar.getInstance();
        Calendar endCalendar=Calendar.getInstance();
        switch (type) {
            case 1:
                beginCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                endCalendar.add(Calendar.DATE, -1);
                break;
            case 2:
                beginCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                beginCalendar.add(Calendar.WEEK_OF_YEAR, -1);
                beginCalendar.add(Calendar.DATE, 1);
                endCalendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                endCalendar.add(Calendar.WEEK_OF_YEAR, -1);
                endCalendar.add(Calendar.DATE, 1);
                break;
            case 3:
                beginCalendar.set(Calendar.DATE,1);
                endCalendar.add(Calendar.DATE, -1);
                break;
            case 4:
                beginCalendar.set(Calendar.DAY_OF_MONTH,1);
                beginCalendar.add(Calendar.MONTH, -1);
                endCalendar.set(Calendar.DAY_OF_MONTH,1);
                endCalendar.add(Calendar.DATE, -1);
            default:
                break;
        }
        Date begin=beginCalendar.getTime();
        Date end=endCalendar.getTime();
        return format(getDateStr(begin, end));
        
    }
    
    
    public static List<String> getDateStr(Date begin,Date end) throws Exception {
        List<String> returnList=new ArrayList<>();
        SimpleDateFormat dayFormat=new SimpleDateFormat("yyyy-MM-dd");
        long oneDay=24*60*60*1000;
        int day=(int) ((end.getTime()-begin.getTime())/oneDay);
        if(day>0){
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(begin);
            returnList.add(dayFormat.format(calendar.getTime()));
            for (int i=0;i<day;i++){
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                returnList.add(dayFormat.format(calendar.getTime()));
            }
        }
        
        return returnList;
    }
    public static String format(List<String> dayList){
        StringBuilder sb=new StringBuilder();
        Iterator<String> it=dayList.iterator();
        while(it.hasNext()){
            sb.append("'").append(it.next()).append("'");
            if(it.hasNext()){
                sb.append(",");
            }
        }
        return sb.toString();
    }
    
    
}
