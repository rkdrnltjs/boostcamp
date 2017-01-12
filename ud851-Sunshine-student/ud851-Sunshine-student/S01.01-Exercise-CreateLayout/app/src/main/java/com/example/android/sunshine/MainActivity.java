/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.sunshine;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    Handler handler;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
        Log.d("Oncreate","시작");
        textView = (TextView) findViewById(R.id.textView1);
        httpthread ht = new httpthread();
        ht.start();

    }

    class httpthread extends Thread {
        public void run() {
            try {
                String naver = "http://m.naver.com";
                Log.d("httpthread","시작");
                URL url = new URL(naver);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoOutput(true);
                int a = httpURLConnection.getResponseCode();
                Log.d("리스폰스",String.valueOf(a));
                int b = HttpURLConnection.HTTP_OK;
                Log.d("리스폰스b",String.valueOf(b));
                Log.d("리스폰스",naver);

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(), "UTF-8"));

                String abc = bufferedReader.readLine();
                Toast.makeText(getApplicationContext(),abc,Toast.LENGTH_LONG).show();



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}