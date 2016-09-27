package com.admaster.imageloader.request;

/**
 * Created by lincoln on 16/9/23.
 */
public class DisplayConfigFactory {

    /**
     * 获取配置实体类
     */
    private static final int IMAGE_WIDTH = 400;
    private static final int IMAGE_HEIGHT = 400;

    public static DisplayImageConfig getDefaultDisplayImageConfig() {
        DisplayImageConfig displayImageConfig = new DisplayImageConfig(IMAGE_WIDTH, IMAGE_HEIGHT);
        return displayImageConfig;
    }


}
