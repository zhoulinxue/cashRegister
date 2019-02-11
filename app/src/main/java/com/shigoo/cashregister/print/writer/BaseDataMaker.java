package com.shigoo.cashregister.print.writer;

import android.content.Context;

import com.xgsb.datafactory.bean.BasePrintbean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDataMaker<P extends BasePrintbean> implements PrintDataMaker {
    ArrayList<byte[]> data = new ArrayList<>();
    protected P mData;
    protected PrinterWriter mWriter;
    protected Context mContext;
    protected int width;
    protected int height;

    public BaseDataMaker(P mData, Context mContext) {
        this(mData, mContext, PrinterWriter58mm.TYPE_58, PrinterWriter.HEIGHT_PARTING_DEFAULT);
    }

    public BaseDataMaker(P mData, Context mContext, int width, int height) {
        this.mData = mData;
        this.mContext = mContext;
        this.width = width;
        this.height = height;
        initWriter(PrinterWriter58mm.TYPE_58);
    }

    public void initWriter(int type) {
        try {
            mWriter = type == PrinterWriter58mm.TYPE_58 ? new PrinterWriter58mm(height, width) : new PrinterWriter80mm(height, width);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<byte[]> getPrintData() {
        //打印默认头部信息
        writeHeaderPrintData();
        //打印默认列表信息
        writeContentPrintData();
        //打印底部信息
        writeFooterPrintData();
        try {
            data.add(mWriter.getDataAndClose());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
