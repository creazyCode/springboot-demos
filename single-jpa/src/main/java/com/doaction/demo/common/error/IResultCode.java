package com.doaction.demo.common.error;

public interface IResultCode {

    String code();

    String message();

    IResultCode SUCCESS = new IResultCode() {
        public String code() {
            return "0000";
        }

        public String message() {
            return "Success";
        }
    };

    IResultCode SYSTEM_ERROR = new IResultCode() {
        public String code() {
            return "9999";
        }

        public String message() {
            return "系统错误，请稍后重试！";
        }
    };

    IResultCode ILLEGAL_ARGUMENT = new IResultCode() {
        public String code() {
            return "4999";
        }

        public String message() {
            return "Illegal Arguments";
        }
    };
}
