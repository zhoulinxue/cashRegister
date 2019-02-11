package com.shigoo.cashregister.print.writer;

import java.util.List;

/**
 * Print
 * * Created by liugruirong on 2017/8/3.
 */

public interface PrintDataMaker {
    List<byte[]> getPrintData();

    void writeHeaderPrintData();

    void writeContentPrintData();

    void writeFooterPrintData();
}
