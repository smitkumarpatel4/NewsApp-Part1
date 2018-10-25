package com.example.android.newsapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.newsapp.Utilities.NetworkUtils;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView mQueryTextView;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mQueryTextView = (TextView)findViewById(R.id.tv_newsapp_results_json);
        setNewsQuery();
    }

    public void setNewsQuery(){
        URL newsURL = NetworkUtils.buildUrl();
        mQueryTextView.setText(newsURL.toString());
        new NewsAppQueryTask().execute(newsURL);
    }

    public class NewsAppQueryTask extends AsyncTask<URL, Void, String>{
        @Override
        protected String doInBackground(URL... urls) {
            URL clickUrl = urls[0];
            String newsAppClickResult = null;
            try{
                newsAppClickResult = NetworkUtils.getResponseFromHttpUrl(clickUrl);
            }
            catch (IOException e){
                e.printStackTrace();
            }
            return newsAppClickResult;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s != null && !s.equals("")){
                mQueryTextView.setText(s);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main ,menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int itemThatClickedId = item.getItemId();
        if (itemThatClickedId == R.id.action_search){
            Context context = MainActivity.this;
            String textToShow ="Search Clicked";
            Toast.makeText(context, textToShow, Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }
}
