package com.sprsec.util;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Created by Yaroslav on 03.02.2015.
 */
public class Util {
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
