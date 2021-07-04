package com.funtl.itoken.common.constants;

/**
 * @ClassName HttpStatusConstants
 * @Description TODO
 * @Author kdnight
 * @Date 2019/7/3 14:44
 * @Version 1.0
 **/
public enum HttpStatusConstants {
    BAD_GATEWAY(502,"从上游服务器接受到无效的响应");

    private int status;
    private String content;

    private HttpStatusConstants(int status, String content) {
        this.status = status;
        this.content = content;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
}
