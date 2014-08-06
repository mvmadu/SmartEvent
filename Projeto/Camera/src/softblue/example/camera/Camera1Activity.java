package softblue.example.camera;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Esta activity mostra a integração com a câmera usando a aplicação nativa de câmera do Android
 * @author Carlos Tosin @ Softblue*/
public class Camera1Activity extends Activity {

	FileOutputStream fos = null;
	float XIni = 0;
    float YIni = 0;
    float XFin = 0;
    float YFin = 0;	
	//static private Context C;
    static int numFotos = 0;
    GridView grid;
    final int CAMERA_CAPTURE = 1;
    
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private  List<String> listOfImagesPath;
    
    public static final String GridViewDemo_ImagePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/GridViewDemo/";
    
    //static ImageAdapter myImageAdapter = new ImageAdapter(C);

    
    /*String state = Environment.getExternalStorageState();
    {
    	if (Environment.MEDIA_MOUNTED.equals(state)) 
    	{
        	File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SmartFotos");
    	}
    }
    File file = new File(Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES)+"/SmartFotos");
	/**
	 * Código de requisição para poder identificar quando a activity da câmera é finalizada
	 */
	private static final int REQUEST_PICTURE = 1000;

	/**
	 * View onde a foto tirada será exibida
	 */
	private ImageView imageView;
	
	/**
	 * Local de armazenamento da foto tirada 
	 */
	private File imageFile;

	/**
	 * Invocado quando a activity é criada
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	static File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SmartEvent");
	
	private Uri fileUri;
	public static final int MEDIA_TYPE_IMAGE = 1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		grid = (GridView) findViewById(R.id.gridview);
		this.imageView = (ImageView) findViewById(R.id.imagem);
		Log.v("Log0", "fgbsfghnxf");
		// Obtém o local onde as fotos são armazenadas na memória externa do dispositivo
		//File picsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		
		// Define o local completo onde a foto será armazenada (diretório + arquivo) 
		//File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "SmartEvent");
		/*try
		{
			fos = new FileOutputStream(file+"/foto"+numFotos);
			fos.write("huebr".getBytes());
			fos.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		listOfImagesPath = null;
		listOfImagesPath = RetriveCapturedImagePath();
		Log.v("Log2", "fgbsfghnxf");
		if(listOfImagesPath!=null){
			System.out.println(listOfImagesPath);
			grid.setAdapter(new ImageListAdapter(this,listOfImagesPath));
		}
		Log.v("Log3", "fgbsfghnxf");
		
		//imageFile = new File(mediaStorageDir, "foto_"+ numFotos +".jpg");
		
	    final View touchView = findViewById(R.id.entire_view);
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
	                    
	                    if(XIni-XFin>250)
	    	            {
	                    	Intent i = new Intent(Camera1Activity.this, PicturesList.class);
	                        startActivity(i);
	    	            }

	                    break;
	                }
	            }
	            return true;
	        }

	    });
	    
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		Log.v("Log2", "fgbsfghnxf");
		//super.onCreateOptionsMenu(menu);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.menu, menu);
	    return true;
	}
	
	
	public void onClick(View arg0) {
		Log.v("Log3", "fgbsfghnxf");
	// TODO Auto-generated method stub
		if (arg0.getId() == R.id.capture) {
	 
			try {
			//use standard intent to capture an image
				Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				//we will handle the returned data in onActivityResult
				startActivityForResult(captureIntent, CAMERA_CAPTURE);
			} catch(ActivityNotFoundException anfe){
			    //display an error message
				String errorMessage = "Whoops - your device doesn't support capturing images!";
				Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	 
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.v("Log1", "fgbsfghnxf");
		if (resultCode == RESULT_OK) {
		//user is returning from capturing an image using the camera
			if(requestCode == CAMERA_CAPTURE){
				Bundle extras = data.getExtras();
				Bitmap thePic = extras.getParcelable("data");
				String imgcurTime = dateFormat.format(new Date());
				File imageDirectory = new File(GridViewDemo_ImagePath);
				imageDirectory.mkdirs();
				String _path = GridViewDemo_ImagePath + imgcurTime+".jpg";
				try {
					FileOutputStream out = new FileOutputStream(_path);
					thePic.compress(Bitmap.CompressFormat.JPEG, 90, out);
					out.close();
				} catch (FileNotFoundException e) {
					e.getMessage();
				} catch (IOException e) {
					e.printStackTrace();
				}
				listOfImagesPath = null;
				listOfImagesPath = RetriveCapturedImagePath();
				if(listOfImagesPath!=null){
					grid.setAdapter(new ImageListAdapter(this,listOfImagesPath));
				}
			}		
		}
		Log.v("Log1", "fgbsfghnxf");
	}
	
	
	/*public void takePicture(View v) {
		// Cria uma intent que será usada para abrir a aplicação nativa de câmera
		Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		
		// Indica na intent o local onde a foto tirada deve ser armazenada
		i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(imageFile));
		
		 fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
		 i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

		
		// Abre a aplicação de câmera
		startActivityForResult(i, REQUEST_PICTURE);

		numFotos++;
	}*/
	
