package app.whowent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by bear on 11/16/17.
 */

public class ServerConnection {

    private final String serverPath = "http://0db80bea.ngrok.io";
    private String serverResponse = "";


    //==============================================================================================
    // Get Info
    //==============================================================================================

    //---sendInfoToServer---------------------------------------------------------------------------

    void sendInfoToServer(final HashMap<String, String> info) {
        // spawn a new thread for request to run on
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                serverResponse = makePostCallToServer(info);
            }
        });

        // start thread
        thread.start();

        // wait for thread to finish before moving on
        try {
            thread.join();
        } catch (Exception e) {
            System.out.println("Thread issues in SendInfoToServer()");
        }

    }

    /*
    //---getInfoFromServer--------------------------------------------------------------------------

    // makes a post call to server and returns info
    String getInfoFromServer() {

        // spawn a new thread for request to run on
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                serverResponse = makePostCallToServer();
            }
        });

        // start thread
        thread.start();

        // wait for thread to finish before moving on
        try {
            thread.join();
        } catch (Exception e) {
            System.out.println("Thread issues in setUpButtons()");
        }

        // after result has come in and thread closed, change UI on main thread
        return serverResponse;
    }
*/

    //==============================================================================================
    // Web Shit
    //==============================================================================================
    /*
        functions for handling http requests
     */

    //---makePostCallToServer-----------------------------------------------------------------------

    // makes a Post Call
    String makePostCallToServer(HashMap<String, String> info) {
        String response = "";

        // connect
        try {
            // connect to url
            URL url = new URL(serverPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            //urlConnection.setRequestProperty("test", "test2");

            urlConnection.getOutputStream().write(getPostDataString(info).getBytes());
            urlConnection.getOutputStream().flush();
            urlConnection.getOutputStream().close();


            try {

                // get response
                InputStream inputStream = urlConnection.getInputStream();
                Scanner scanner = new Scanner(inputStream);
                StringBuilder stringBuilder = new StringBuilder();
                while(scanner.hasNext()){
                    //Log.i("inside", "inside");
                    stringBuilder.append(scanner.nextLine());
                }
                response = stringBuilder.toString();

                //echoTextView.setText(document);
                System.out.println(response);

            } finally {
                urlConnection.disconnect();
            }
        } catch (MalformedURLException e) {
            System.out.println("MalformedURLException in pingInternet()");
        } catch (IOException e) {
            System.out.println("IOException in pingInternet()");
        }

        return response;
    }

    //---getPostDataString--------------------------------------------------------------------------

    // converts hashmap to string
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
