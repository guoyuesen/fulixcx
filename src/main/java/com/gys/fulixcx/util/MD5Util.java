package com.gys.fulixcx.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
    public static String StringToMd5(String psw) {
        {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(psw.getBytes("UTF-8"));
                byte[] encryption = md5.digest();

                StringBuffer strBuf = new StringBuffer();
                for (int i = 0; i < encryption.length; i++) {
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                    } else {
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));
                    }
                }

                return strBuf.toString();
            } catch (NoSuchAlgorithmException e) {
                return "";
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
    }
}
