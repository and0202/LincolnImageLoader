package lincoln.imageframework.lincoln.core.cache.disk.naming;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;

/**
 * Created by lincoln on 16/9/21.
 */
public class HashCodeFileNameGenerator implements FileNameGenerator {
    @Override
    public String generate(String imageUri) {
        return String.valueOf(imageUri.hashCode());
    }
}
