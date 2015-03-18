package com.goodfood.util;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yaroslav on 03.02.2015.
 */
public class Util {
    public static Date getDate(int day) { // for calendar in Calc Controller
        // if day == 0 this means I have this day
        Date date = new Date();                      // timestamp now
        Calendar cal = Calendar.getInstance();       // get calendar instance
        cal.setTime(date);                           // set cal to date
        if (day == 0) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        } else {
            cal.add(Calendar.DAY_OF_MONTH,  -day);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);            // set hour to midnight
        cal.set(Calendar.MINUTE, 0);                 // set minute in hour
        cal.set(Calendar.SECOND, 0);                 // set second in minute
        cal.set(Calendar.MILLISECOND, 0);            // set millis in second
        Date zeroedDate = cal.getTime();
        return zeroedDate;
    }
    public static String fileToString(MultipartFile file) {
        Blob blob;
        try {
            byte[] img = file.getBytes();
            blob = new javax.sql.rowset.serial.SerialBlob(img);
            int length = (int) blob.length();
            byte[] encodeBase64 = Base64.encode(blob.getBytes(1, length));
            return new String(encodeBase64, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fileToString(File file) {
        Blob blob;
        try {
            FileInputStream fileInputStream=null;
            byte[] bFile = new byte[(int) file.length()];
            //convert file into array of bytes
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            blob = new javax.sql.rowset.serial.SerialBlob(bFile);
            int length = (int) blob.length();
            byte[] encodeBase64 = Base64.encode(blob.getBytes(1, length));
            return new String(encodeBase64, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SerialException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String Iso88591ToUtf8(String value) {
        byte ptext[] = new byte[0];
        try {
            ptext = value.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String newValue = null;
        try {
            newValue = new String(ptext, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return newValue;
    }
}
