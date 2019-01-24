package com.shigoo.cashregister.print;

import android.content.Context;

import com.shigoo.cashregister.print.attr.Address;
import com.shigoo.cashregister.print.attr.ESC_SYTLE;
import com.shigoo.cashregister.print.attr.PrintFormat;
import com.shigoo.cashregister.print.inter.PrintProviderInterface;
import com.shigoo.cashregister.print.printer.PrinterProvider;
import com.zx.api.api.utils.AppUtil;

import java.util.HashMap;
import java.util.Map;

public class PrintManager {
    private static Context mContext;
    private static PrintManager mPrintManager;
    private Map<String, Address> printerProviderMap = new HashMap<>();

    public static PrintManager getInstance() {
        if (mPrintManager == null) {
            mPrintManager = new PrintManager();
        }
        return mPrintManager;
    }

    public void initPrint(Context context) {
        mContext = context;
    }

    public void addPrinterAddress(String printerName, Address address) {
//        //收银机器
//        printerProviderMap.put(ESC_SYTLE.PRINT.CASHR_EGISTER,new Address("192.168.188.250",9100));
//        //点单机
//        printerProviderMap.put(ESC_SYTLE.PRINT.ORDER_SHEET,new Address("192.168.188.251",9100));
//        // 厨房
//        printerProviderMap.put(ESC_SYTLE.PRINT.KITCHEN,new Address("192.168.188.252",9100));
        printerProviderMap.put(printerName, address);
    }

    public Address getPrinterAddress(String name) {
        return printerProviderMap.get(name);
    }


    /**
     * 初始化 设置 ip，端口，wifi，打印。。socket通讯
     */
    public PrintProviderInterface creatWifiPrint(Address address) {
        PrinterProvider print = new PrinterProvider(mContext);
        print.setPrinterConfig(address.getIp(), address.getPort() + "", "GBK");
        PrintProviderInterface printerdevice = print.CreatePrint(ESC_SYTLE.MODE_PRINT.WIFI_PRINT);
        printerdevice.InitPrint();
        printerdevice.preparePrint();
        return printerdevice;
    }

    public void startPrint(final PrintProviderInterface printDevice, final boolean cutpaper) {
        if (cutpaper) {
            printDevice.feedPaper(1, 0);
            //printerdevice.cutPaper();
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    printDevice.startPrint(cutpaper);
                    printDevice.releasePrint();
                }

            }.start();
        }
    }
}
