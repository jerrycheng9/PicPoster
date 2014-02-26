package ca.ualberta.cs.picposter.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ca.ualberta.cs.picposter.ElasticSearchOperations;

import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

/**
 * Represents a list of all Pic Posts.
 * @author zjullion
 */
public class PicPosterModelList {
	
	
	private List<PicPostModel> list;
	private ArrayAdapter<PicPostModel> adapter;
	
	
	public PicPosterModelList() {
		this.list = new ArrayList<PicPostModel>();
	}
	
	
	public void addPicPost(Bitmap picture, String text, Date timestamp) {
		//PicPostModel picPost = new PicPostModel(pic, text, timestamp);
		PicPostModel picPost = new PicPostModel(text, timestamp);
		this.list.add(picPost);
		this.adapter.notifyDataSetChanged();
		
		ElasticSearchOperations.pushPicPostModel(picPost);
	}
	
	
	public void clear() {
		this.list.clear();
		this.adapter.notifyDataSetChanged();
	}
	
	
	public List<PicPostModel> getList() {
		return Collections.unmodifiableList(this.list);
	}
	
	
	public void setAdapter(ArrayAdapter<PicPostModel> adapter) {
		this.adapter = adapter;
	}
}