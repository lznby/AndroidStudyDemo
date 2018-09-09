package com.lznby.bigdemo.lznby.fragment.modle;

import java.util.UUID;

/**
 * @author Lznby
 * @time 2018/9/4 9:45
 * Class Note:Crime对象
 */
public class Crime {
    private UUID mUUID;
    private String content;

    public Crime() {
        mUUID = UUID.randomUUID();
    }
    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID uuid) {
        mUUID = uuid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}