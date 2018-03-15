package com.example.mao.beautylife.data;

/**
 * Created by -- Mao on 2017/12/31.
 */

public class VideoData {

    private String name;
    private String title;
    private String imageUrl;
    private int num;
    private String videoUrl;
    private String videoTime;

    public VideoData(String name, String title, String imageUrl, int num, String videoUrl, String videoTime) {
        this.name = name;
        this.title = title;
        this.imageUrl = imageUrl;
        this.num = num;
        this.videoUrl = videoUrl;
        this.videoTime = videoTime;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getNum() {
        return num;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoTime() {
        return videoTime;
    }
}
