package lincoln.imageframework.lincoln.core.cache.disk;

import android.os.Environment;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;

import java.io.File;

import lincoln.imageframework.lincoln.core.cache.disk.naming.HashCodeFileNameGenerator;

/**
 * Created by lincoln on 16/9/21.
 */
public class DiskCacheConfigFactory {
    private static final String fileNameDefault="lincoln";
    private static final String FilePath = Environment.getExternalStorageDirectory().getPath();


    public static FileNameGenerator createFileNameGenerator(){
        return new HashCodeFileNameGenerator();
    }

    public static DiskCache createDefaultDiskCache(){
//        Log.d("lincoln","filePath:"+FilePath);
        File file = new File(FilePath,fileNameDefault);
//        Log.d("lincoln","directoryPath:"+file.getAbsolutePath());

        if (!file.exists()){
            Log.d("lincoln","DiskCache directory not Exist");
            boolean createDiskCache = file.mkdir();
            Log.d("lincoln","DiskCache create success :"+createDiskCache);
        }else{
            Log.d("lincoln","DiskCache exist");
        }
        return  new BaseDiskCache(file);
    }
}
