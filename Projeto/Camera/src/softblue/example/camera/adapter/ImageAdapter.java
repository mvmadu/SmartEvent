package softblue.example.camera.adapter;

import java.io.File;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
 
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Cursor cursor;
    private int columnIndex;
    //static File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SmartEvent");

    /*String[] projection = {MediaStore.Images.Media._ID};
    // Create the cursor pointing to the SDCard
    cursor = managedQuery( MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, MediaStore.Images.Media.DATA + " like ? ", new String[] {"%myimagesfolder%"}, null);
    // Get the column index of the image ID
    columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
    g.setAdapter(new ImageAdapter(this));
*/
    
    // Keep all Images in array
    public Integer[] mThumbIds = {
    		/*(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ 1 + ".jpg"), R.drawable.sample_1,
            R.drawable.sample_1, R.drawable.sample_2,
            R.drawable.sample_3, R.drawable.sample_4,
            R.drawable.sample_5, R.drawable.sample_6,
            R.drawable.sample_1, R.drawable.sample_1,
            R.drawable.sample_1, R.drawable.sample_2,
            R.drawable.sample_3, R.drawable.sample_4,
            R.drawable.sample_5, R.drawable.sample_6,
            R.drawable.sample_7, R.drawable.sample_6,
            R.drawable.sample_1, R.drawable.sample_1,*/

    };
 
    public void setmThumbIds(int pos, int f){
    	mThumbIds[pos] = f;
    }
    
    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }
 
    @Override
    public int getCount() {
        return mThumbIds.length;
    }
 
    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }
 
    @Override
    public long getItemId(int position) {
        return 0;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));
        return imageView;
    }
	 
}