package be.ecam.a16333.fakeweather;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {
TextView mDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mDisplay = findViewById(R.id.zone);
        new QueryTask().execute("https://andfun-weather.udacity.com/weather");
    }

    public class QueryTask extends AsyncTask<String, Void ,String>{

        @Override
        protected String doInBackground (String... params){

            String searchUrl = params[0];
            String queryResults = null;

            try {

                queryResults = UrlConnect.getResponseFromHttpUrl(searchUrl);

            }catch (IOException e){
                Context c = WeatherActivity.this;
                String txt = e.getMessage();
                Toast.makeText(c,txt,Toast.LENGTH_SHORT).show();
            }

            return queryResults;
        }

        @Override
        protected void onPostExecute (String queryResults){

            if (queryResults != null && !queryResults.equals("")){

                mDisplay.setText(queryResults);
            }
        }
    }
}
