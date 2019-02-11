package com.shigoo.cashregister.print.writer;

import android.content.Context;

import com.shigoo.cashregister.print.attr.FormatFactory;
import com.xgsb.datafactory.bean.Dishesbean;
import com.xgsb.datafactory.bean.PrintOrderbean;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/**
 * 测试数据生成器
 * Created by liuguirong on 8/1/17.
 */

public class PrintOrderDataMaker extends BaseDataMaker<PrintOrderbean> implements PrintDataMaker {

    public PrintOrderDataMaker(Context mContext, PrintOrderbean mData) {
        super(mData, mContext);
    }

    public PrintOrderDataMaker(Context mContext, PrintOrderbean mData, int width, int height) {
        super(mData, mContext, width, height);
    }

    @Override
    public void writeHeaderPrintData() {
        try {
            mWriter.setAlignCenter();
            data.add(mWriter.getDataAndReset());
            mWriter.printLineFeed();
            mWriter.setAlignCenter();
            mWriter.setEmphasizedOn();
            mWriter.setFontSize(1);
            mWriter.print(mData.getTitle());
            mWriter.printLineFeed();
            mWriter.setEmphasizedOff();
            mWriter.printLineFeed();

            mWriter.printLineFeed();
            mWriter.setAlignLeft();
            mWriter.setEmphasizedOn();
            mWriter.setFontSize(2);
            mWriter.print("台号: " + mData.getTableNumber());
            mWriter.setEmphasizedOff();
            mWriter.printLineFeed();

            mWriter.printLineFeed();
            mWriter.setAlignLeft();
            mWriter.setFontSize(0);
            mWriter.print("日期: " + mData.getDate());
            mWriter.printLineFeed();

            mWriter.print("结算时间: " + mData.getPayTime());
            mWriter.printLineFeed();
            mWriter.print("点单员: " + mData.getOrderName());
            mWriter.printLineFeed();
            mWriter.printLine();
            mWriter.printLineFeed();

            mWriter.print("单号：" + mData.getBillCode());
            mWriter.printLineFeed();
            mWriter.print("销售员：" + mData.getSellerName());
            mWriter.printLineFeed();
            mWriter.print("流水号：");
            mWriter.printLineFeed();
            mWriter.print("收银员：" + mData.getOrderName());
            mWriter.printLineFeed();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeContentPrintData() {
        try {
            mWriter.print("菜品信息");
            mWriter.printLineFeed();
            mWriter.setAlignCenter();
            mWriter.printInOneLine("菜名", "数量", "单价", 0);
            mWriter.printLineFeed();
            for (int i = 0; i < mData.getDishes().size(); i++) {
                mWriter.setAlignLeft();
                Dishesbean dishesbean = mData.getDishes().get(i);
                mWriter.printInOneLine(FormatFactory.subStr(dishesbean.getNotNullName()+"                  ",15), "X" + dishesbean.getLocal_num(), "￥" + dishesbean.getShowPrice(), 0);
                mWriter.printLineFeed();
            }

            mWriter.setAlignLeft();
            mWriter.printInOneLine("应收金额：", "￥" + mData.getFinalyPrice()
                    , 0);
            mWriter.printLineFeed();

            mWriter.setAlignLeft();
            mWriter.printInOneLine("已收金额：", "￥" + "0.00"
                    , 0);
            mWriter.printLineFeed();


            mWriter.setAlignLeft();
            mWriter.printInOneLine("总计：", "￥" + mData.getSalePrice(), 0);
            mWriter.printLineFeed();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeFooterPrintData() {
        try {
            mWriter.printLine();
            mWriter.printLineFeed();
            mWriter.setAlignCenter();
            mWriter.print("谢谢惠顾，欢迎再次光临！");
            mWriter.printLineFeed();
            mWriter.printLineFeed();
            mWriter.printLineFeed();
            mWriter.feedPaperCutPartial();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
