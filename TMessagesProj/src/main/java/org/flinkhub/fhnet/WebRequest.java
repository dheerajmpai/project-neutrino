package org.flinkhub.fhnet;

import android.util.Log;

import org.flinkhub.messenger2.BuildVars;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Utility class for performing Web requests
 *
 * @author Ananth SNC
 * @version 1.0
 */
public class WebRequest {
    public static String get(String targetURL) throws IOException {
        HttpURLConnection con = null;

        try {
            URL obj = new URL(targetURL);
            Log.e(Constants.TAG, "Connecting to internet..");

            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-APP-VERSION", BuildVars.BUILD_VERSION_STRING);
//        con.setRequestProperty("User-Agent", USER_AGENT);

            // Get Response
            InputStream is;
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            return response.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /**
     * Execute a HTTP POST Web request
     *
     * @param targetURL The URL to post data to
     * @param postData  The data to be posted, encoded as a query string
     * @return the response as string
     * @throws IOException in case of network errors
     */
    public static String post(String targetURL, String postData) throws IOException {
        HttpURLConnection con = null;

        try {
            // Create connection
            URL url = new URL(targetURL);
            Log.e(Constants.TAG, "Connecting to internet..");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("User-Agent", Config.APP_NAME);
            con.setRequestProperty("Content-Length",
                    Integer.toString(postData.getBytes().length));
            con.setRequestProperty("Content-Language", "en-US");
            con.setRequestProperty("X-APP-VERSION", BuildVars.BUILD_VERSION_STRING);

            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(
                    con.getOutputStream());
            wr.writeBytes(postData);
            wr.close();

            InputStream is;
            if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            return response.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    public static void downloadFile(String urlStr, String targetPath) throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        FileOutputStream fos = new FileOutputStream(targetPath);
        byte[] buffer = new byte[1024];
        int count;

        while ((count = bis.read(buffer, 0, 1024)) != -1) {
            fos.write(buffer, 0, count);
        }

        fos.close();
        bis.close();
    }

    /**
     * Execute a HTTP PUT Web request
     *
     * @param targetURL The URL to post data to
     * @param postData  The data to be posted, encoded as a query string
     * @return the response as string
     * @throws IOException in case of network errors
     */
    public static String put(String targetURL, String postData) throws IOException {
        HttpURLConnection con = null;

        try {
            // Create connection
            URL url = new URL(targetURL);
            Log.e(Constants.TAG, "Connecting to internet..");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PUT");
            con.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("User-Agent", Config.APP_NAME);
            con.setRequestProperty("Content-Length",
                    Integer.toString(postData.getBytes().length));
            con.setRequestProperty("Content-Language", "en-US");
            con.setRequestProperty("X-APP-VERSION", BuildVars.BUILD_VERSION_STRING);

            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.close();

            InputStream is;
            if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }

            // Get Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            Log.e(Constants.TAG, "Got response: " + response);
            return response.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /**
     * Execute a HTTP PATCH Web request
     *
     * @param targetURL The URL to post data to
     * @param postData  The data to be posted, encoded as a query string
     * @return the response as string
     * @throws IOException in case of network errors
     */
    public static String patch(String targetURL, String postData) throws IOException {
        HttpURLConnection con = null;

        try {
            // Create connection
            URL url = new URL(targetURL);
            Log.e(Constants.TAG, "Connecting to internet..");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("PATCH");
            con.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("User-Agent", Config.APP_NAME);
            con.setRequestProperty("Content-Length",
                    Integer.toString(postData.getBytes().length));
            con.setRequestProperty("Content-Language", "en-US");
            con.setRequestProperty("X-APP-VERSION", BuildVars.BUILD_VERSION_STRING);

            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(postData);
            wr.close();

            InputStream is;
            if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }

            // Get Response
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            Log.e(Constants.TAG, "Got response: " + response);
            return response.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }

    /**
     * Execute a HTTP DELETE Web request
     *
     * @param targetURL The URL to post data to
     * @param postData  The data to be posted, encoded as a query string
     * @return the response as string
     * @throws IOException in case of network errors
     */
    public static String delete(String targetURL, String postData) throws IOException {
        HttpURLConnection con = null;

        try {
            // Create connection
            URL url = new URL(targetURL);
            Log.e(Constants.TAG, "Connecting to internet..");

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("DELETE");
            con.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("User-Agent", Config.APP_NAME);
            con.setRequestProperty("Content-Length",
                    Integer.toString(postData.getBytes().length));
            con.setRequestProperty("Content-Language", "en-US");
            con.setRequestProperty("X-APP-VERSION", BuildVars.BUILD_VERSION_STRING);

            con.setUseCaches(false);
            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(
                    con.getOutputStream());
            wr.writeBytes(postData);
            wr.close();

            InputStream is;
            if (200 <= con.getResponseCode() && con.getResponseCode() <= 299) {
                is = con.getInputStream();
            } else {
                is = con.getErrorStream();
            }

            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }

            rd.close();
            return response.toString();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}
