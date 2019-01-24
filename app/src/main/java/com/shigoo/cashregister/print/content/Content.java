package com.shigoo.cashregister.print.content;

import com.shigoo.cashregister.print.attr.PrintFormat;

public class Content {
    private String content;
    private PrintFormat format;
    private int enterNum=1;

    public int getEnterNum() {
        return enterNum;
    }

    public void setEnterNum(int enterNum) {
        this.enterNum = enterNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PrintFormat getFormat() {
        return format;
    }

    public void setFormat(PrintFormat format) {
        this.format = format;
    }
}
