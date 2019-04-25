package org.wolf.common.entity.http;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponseBase implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1879381784055514895L;

    private boolean success = true;
    private String status;
    private String msg;
    private Object results;

    HttpResponseBase() {
    }

    HttpResponseBase(String reCode, String msg, Object data) {
        this.status = reCode;
        this.msg = msg;
        this.results = data;
    }

    HttpResponseBase(boolean success, String reCode, String msg, Object data) {
        this.success = success;
        this.status = reCode;
        this.msg = msg;
        this.results = data;
    }
}
