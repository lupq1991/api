package com.cn.phoenix.api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配
 */
public class RegexUtil {

    public static String getMatchedString(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }

}
