package com.shigoo.cashregister.print.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class FileUtils {

    //文件存储根目录
    public String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }
}
