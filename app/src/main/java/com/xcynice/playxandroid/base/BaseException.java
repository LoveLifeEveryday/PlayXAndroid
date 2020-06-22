package com.xcynice.playxandroid.base;

import java.io.IOException;

/**
 * Description : BaseException
 *
 * @author XuCanyou666
 * @date 2020/2/7
 */


public class BaseException extends IOException {



    private String errorMsg;
    private int errorCode;

    public String getErrorMsg() {
        return errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }


    public BaseException(String message) {
        this.errorMsg = message;
    }

    public BaseException(String errorMsg, Throwable cause) {
        super(errorMsg, cause);
        this.errorMsg = errorMsg;
    }

    public BaseException(int errorCode, String message) {
        this.errorMsg = message;
        this.errorCode = errorCode;
    }

}