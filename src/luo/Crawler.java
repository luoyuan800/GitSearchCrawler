/*
 * Crawler.java
 * Date: 8/6/2015
 * Time: 11:32 AM
 * 
 * Copyright 2015 luoyuan.
 * ALL RIGHTS RESERVED.
*/

package luo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Crawler {
    public HttpURLConnection openConnect(String urlString) {
        HttpURLConnection httpUrl = null;
        URL url;
        try {
            url = new URL(urlString);
            httpUrl = (HttpURLConnection) url.openConnection();
            httpUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return httpUrl;
    }

    public String readPageAsString(HttpURLConnection connection) {
        StringBuilder sb = new StringBuilder();
        try {
            connection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = br.readLine();
            while (line != null && !line.isEmpty()) {
                //System.out.println(line);
                sb.append(line).append("\n");
                line = br.readLine();
            }
            if (Integer.parseInt(connection.getHeaderField("X-RateLimit-Remaining")) < 2) {
                Thread.sleep(1000 * 60);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public String buildUrlString(CharSequence desc) {
        return "https://api.github.com/search/repositories?q=" + desc + "+language:java&sort=stars&order=desc&per_page=10";
    }

    public void handler(List<String> page) {
        for (String line : page) {

        }
    }

    public static void main(String... args) {
        Crawler crawler = new Crawler();
        String url;
//        for (int i = 97; i <= 122; i++) {
            url = crawler.buildUrlString(String.valueOf('c'));
            HttpURLConnection connection = crawler.openConnect(url);
            String page = crawler.readPageAsString(connection);
            System.out.println(page);
            System.out.println("-----");
//        }
    }
}
