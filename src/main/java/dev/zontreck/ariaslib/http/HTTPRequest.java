package dev.zontreck.ariaslib.http;

import com.sun.istack.internal.NotNull;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequest
{

    public String url;

    public String method;
    public String body;
    public String contentType;

    protected HTTPRequest(){

    }
}
