package com.witbus.demo.services;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by nvtien on 10/4/18.
 */
public class Utils {
    public static String getData(String url) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        return br.readLine();
    }

    public static String postData(String url, String data) throws IOException {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        StringEntity entity = new StringEntity(data);
        request.setEntity(entity);
        entity.setContentType("application/json");
        HttpResponse response = client.execute(request);

        BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        return br.readLine();
    }
}
