package ee.kg.paike.saaremaapaike.model;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

public class WebpageClient {

    private static WebpageService instance;

    public WebpageClient() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.readTimeout(10, TimeUnit.SECONDS);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        OkHttpClient okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://saaremaasuvi.ee/")
                .client(okHttpClient)
                .build();

        instance = retrofit.create(WebpageService.class);
    }

    public static WebpageService getInstance() {
        if (instance == null) new WebpageClient();
        return instance;
    }
}
