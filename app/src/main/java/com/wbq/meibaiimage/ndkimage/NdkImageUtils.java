package com.wbq.meibaiimage.ndkimage;

/**
 * 作者：${wbq} on 2017/1/4 16:30
 * 邮箱：wangbaiqiang@heigo.com.cn
 */

public class NdkImageUtils {
    public static native int[] getNdkImage(int[] buffer,int width,int height);

}
