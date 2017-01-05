package com.wbq.meibaiimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wbq.meibaiimage.javaimage.JavaImageUtils;
import com.wbq.meibaiimage.ndkimage.NdkImageUtils;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_image;
    private Bitmap mBitmap;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        iv_image = (ImageView) findViewById(R.id.imageView);
        mBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.image);
        /*美白 argb 调节argb
        * 1.亮度
        * 2.对比度
        * 3.argb
        * **/
    }
    //展示原图
    public void srcImage(View v){
        iv_image.setImageResource(R.drawable.image);
    }
    //java 图像处理
    public void javaImage(View v){
        long currentTimes=System.currentTimeMillis();
        Bitmap imageBitmap = JavaImageUtils.getImageBitmap(mBitmap);
        iv_image.setImageBitmap(imageBitmap);
        long endTimes=System.currentTimeMillis();
        Toast.makeText(this,"java处理"+(endTimes-currentTimes),Toast.LENGTH_SHORT).show();
    }
    //ndk 图像处理
    public void ndkImage(View v){
        long currentTimes=System.currentTimeMillis();
        int width=mBitmap.getWidth();
        int height=mBitmap.getHeight();
        int[] buffer=new int[width*height];
        //数组是空的 需要我们把bitmap的像素保存到该int数组当中
        mBitmap.getPixels(buffer,0,width,0,0,width-1,height-1);
        int[] ndkImage = NdkImageUtils.getNdkImage(buffer, width, height);
        Bitmap bitmap = Bitmap.createBitmap(ndkImage, width, height, Bitmap.Config.RGB_565);
        iv_image.setImageBitmap(bitmap);
        long endTimes=System.currentTimeMillis();
        Toast.makeText(this,"ndk处理"+(endTimes-currentTimes),Toast.LENGTH_SHORT).show();
    }
}
