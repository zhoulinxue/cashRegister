package com.shigoo.cashregister.print.attr;

import com.shigoo.cashregister.print.content.Content;
import com.xgsb.datafactory.bean.Dishesbean;
import com.zx.api.api.utils.AppLog;
import com.zx.network.Param;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

public class FormatFactory {
    /**
     * @param title
     * @return
     */
    public static Content getDefaultTitleContent(String title) {
        Content content = new Content();
        content.setContent(title);
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

    public static Content getDishesNameContent(String name) {
        Content content = new Content();
        content.setContent(name);
        PrintFormat format = new PrintFormat();
        format.setParameter(PrintFormat.ALIGN, PrintFormat.ALIGN_LEFT);
        format.setParameter(PrintFormat.BOLD, PrintFormat.BOLD_DISABLE);
        format.setParameter(PrintFormat.WIDTH_HEIGHT, PrintFormat.NORMAL_WIDTH_HEIGHT);
        content.setFormat(format);
        return content;
    }

    /**
     * 打印名字
     *
     * @param devider
     * @return
     */
    public static Content getDefaultDividerContent(String devider) {
        Content content = new Content();
        content.setContent(devider);
        PrintFormat format = new PrintFormat();
        format.setParameter(PrintFormat.ALIGN, PrintFormat.ALIGN_CENTER);
        format.setParameter(PrintFormat.BOLD, PrintFormat.BOLD_DISABLE);
        format.setParameter(PrintFormat.WIDTH_HEIGHT, PrintFormat.NORMAL_WIDTH_HEIGHT);
        format.setParameter(PrintFormat.FONT, PrintFormat.FONT_SIZE_MEDIUM);
        content.setFormat(format);
        return content;
    }

    /**
     * 打印数量
     *
     * @param num
     * @return
     */
    public static Content getDishesNumContent(String num) {
        Content content = new Content();
        content.setContent(num);
        PrintFormat format = new PrintFormat();
        format.setParameter(PrintFormat.ALIGN, PrintFormat.ALIGN_LEFT);
        format.setParameter(PrintFormat.BOLD, PrintFormat.BOLD_DISABLE);
        format.setParameter(PrintFormat.WIDTH_HEIGHT, PrintFormat.NORMAL_WIDTH_HEIGHT);
        content.setFormat(format);
        return content;
    }

    /**
     * 打印数量
     *
     * @return
     */
    public static Content getDishesPriceContent(String price) {
        Content content = new Content();
        content.setContent(price);
        PrintFormat format = new PrintFormat();
        format.setParameter(PrintFormat.ALIGN, PrintFormat.ALIGN_LEFT);
        format.setParameter(PrintFormat.BOLD, PrintFormat.BOLD_DISABLE);
        format.setParameter(PrintFormat.WIDTH_HEIGHT, PrintFormat.NORMAL_WIDTH_HEIGHT);
        content.setFormat(format);
        return content;
    }

    public static Line getDishesLine(Dishesbean dishes) {
        Line line = new Line();
        line.setTopNum(1);
        line.setBottomNum(1);
        List<Colum> colums = new ArrayList<>();
        //打印菜品名称
        Content nameContent = getDishesNameContent(dishes.getNotNullName());
        //打印菜品数量
        Content numContent = getDishesNumContent("x" + dishes.getLocal_num());
        //打印价格
        Content priceContent = getDishesPriceContent(dishes.getShowPrice());
        colums.add(new Colum(nameContent, 5));
//        colums.add(new Colum(numContent, 2));
//        colums.add(new Colum(priceContent, 3));
        line.setColum(colums);
        return line;
    }
}
