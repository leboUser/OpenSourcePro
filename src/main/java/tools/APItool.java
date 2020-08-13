package tools;

import okhttp3.*;
import org.openqa.selenium.json.Json;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class APItool {

    private OkHttpClient client;
    private Request request;
    private Response response;
    private String path;


    public Request getRequest() {
        return request;
    }

    public void setRequest() {
        this.request = new Request.Builder()
                .url(this.path)
                .build();
    }

    public Response getResponse() {
        return response;
    }

    public String ResponseCode() throws IOException {
        try {
            this.response = this.client.newCall(this.request).execute();
            return String.valueOf(response.code());
        }catch(Exception e){
            e.getCause();
            e.getMessage();//("unable to send message "+this.request.toString());
        }
        return null;
    }

    public ResponseBody getResponseBody() throws IOException {
        return this.client.newCall(this.request).execute().body();
    }

    public void setRequestAuth(String auth,String token){
        this.request = new Request.Builder()
                .header(auth, token)
                .url(this.path)
                .build();
    }

    public void postRequest(String body){
        final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
        this.request = new Request.Builder()
                .url(this.path)
                .post(RequestBody.create(body,MEDIA_TYPE_MARKDOWN))
                .build();
    }

    public void ConfigureTimeouts(int connectTime, int writetimeout, int readtimeout) throws Exception {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(connectTime, TimeUnit.SECONDS)
                .writeTimeout(writetimeout, TimeUnit.SECONDS)
                .readTimeout(readtimeout, TimeUnit.SECONDS)
                .build();
    }
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.client = new OkHttpClient();
        this.path = path;
    }

}
