package ee.kg.paike.saaremaapaike.model;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface WebpageService {

    @GET("http://saaremaasuvi.ee/")
    Call<ResponseBody> getMainPageHtml();

}
