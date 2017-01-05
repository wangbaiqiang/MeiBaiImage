#include <string.h>
#include <stdlib.h>
#include <jni.h>

    JNIEXPORT jintArray JNICALL Java_com_wbq_meibaiimage_ndkimage_NdkImageUtils_getNdkImage(JNIEnv *env, jclass jclaz, jintArray buffer, jint width, jint height)
      {

        //int[] array=new int[size];
        jint * source=(*env)->GetIntArrayElements(env,buffer,0);
        float brightness=0.2f;
        float contrainst=0.2f;
          int a,r,g,b;
        int bab= (int) (255*0.2f);
        float ca=1.0f+contrainst;
        int size=width*height;
        //依次循环改变像素点
        int x=0,y=0;
        for (x=0;x<width;x++){
            for(y = 0; y < height; y++) {
             int color=source[width*y+x];
                a= color >> 24;
                r= (color >> 16) & 0xFF;
                g= (color >> 8) & 0xFF;
                b= color & 0xFF;
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

                //左移相或 Color的类中有具体的实现
                source[width*y+x]= (a << 24) | (r << 16) | (g << 8) | b;
//                result.setPixel(x,y,Color.argb(a,r,g,b));
            }
        }
        //做两件事：1.source保存到jintArray当中 2.释放资源
        jintArray result=(*env)->NewIntArray(env,size);
        (*env)->SetIntArrayRegion(env,result,0,size,source);
        (*env)->ReleaseIntArrayElements(env,buffer,size,0);
        return result;
      }