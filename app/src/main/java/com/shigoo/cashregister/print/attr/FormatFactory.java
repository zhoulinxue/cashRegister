package com.shigoo.cashregister.print.attr;

import com.shigoo.cashregister.print.content.Content;
import com.xgsb.datafactory.bean.Dishesbean;
import com.zx.network.Param;

import java.security.Key;

public class FormatFactory {
    /**
     * @param title
     * @return
     */
    public static Content getDefaultTitleContent(String title) {
        Content content = new Content();
        content.setContent("******************+" + title + "******************");
        PrintFormat format = new PrintFormat();
        format.setParameter(PrintFormat.ALIGN, PrintFormat.ALIGN_CENTER);
        format.setParameter(PrintFormat.BOLD, PrintFormat.BOLD_ENABLE);
        format.setParameter(PrintFormat.WIDTH_HEIGHT, PrintFormat.DOUBLE_HEIGHT);
        format.setParameter(PrintFormat.FONT, PrintFormat.FONT_SIZE_MEDIUM);
        content.setFormat(format);
        return content;
    }

    /**
     * @param storName
     * @return
     */
    public static Content getDefaultStorContent(String storName) {
        Content content = new Content();
        content.setContent(storName);
        PrintFormat format = new PrintFormat();
        format.setParameter(PrintFormat.BOLD, PrintFormat.BOLD_ENABLE);
        format.setParameter(PrintFormat.WIDTH_HEIGHT, PrintFormat.DOUBLE_WIDTH_HEIGHT);
        content.setFormat(format);
        return content;
    }

    ;

    public static Content getDishesContent(DishesPrint dishesbean) {
        Content content = new Content();
        content.setContent(dishesbean.getName() + "   " + "x" + dishesbean.getNum() + "    " + Param.Keys.RMB + dishesbean.getPrice());
        PrintFormat format = new PrintFormat();
        format.setParameter(PrintFormat.ALIGN, PrintFormat.ALIGN_LEFT);
        format.setParameter(PrintFormat.BOLD, PrintFormat.BOLD_DISABLE);
        format.setParameter(PrintFormat.WIDTH_HEIGHT, PrintFormat.NORMAL_WIDTH_HEIGHT);
        content.setFormat(format);
        return content;
    }
}
