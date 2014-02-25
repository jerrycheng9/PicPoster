package ca.ualberta.cs.picposter.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.ualberta.cs.picposter.R;
import ca.ualberta.cs.picposter.model.PicPostModel;

/**
 * An ArrayAdapter for a PicPostModel.
 * Most of the code in this class is taken from 
 * http://stackoverflow.com/questions/8166497/custom-adapter-for-list-view
 * @author zjullion
 */
public class PicPostModelAdapter extends ArrayAdapter<PicPostModel> {


	public PicPostModelAdapter(Context context, int resource, List<PicPostModel> model) {
		super(context, resource, model);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			convertView = inflater.inflate(R.layout.pic_post, null);
		}

		PicPostModel picPostModel = this.getItem(position);
		if (picPostModel != null) {

			ImageView picImageView = (ImageView)convertView.findViewById(R.id.pic_image_view);
			if (picImageView != null)
				picImageView.setImageBitmap(picPostModel.getPicture());
			
			TextView picText = (TextView)convertView.findViewById(R.id.pic_text);
			if (picText != null)
				picText.setText(picPostModel.getText());
			
			TextView picTimestamp = (TextView)convertView.findViewById(R.id.pic_timestamp);
			if (picTimestamp != null)
				picTimestamp.setText(picPostModel.getTimestamp().toString());
		}

		return convertView;

	}
}
