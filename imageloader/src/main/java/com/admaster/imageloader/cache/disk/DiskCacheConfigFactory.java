package com.admaster.imageloader.cache.disk;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.admaster.imageloader.cache.disk.impl.LruDiskCache;
import com.admaster.imageloader.cache.disk.naming.FileNameGenerator;
import com.admaster.imageloader.cache.disk.naming.HashCodeFileNameGenerator;

import java.io.File;
import java.io.IOException;

/**
 * Created by lincoln on 16/9/21.
 */
public class DiskCacheConfigFactory {
    private static final String FilePath = Environment.getExternalStorageDirectory().getPath();

    private static final long DEFAULT_MAX_DISK_CACHE =  100*1024*1024;

    public static FileNameGenerator createFileNameGenerator(){
        return new HashCodeFileNameGenerator();
    }

    public static DiskCache createDefaultDiskCache(Context context, String fileName, long maxCache){
        String packageName = context.getPackageName();
        fileName = packageName+"/"+fileName;
        File file = new File(FilePath,fileName);
        Log.d("lincoln","directoryPath:"+file.getAbsolutePath());

        if (!file.exists()){
            Log.d("lincoln","DiskCache directory not Exist");
            boolean createDiskCache = file.mkdir();
            Log.d("lincoln","DiskCache create success :"+createDiskCache);
        }else{
            Log.d("lincoln","DiskCache exist");
        }


        LruDiskCache lruDiskCache;
        try {
            lruDiskCache= new LruDiskCache(file,new HashCodeFileNameGenerator(),maxCache);
            Log.d("lincoln","create  lruDiskCache file success");

//            return lruDiskCache;
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("lincoln","create  lruDiskCache file error");
        }
        return  new BaseDiskCache(file);
    }
}
