package com.admaster.imageloader.cache.disk.naming;

/**
 * Created by lincoln on 16/9/21.
 */
public class HashCodeFileNameGenerator implements FileNameGenerator {
    @Override
    public String generate(String imageUri) {
        return String.valueOf(imageUri.hashCode());
    }
}
