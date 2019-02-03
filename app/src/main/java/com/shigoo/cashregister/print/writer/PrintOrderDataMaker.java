package com.shigoo.cashregister.print.writer;

import android.content.Context;

import com.xgsb.datafactory.bean.PrintOrderbean;

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

    public PrintOrderDataMaker( Context mContext,PrintOrderbean mData) {
        super(mData, mContext);
    }

    public PrintOrderDataMaker(Context mContext, PrintOrderbean mData, int width, int height) {
        super(mData, mContext, width, height);
    }

    @Override
    public List<byte[]> getHeaderPrintData() {
        return null;
    }

    @Override
    public List<byte[]> getContentPrintData() {
        return null;
    }

    @Override
    public List<byte[]> getFooterPrintData() {
        return null;
    }

}
