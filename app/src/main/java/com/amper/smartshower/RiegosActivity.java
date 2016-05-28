package com.amper.smartshower;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amper.smartshower.rest.Riegos;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RiegosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riegos);

    }


    public void obtenerRiegos(View v){
        Log.d("obtenerRiegos", "SOY OBTENER RIEGOS");

        TextView tv = (TextView)findViewById(R.id.txtRiegos);
        tv.setText("Welcome to android");

        new HttpRequestTask().execute();

    }


    /**
     * TESTING
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_riegos, container, false);
            return rootView;
        }
    }
    private class HttpRequestTask extends AsyncTask<Void, Void, Riegos> {
        @Override
        protected Riegos doInBackground(Void... params) {
            Log.d("doInBackground", "SOY doInBackground");
            try {
                final String url = "http://rest-service.guides.spring.io/greeting";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Riegos greeting = restTemplate.getForObject(url, Riegos.class);
                return greeting;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Riegos greeting) {
            Log.d("onPostExecute", "SOY onPostExecute");
            TextView greetingIdText = (TextView) findViewById(R.id.id_value);
            TextView greetingContentText = (TextView) findViewById(R.id.content_value);

            greetingIdText.setText(greeting.getId());
            greetingContentText.setText(greeting.getContent());

            Log.d("onPostExecute", "greetingIdText:"+greetingIdText);
            Log.d("onPostExecute", "greetingContentText:"+greetingContentText);
        }

    }
}
