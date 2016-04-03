package com.example.mustafa.mvig_android;


import android.os.AsyncTask;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.net.URL;

/**
 * Created by Mustafa on 13.12.2015.
 */


class JSONParser extends AsyncTask<String, Void, Document> {

    private Exception exception;

       protected Document doInBackground(String... urls) {
            try {
                Document doc = Jsoup.connect("http://www.tcmb.gov.tr/kurlar/today.xml").get();

                return doc;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        protected void onPostExecute(Document feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }


