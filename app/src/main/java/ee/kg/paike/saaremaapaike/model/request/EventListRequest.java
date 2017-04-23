package ee.kg.paike.saaremaapaike.model.request;

import android.text.LoginFilter;
import android.util.Log;

import java.io.IOException;

import ee.kg.paike.saaremaapaike.model.WebpageClient;
import ee.kg.paike.saaremaapaike.presenter.eventlist.EventListPresenter;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventListRequest {

    public static void getMainPageHtml(final EventListPresenter presenter) {
        Call<ResponseBody> call = WebpageClient.getInstance().getMainPageHtml();

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d("!!!!!!!!!!!!", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
