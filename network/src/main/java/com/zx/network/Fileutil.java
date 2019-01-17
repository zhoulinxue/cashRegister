package com.zx.network;

import com.zx.api.api.utils.AppLog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/**
 * Name: Fileutil
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 16:43
 */
public class Fileutil {
    /**
     * 写入文件
     * 断点续传
     * @param in
     * @param file
     */
    public static void writeFile(InputStream in, File file) throws IOException {
        RandomAccessFile savedFile = null;
        long ltest = 0;
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        if (file != null && file.exists()){
            ltest = file.length();
        }
        if (in != null){
            savedFile = new RandomAccessFile(file , "rw");
            savedFile.seek(ltest);
            byte[] buffer = new byte[1024 * 128];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                savedFile.write(buffer, 0, len);
            }
            in.close();
        }else {
          AppLog.print("writeFile"+"写文件失败");
        }
    }

    /**
     * 读取文件长度
     */
    public static long readFile(File file){
        if (file != null && file.exists()){
            return file.length();
        }else {
            return 0;
        }
    }
}
