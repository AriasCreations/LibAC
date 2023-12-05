package dev.zontreck.ariaslib.http;

public class HTTPResponse
{
    private String ContentType;
    private int ResponseCode;
    private String ResponseBody;

    protected HTTPResponse(String contentType, int code, String body, HTTPRequest request){

    }

    public String getContentType() {
        return ContentType;
    }

    public int getResponseCode() {
        return ResponseCode;
    }

    public String getResponseBody() {
        return ResponseBody;
    }
}
