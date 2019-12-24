package com.cn.phoenix.api.util;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author lupq
 * @date 2019/11/27 11:30
 */
@Component
public class VariableUtil {

    private static int count = 0;
    //暂存每次要替换变量的参数体
    private static String parameterSave = null;

    /**
     * 全局变量替换
     *
     * @param
     * @return
     */
    public static String replaceVariables(String parameter, Map<String, String> variableNameAndValueMap) {
        //判断本次拿到的参数与上次参数如果不一样，就将调用数归0
        if (!parameter.equalsIgnoreCase(parameterSave)) {
            count = 0;
        }
        parameterSave = parameter;
        // 将变量及变量值加载到map集合
        Set<String> variableNames = variableNameAndValueMap.keySet();
        for (String variableName : variableNames) {
            //参数如果没有匹配到 { 就返回值
            if (!parameter.contains("${")) {
                return parameter;
            }
            // 判断，如果测试数据出现了变量名
            if (parameter.contains(variableName)) {
                String variableValue = variableNameAndValueMap.get(variableName);
                if (StringUtils.isBlank(variableValue)) {
                    parameter = parameter.replace(variableName, "");
                } else {
                    String[] value = variableValue.split(",");
                    int length = value.length;
                    if (count + 1 > length) {
                        parameter = parameter.replace(variableName, value[0]);
                    }
                    parameter = parameter.replace(variableName, value[count % length]);
                }
            }
        }
        count++;
        return parameter;
    }
}
