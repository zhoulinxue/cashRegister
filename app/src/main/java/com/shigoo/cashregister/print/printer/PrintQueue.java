package com.shigoo.cashregister.print.printer;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

import com.shigoo.cashregister.mvp.presenter.PrintPresenter;
import com.shigoo.cashregister.print.inter.PrintProviderInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuguirong on 8/1/17.
 * <p/>
 * this is print queue.
 * you can simple add print bytes to queue. and this class will send those bytes to bluetooth device
 * 这是打印队列。
 *   你可以简单地添加打印字节来排队。 并且这个类将这些字节发送到蓝牙设备
 */
public class PrintQueue {

    /**
     * instance
     */
    private static PrintQueue mInstance;
    /**
     * context
     */
    private static Context mContext;
    /**
     * print queue
     */
    private ArrayList<byte[]> mQueue;
    /**
     * bluetooth adapter
     */
    private BluetoothAdapter mAdapter;


    private PrintQueue() {
    }

    public static PrintQueue getQueue(Context context) {
        if (null == mInstance) {
            mInstance = new PrintQueue();
        }
        if (null == mContext) {
            mContext = context;
        }
        return mInstance;
    }

    /**
     * add print bytes to queue. and call print
     *
     * @param bytes bytes
     */
    public synchronized void add(byte[] bytes, PrintProviderInterface providerInterface) {
        if (null == mQueue) {
            mQueue = new ArrayList<byte[]>();
        }
        if (null != bytes) {
            mQueue.add(bytes);
        }
        print(providerInterface);
    }

    /**
     * add print bytes to queue. and call print
     */
    public synchronized void add(List<byte[]> bytesList, PrintProviderInterface providerInterface) {
        if (null == mQueue) {
            mQueue = new ArrayList<byte[]>();
        }
        mQueue.clear();
        if (null != bytesList) {
            mQueue.addAll(bytesList);
        }
        print(providerInterface);
    }

    /**
     * print queue
     */
    public synchronized void print(final PrintProviderInterface print) {
        try {
            if (null == mQueue || mQueue.size() <= 0) {
                return;
            }
            print.setData(mQueue);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    print.startPrint(true);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
