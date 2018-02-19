package be.ecam.a16333.fakeweather;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;

/**
 * Created by Sen on 6/02/2018.
 */

public class WeatherListActivity extends AppCompatActivity{

    private RecyclerView resultView;
    private Adaptater itemAdapter;
    private TextView cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        resultView = (RecyclerView) findViewById(R.id.zone);
        cityName = findViewById(R.id.city);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,
                        false);
        resultView.setLayoutManager(layoutManager);
        resultView.setHasFixedSize(true);

        itemAdapter = new Adaptater();
        resultView.setAdapter(itemAdapter);
        new WeatherListActivity.QueryTask().execute("https://andfun-weather.udacity.com/weather");

    }

    public class QueryTask extends AsyncTask<String, Void ,String> {

        @Override
        protected String doInBackground (String... params){

            String searchUrl = params[0];
            String queryResults = null;

            try {

                queryResults = UrlConnect.getResponseFromHttpUrl(searchUrl);




            }catch (Exception e){
                Context c = WeatherListActivity.this;
                String txt = e.getMessage();
                Toast.makeText(c,txt,Toast.LENGTH_SHORT).show();
            }

            return queryResults;
        }

        @Override
        protected void onPostExecute (String queryResults){

            if (queryResults != null && !queryResults.equals("")){

                try {
                    JSONObject jo = new JSONObject(queryResults);
                    JSONObject j = jo.getJSONObject("city");
                  //  Array weatherData = jo.getJSONArray("list");
                  //  cityName.setText(j.getString("name"));

                }catch(JSONException e) {

                    e.printStackTrace();

                }
            }
        }
    }

}