	private static Uri getOutputMediaFileUri(int type){
		Log.v("Log4", "fgbsfghnxf");
	      return Uri.fromFile(getOutputMediaFile(type));
	}

	private static File getOutputMediaFile(int type){
		Log.v("Log5", "fgbsfghnxf");
	    // To be safe, you should check that the SDCard is mounted
	    // using Environment.getExternalStorageState() before doing this.

	   
	    // This location works best if you want the created images to be shared
	    // between applications and persist after your app has been uninstalled.

	    // Create the storage directory if it does not exist
	    if (! mediaStorageDir.exists()){
	        if (! mediaStorageDir.mkdirs()){
	            Log.d("SmartEvent", "Falhou ao criar o diretório");
	            return null;
	        }
	    }

	    // Create a media file name
	    //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
	    File mediaFile;
	    if (type == MEDIA_TYPE_IMAGE){
	        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
	        "IMG_"+ numFotos + ".jpg");
	        //int x = myImageAdapter.getCount();
	        //myImageAdapter.setmThumbIds(numFotos, R.drawable.sample_1);
	    } else {
	        return null;
	    }
	    //myImageAdapter.setmThumbIds(numFotos, R.drawable.sample_1);
	    return mediaFile;
	}
	/**
	 * Método chamado quando a aplicação nativa da câmera é finalizada
	 * @see android.app.Activity#onActivityResult(int, int, android.content.Intent)
	 */
	//@Override
	/*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// Verifica o código de requisição e se o resultado é OK (outro resultado indica que
		// o usuário cancelou a tirada da foto)
		if (requestCode == REQUEST_PICTURE && resultCode == RESULT_OK) {
		
			FileInputStream fis = null;
			
			try {
				try {
					// Cria um FileInputStream para ler a foto tirada pela câmera
					fis = new FileInputStream(imageFile);
					
					// Converte a stream em um objeto Bitmap
					Bitmap picture = BitmapFactory.decodeStream(fis);
					
					// Exibe o bitmap na view, para que o usuário veja a foto tirada
					imageView.setImageBitmap(picture);
				} finally {
					if (fis != null) {
						fis.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
	private List<String> RetriveCapturedImagePath() {
		Log.v("Log1", "fgbsfghnxf");
		List<String> tFileList = new ArrayList<String>();
		File f = new File(GridViewDemo_ImagePath);
		if (f.exists()) {
			File[] files=f.listFiles();
			Arrays.sort(files);
		 
			for(int i=0; i<files.length; i++){
				File file = files[i];
				if(file.isDirectory())
					continue;
				tFileList.add(file.getPath());
			}
		}
		Log.v("Log1", "fgbsfghnxf");
		return tFileList;
		}
}

