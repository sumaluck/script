package com.Org;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestTime {
	public static void main(String[] args) {
		 int ihour=1;

         int min=20;
         Date d=new Date();
         Calendar c=Calendar.getInstance();
         c.set(Calendar.HOUR_OF_DAY,9);
         c.set(Calendar.MINUTE,00);
         c.set(Calendar.SECOND,00);
         Calendar e=Calendar.getInstance();
         e.set(Calendar.HOUR_OF_DAY,17);
         e.set(Calendar.MINUTE,20);
         System.out.println(c.get(Calendar.HOUR_OF_DAY)+" df "+e.get(Calendar.HOUR_OF_DAY));
         while(c.get(Calendar.HOUR_OF_DAY)<e.get(Calendar.HOUR_OF_DAY)) {
             d = c.getTime();
            // SimpleDateFormat ss=new SimpleDateFormat("e dd mmm yy  HH:mm:ss");
             SimpleDateFormat so=new SimpleDateFormat(" E dd MMM YYYY HH:mm a");
             String s=so.format(d);

            // String s=d.toString();
             //freeTimesList.add(s);
             System.out.println(s);

             c.add(Calendar.HOUR, ihour);
             c.add(Calendar.MINUTE, min);



         }



		
	}

}
