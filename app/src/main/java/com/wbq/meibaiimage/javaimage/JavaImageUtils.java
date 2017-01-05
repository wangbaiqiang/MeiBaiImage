package com.wbq.meibaiimage.javaimage;

import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * 作者：${wbq} on 2017/1/4 15:54
 * 邮箱：wangbaiqiang@heigo.com.cn
 */

public class JavaImageUtils {

    //亮度
    private static final float brighness=0.2f;
    //对比度
    private static final float contrinst=2.0f;
    public static Bitmap getImageBitmap(Bitmap bitmap){
        int width=bitmap.getWidth();
        int height=bitmap.getHeight();
        Bitmap result= Bitmap.createBitmap(width,height, Bitmap.Config.RGB_565);
        int a,r,g,b;
        int bab= (int) (255*0.2f);
        float ca=1.0f+contrinst;
        //依次循环改变像素点
        for (int x=0;x<width;x++){
            for(int y = 0; y < height; y++) {
             int color=bitmap.getPixel(x,y);
                a= Color.alpha(color);
                r= Color.red(color);
                g= Color.green(color);
                b=Color.blue(color);
                //进行美白

                int ri=r+bab;
                int gi=g+bab;
                int bi=b+bab;
                //边界检测

                r=ri>255?255:(ri<0?0:ri);
                g=gi>255?255:(gi<0?0:gi);
                b=gi>255?255:(bi<0?0:bi);

                //扩大对比度
                ri=r-128;
                gi=g-128;
                bi=b-128;

                ri= (int) (ri*ca);
                gi= (int) (gi*ca);
                bi= (int) (bi*ca);

                ri=ri+128;
                gi=gi+128;
                bi=bi+128;

                r=ri>255?255:(ri<0?0:ri);
                g=gi>255?255:(gi<0?0:gi);
                b=gi>255?255:(bi<0?0:bi);

                result.setPixel(x,y,Color.argb(a,r,g,b));
            }
        }
        return result;
    }
}
