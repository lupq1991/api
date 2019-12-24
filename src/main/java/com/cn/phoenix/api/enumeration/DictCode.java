package com.cn.phoenix.api.enumeration;

import java.util.HashMap;
import java.util.Map;

public class DictCode {

    public enum TestData {
        RANDOM(0, "随机数", "random"),
        NAME(1, "姓名", "systemName"),
        ID_CARD(2, "姓名", "systemIdCard"),
        PHONE(3, "手机号", "systemPhone");

        private int code;
        private String cnDescribe;
        private String enDescribe;

        private TestData(int code, String cnDescribe, String enDescribe) {
            this.code = code;
            this.cnDescribe = cnDescribe;
            this.enDescribe = enDescribe;
        }

        public int code() {
            return this.code;
        }

        public String text() {
            return this.cnDescribe;
        }

        public String enText() {
            return this.enDescribe;
        }
    }

    public enum CheckType {
        NO_CHECK(0, "不校验", "noCheck"),
        STATUS_CODE(1, "状态码", "statusCode"),
        CONTENT(2, "内容校验", "content"),
        CONTENT_TYPE(1, "包含内容", "contains"),
        REGULAR(3, "正则校验", "regular");

        private int code;
        private String cnDescribe;
        private String enDescribe;

        private CheckType(int code, String cnDescribe, String enDescribe) {
            this.code = code;
            this.cnDescribe = cnDescribe;
            this.enDescribe = enDescribe;
        }

        public int code() {
            return this.code;
        }

        public String text() {
            return this.cnDescribe;
        }

        public String enText() {
            return this.enDescribe;
        }

    }

    public enum ProjectType {
        OTHER(0, "其他", "other"), WEB(1, "WEB", "web"), APP(2, "APP", "app"), INTERFACE(3, "接口", "interface");

        private int code;
        private String cnDescribe;
        private String enDescribe;

        private ProjectType(int code, String cnDescribe, String enDescribe) {
            this.code = code;
            this.cnDescribe = cnDescribe;
            this.enDescribe = enDescribe;
        }

        public int code() {
            return this.code;
        }

        public String text() {
            return this.cnDescribe;
        }

        public String enText() {
            return this.enDescribe;
        }

        public static String getText(int code) {
            for (ProjectType item : ProjectType.values()) {
                if (item.code() == code) {
                    return item.text();
                }
            }
            return null;
        }
    }

    public enum SwitchCode {
        DISABLE(0, "禁用", "disable"), ENABLE(1, "启用", "enable");

        private int code;
        private String cnDescribe;
        private String enDescribe;

        private SwitchCode(int code, String cnDescribe, String enDescribe) {
            this.code = code;
            this.cnDescribe = cnDescribe;
            this.enDescribe = enDescribe;
        }

        public int code() {
            return this.code;
        }

        public String text() {
            return this.cnDescribe;
        }

        public String enText() {
            return this.enDescribe;
        }

        public static String getText(int code) {
            for (SwitchCode s : SwitchCode.values()) {
                if (s.code() == code) {
                    return s.text();
                }
            }
            return null;
        }

        public static String getEnText(int code) {
            for (SwitchCode s : SwitchCode.values()) {
                if (s.code() == code) {
                    return s.enText();
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<>();
            for (SwitchCode s : SwitchCode.values()) {
                map.put(String.valueOf(s.code()), s.text());
            }
            return map;
        }
    }

    public enum RequestCode {
        POST(1, "POST"), GET(2, "GET");

        private int code;
        private String describe;

        private RequestCode(int code, String describe) {
            this.code = code;
            this.describe = describe;
        }

        public int code() {
            return this.code;
        }

        public String text() {
            return this.describe;
        }

        public static String getText(int code) {
            for (RequestCode s : RequestCode.values()) {
                if (s.code() == code) {
                    return s.text();
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<>();
            for (RequestCode s : RequestCode.values()) {
                map.put(String.valueOf(s.code()), s.text());
            }
            return map;
        }
    }

    public enum ContentCode {
        JSON(1, "JSON"), FORM(2, "FORM(表单)");

        private int code;
        private String describe;

        private ContentCode(int code, String describe) {
            this.code = code;
            this.describe = describe;
        }

        public int code() {
            return this.code;
        }

        public String text() {
            return this.describe;
        }

        public static String getText(int code) {
            for (ContentCode s : ContentCode.values()) {
                if (s.code() == code) {
                    return s.text();
                }
            }
            return null;
        }

        public static Map<String, String> map() {
            Map<String, String> map = new HashMap<>();
            for (ContentCode s : ContentCode.values()) {
                map.put(String.valueOf(s.code()), s.text());
            }
            return map;
        }
    }

}
