package dev.zontreck.ariaslib.http;

public class HTTPResponse
{
    private String ContentType;
    private int ResponseCode;
    private String ResponseBody;
    private HTTPRequest OriginalRequest;

    protected HTTPResponse(String contentType, int code, String body, HTTPRequest request){
        this.ContentType = contentType;
        this.ResponseCode = code;
        this.ResponseBody = body;
        this.OriginalRequest = request;

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

    public HTTPRequest getOriginalRequest() {
        return OriginalRequest;
    }
}
