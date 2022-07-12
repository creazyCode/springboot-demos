package com.doaction.demo.common.error;

public interface IBaseCode {

    String code();

    String message();

    IBaseCode SUCCESS = new IBaseCode() {
        public String code() {
            return "0000";
        }

        public String message() {
            return "Success";
        }
    };

    IBaseCode FAIL = new IBaseCode() {
        public String code() {
            return "9999";
        }

        public String message() {
            return "操作失败, 请稍后重试";
        }
    };
}
