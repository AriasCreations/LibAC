package dev.zontreck.ariaslib.http;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequestBuilder
{

    private HttpURLConnection connection;
    private URL url;
    private HTTPRequest request = new HTTPRequest();

    public static HTTPRequestBuilder builder()
    {
        return new HTTPRequestBuilder();
    }

    protected HTTPRequestBuilder()
    {

    }

    /**
     * Sets the url in this request to the one supplied
     * @param url The url to connect to
     * @return Builder instance
     * @throws MalformedURLException If the URL supplied was invalid
     */
    public HTTPRequestBuilder withURL( String url) throws MalformedURLException {
        request.url = url;
        this.url = new URL(url);

        return this;
    }

    /**
     * Sets the HTTP Request method
     * @param method The method you want to use
     * @see HTTPMethod
     * @return Builder instance
     */
    public HTTPRequestBuilder withMethod(HTTPMethod method)
    {
        switch(method)
        {
            case GET:
            {
                request.method = "GET";
                break;
            }
            case POST: {
                request.method = "POST";
                if(request.contentType.isEmpty()) request.contentType = "application/x-www-form-urlencoded";
                break;
            }
            case DELETE:{
                request.method = "DELETE";
                break;
            }
            case PUT:{
                request.method = "PUT";
                if(request.contentType.isEmpty()) request.contentType = "application/x-www-form-urlencoded";
                break;
            }
        }

        return this;
    }

    /**
     * Sets the request body. This may only be processed by the server when using POST or PUT, depending on the server's setup
     * @param body The body to upload
     * @return Builder Instance
     */
    public HTTPRequestBuilder withBody(String body)
    {
        request.body = body;
        return this;
    }

    /**
     * Sets the content type header
     * Default: application/x-www-form-urlencoded for POST/PUT, and null/not present for GET
     * @param type
     * @return
     */
    public HTTPRequestBuilder withContentType(String type)
    {
        request.contentType = type;
        return this;
    }

    public HTTPResponse build()
    {
        try {
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(request.method);
            byte[] array = request.body.getBytes("UTF-8");
            connection.setRequestProperty("Content-Length" , "" + array.length);
            connection.setRequestProperty("Content-Type", request.contentType);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setDoOutput(true);
            DataOutputStream dos = new DataOutputStream(connection.getOutputStream());
            dos.write(array);
            dos.flush();
            dos.close();


            // Get the response body
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            inputStream.close();

            String responseBody = response.toString();

            return new HTTPResponse(connection.getContentType(), connection.getResponseCode(), responseBody, request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            connection.disconnect();
        }
    }
}
