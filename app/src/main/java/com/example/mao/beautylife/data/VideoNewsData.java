package com.example.mao.beautylife.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Tecmry on 2018/3/23.
 */

public class VideoNewsData implements Serializable {
    private String content;
    private String title;
    private String videoUrl;
    private String videoTime;
    private String videoDetails;
    private String videoUserName;
    private List<String> videoUserTags;
    private String videoUserHeadUrl;

    public VideoNewsData(String title, String content, String videoUrl,
                         String videoTime, String videoUserName,String videoDetails,
                         List<String> videoUserTags, String videoUserHeadUrl) {
        this.content = content;
        this.title = title;
        this.videoUrl = videoUrl;
        this.videoTime = videoTime;
        this.videoUserName = videoUserName;
        this.videoUserTags = videoUserTags;
        this.videoUserHeadUrl = videoUserHeadUrl;
        this.videoDetails = videoDetails;
    }

    public String getVideoDetails() {
        return videoDetails;
    }

    public String getContent() {
        return content;
    }

    public List<String> getVideoUserTags() {
        return videoUserTags;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoTime() {
        return videoTime;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getVideoUserHeadUrl() {
        return videoUserHeadUrl;
    }

    public String getVideoUserName() {
        return videoUserName;
    }
}
