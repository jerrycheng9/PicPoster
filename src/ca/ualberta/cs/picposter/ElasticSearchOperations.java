package ca.ualberta.cs.picposter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.google.gson.Gson;

import ca.ualberta.cs.picposter.model.PicPostModel;

public class ElasticSearchOperations {

	public static void pushPicPostModel(final PicPostModel model) {
		Thread thread = new Thread(){
			
			@Override
			public void run(){
				Gson gson = new Gson();
				HttpClient client = new DefaultHttpClient();
				HttpPost request = new HttpPost("http://cmput301.softwareprocess.es:8080/testing/jcheng1/1");
				
				try {
					String jsonString = gson.toJson(model);
					request.setEntity(new StringEntity(jsonString));
					
					HttpResponse response = client.execute(request);
					Log.w("ElasticSearch",response.getStatusLine().toString());
					
					response.getStatusLine().toString();
					HttpEntity entity = response.getEntity();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
					String output = reader.readLine();
					while(output != null){
						Log.w("ElasticSearch",output);
						output = reader.readLine();
					}
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		};
		
		thread.start();
	}
	
	public static void search(final String searchterm){
		Thread thread = new Thread(){
			
			@Override
			public void run(){
				Gson gson = new Gson();
				HttpClient client = new DefaultHttpClient();
				HttpGet request = new HttpGet("http://cmput301.softwareprocess.es:8080/testing/jcheng1/_search?q="+searchterm);
				
				try {
					HttpResponse response = client.execute(request);
					Log.w("ElasticSearch",response.getStatusLine().toString());
					

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
				
			}
		};
	}
	
}
