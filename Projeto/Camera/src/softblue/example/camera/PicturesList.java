package softblue.example.camera;

import java.io.File;

import softblue.example.camera.adapter.ImageAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

public class PicturesList extends Activity {
	
	float XIni = 0;
    float YIni = 0;
    float XFin = 0;
    float YFin = 0;	
    
    //ImageAdapter myImageAdapter;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teste);
		GridView gridview = (GridView) findViewById(R.id.gridview);
	    gridview.setAdapter(new ImageAdapter(this));
	    
	    /*myImageAdapter = new ImageAdapter(this);
        gridview.setAdapter(myImageAdapter);
        
        String ExternalStorageDirectoryPath = Environment
        		.getExternalStorageDirectory()
        		.getAbsolutePath();
        
        String targetPath = ExternalStorageDirectoryPath + "/test/";
        
        Toast.makeText(getApplicationContext(), targetPath, Toast.LENGTH_LONG).show();
        File targetDirector = new File(targetPath);
        
        File[] files = targetDirector.listFiles();
        for (File file : files){
        	myImageAdapter.add(file.getAbsolutePath());
        }*/
	
	    final View touchView = gridview;//findViewById(R.id.gridview);
	    	touchView.setOnTouchListener(new View.OnTouchListener() 
	    	{
	    	
	    		public boolean onTouch(View v, MotionEvent event)
	    		{
	    			final int action = event.getAction();
	     
	    			switch (action & MotionEvent.ACTION_MASK) 
	    			{
	    				case MotionEvent.ACTION_DOWN: 
	    				{
	    					XIni = event.getX();
	    					YIni = event.getY();
	    					break;
	    				}

	    				case MotionEvent.ACTION_MOVE:
	    				{
	    					XFin = event.getX();
	    					YFin = event.getY();
	    					if(XFin-XIni>100)
	    					{	
	    						Intent i = new Intent( PicturesList.this, Camera1Activity.class );
	    						startActivity(i);
	    					}
	                    
	    					break;
	    				}
	    			}
	    			return true;
	    		}
	    	
	    	});
	}
}
