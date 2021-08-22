package com.wbhz.util;


import java.security.MessageDigest;
public class MD5 {
    
    public final static String toMD5(String s){
    	//修改字符集，可以修改生成的md5的串
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9','a', 'b', 'c', 'd', 'e', 'f' };
        try
        {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            //大写MD5
            String result = new String(str).toUpperCase();
            System.out.println(result);
            //转换为大写字�?
            return result;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    public static void main(String[] args) {
    	toMD5("888888");
	}
}
