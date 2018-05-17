package com.smartcityin.waterknow.Utils;

/**
 * Author : Mr.老王
 * Created on 2018/4/27
 * E-mail : wkz123011@gmail.com
 */
public class StringUtils {
    public static String subPhone(String phone){
        String substring = phone.substring(0, 3);
        String substring1 = phone.substring(phone.length() - 4);
        StringBuffer sb=new StringBuffer();
        int length=phone.length()-7;
        sb.append(substring);
        for (int i = 0; i < length; i++) {
            sb.append("*");
        }
        sb.append(substring1);
    return sb.toString();
    }

}
