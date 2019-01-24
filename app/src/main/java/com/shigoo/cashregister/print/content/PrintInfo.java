package com.shigoo.cashregister.print.content;

import java.util.List;

public class PrintInfo {
    private Content title;
    private List<Content> content;

    public PrintInfo(Content title, List<Content> content) {
        this.title = title;
        this.content = content;
    }

    public Content getTitle() {
        return title;
    }

    public void setTitle(Content title) {
        this.title = title;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }
}
