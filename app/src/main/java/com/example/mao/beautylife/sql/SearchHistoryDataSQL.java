package com.example.mao.beautylife.sql;

import org.litepal.crud.DataSupport;

/**
 * Created by -- Mao on 2017/12/6.
 */

public class SearchHistoryDataSQL extends DataSupport {


    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
