package ca.ualberta.cs.picposter.controller;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ca.ualberta.cs.picposter.PicPosterActivity;
import ca.ualberta.cs.picposter.R;
import ca.ualberta.cs.picposter.model.PicPosterModelList;

/**
 * Controller for PicPost that scales Bitmaps, checks Text, etc.
 * @author zjullion
 */
public class PicPosterController {


	public static final int MAX_BITMAP_DIMENSIONS = 50;
	public static final int MAX_TEXT_LENGTH = 100;
	
	
	private PicPosterModelList model;
	private PicPosterActivity activity;
	
	
	public PicPosterController(PicPosterModelList model, PicPosterActivity activity) {
		this.model = model;
		this.activity = activity;
	}
	
	
	/**
	 * Adds the new pic post to the model, adding a default pic and text if none is assigned, and 
	 * scaling the pic and shortening text as necessary.
	 * @param pic the Bitmap to be displayed in the post
	 * @param text the String to be displayed in the post
	 */
	public void addPicPost(Bitmap pic, String text) {
		
		//Assign a default pic if there is none:
		if (pic == null)
			pic = BitmapFactory.decodeResource(this.activity.getResources(), R.drawable.no_img);
		
		//Scale the pic if it is too large:
		if (pic.getWidth() > MAX_BITMAP_DIMENSIONS || pic.getHeight() > MAX_BITMAP_DIMENSIONS) {
			double scalingFactor = pic.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
			if (pic.getHeight() > pic.getWidth())
				scalingFactor = pic.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
			
			int newWidth = (int)Math.round(pic.getWidth() / scalingFactor);
			int newHeight = (int)Math.round(pic.getHeight() / scalingFactor);
			
			pic = Bitmap.createScaledBitmap(pic, newWidth, newHeight, false);
		}
		
		//Assign default text if there is none:
		if (text == null || text.length() == 0)
			text = this.activity.getResources().getString(R.string.no_pic_text);
		
		//Shorten the text if it is too long:
		if (text.length() > MAX_TEXT_LENGTH) {
			text = text.substring(0, MAX_TEXT_LENGTH);
		}
		
		this.model.addPicPost(pic, text, new Date());
	}
	
	
	public void clearModel() {
		this.model.clear();
	}
}
