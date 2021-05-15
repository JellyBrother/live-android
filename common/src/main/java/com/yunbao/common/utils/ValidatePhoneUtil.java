package com.yunbao.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ValidatePhoneUtil {
    //判断手机号码的正则表达式
    private static final String MOBILE_NUM_REGEX = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(18[0-9])|(17[0-9])|(16[0-9])|(19[0-9]))\\d{8}$";


    /**
     * 验证一个号码是不是手机号，以前这个地方是用正则判断的，现在改由服务端验证
     *
     * @param mobileNumber
     */
    public static boolean validateMobileNumber(String mobileNumber) {
//        Pattern p = Pattern.compile(MOBILE_NUM_REGEX);
//        Matcher m = p.matcher(mobileNumber);
//        return m.matches();
        return true;
    }

    /**
     * 验证一个号码是不是对应正则表达式的手机号
     *
     * @param mobileNumber
     */
    public static boolean validateMobileNumber(String regex, String mobileNumber) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mobileNumber);
        return m.matches();
    }

    /**
     * 发送短信前，遍历这个枚举，一一校验
     *
     * @param nationalCode
     * @param mobileNumber
     * @return
     */
    public static boolean isMobileNumber(String nationalCode, String mobileNumber) {
        boolean isMobileNumber = false;
        for (MobileRegularExp regularExp : MobileRegularExp.values()) {
            Pattern pattern = Pattern.compile(regularExp.getRegularExp());
            Matcher matcher = pattern.matcher(new StringBuilder().append(nationalCode).append(mobileNumber).toString());
            if (matcher.matches()) {
                isMobileNumber = true;
                // 枚举中把最常用的国际区号拍在前面可以减少校验开销
                break;
            }
        }
        return isMobileNumber;
    }
}
