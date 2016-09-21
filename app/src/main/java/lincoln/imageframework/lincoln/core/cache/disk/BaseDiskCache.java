package lincoln.imageframework.lincoln.core.cache.disk;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import lincoln.imageframework.lincoln.core.cache.util.IoUtils;

/**
 * Created by lincoln on 16/9/21.
 */
public class BaseDiskCache  implements DiskCache{
    private static final int DEFAULT_BUFFER_SIZE = 32*1024;//32kb
    public static final Bitmap.CompressFormat DEFAULT_COMPRESS_FORMAT = Bitmap.CompressFormat.PNG;
    public static final int DEFAULT_COMPRESS_QUALITY = 100;


    private File cacheFile;
    private FileNameGenerator fileNameGenerator;
    private static final String TEMP_IMAGE_POSTFIX = ".tmp";
    private static int bufferSize =DEFAULT_BUFFER_SIZE ;
    protected Bitmap.CompressFormat compressFormat = DEFAULT_COMPRESS_FORMAT;
    protected int compressQuality = DEFAULT_COMPRESS_QUALITY;

    public BaseDiskCache(File cacheFile) {
        this.cacheFile = cacheFile;
        this.fileNameGenerator = DiskCacheConfigFactory.createFileNameGenerator();
    }

    @Override
    public void clear() {
        File[] files = cacheFile.listFiles();
        if (files != null){
            for (File file: files){
                file.delete();
            }
        }
    }

    @Override
    public File getDirectory() {
        return cacheFile;
    }

    @Override
    public File get(String imageUrl) {
        return getFile(imageUrl);
    }

    @Override
    public boolean save(String imageUrl, Bitmap bitmap) throws IOException {
        File imageFile = getFile(imageUrl);
        File tmpFile = new File(imageFile.getAbsoluteFile()+TEMP_IMAGE_POSTFIX);
        OutputStream os = new BufferedOutputStream(new FileOutputStream(tmpFile),bufferSize);
        boolean savedSuccessfully = false;

        try {
            savedSuccessfully = bitmap.compress(compressFormat,compressQuality,os);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            IoUtils.closeSilently(os);
            if (savedSuccessfully && !tmpFile.renameTo(imageFile)){
                savedSuccessfully = false;
            }
            if (!savedSuccessfully){
                tmpFile.delete();
            }
        }
//        bitmap.recycle();
        return savedSuccessfully;
    }

    @Override
    public boolean remove(String imageUrl) {
        return getFile(imageUrl).delete();
    }

    @Override
    public void close() {

    }

    public File getFile(String imageUrl){
        String fileName = fileNameGenerator.generate(imageUrl);
        File dir = cacheFile;
        if (!cacheFile.exists() && !cacheFile.mkdir()){
            dir =  cacheFile;
        }

        return  new File(dir,fileName);
    }
}
