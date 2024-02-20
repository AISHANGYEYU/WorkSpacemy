package com.easytest.utils;

import com.easytest.entity.enums.VerifyRegexEnum;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerifyUtils {
    public  static  Boolean verfiy(String regs, String value){
        if (StringTools.isEmpty(value)){
            return  false;
        }
        Pattern pattern  = Pattern.compile(regs);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }//检验正则
    public static Boolean verfiy(VerifyRegexEnum regs, String value){
        return verfiy(regs.getRegex(),value);
    }
}
