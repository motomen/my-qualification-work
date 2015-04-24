package com.goodfood.util;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Yaroslav on 03.02.2015.
 */
public class Util {
    public static Date getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }

    public static Date getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
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
    // 0 = ""
    // special symbol is ", ."
    public static String replaceSpecialSymbolOn0(String text) {
        if (text.lastIndexOf(",") > 0){
            text = text.replace(",", "");
        }
        if (text.lastIndexOf(".") > 0) {
            text = text.replace(".", "");
        }
        return text;
    }
}
