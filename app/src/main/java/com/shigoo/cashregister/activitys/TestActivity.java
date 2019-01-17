//package com.shigoo.cashregister.activitys;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.hardware.usb.UsbDevice;
//import android.hardware.usb.UsbDeviceConnection;
//import android.hardware.usb.UsbManager;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
//import com.xgsb.cashregister.mvp.contacts.CashLoginContact;
//import com.xgsb.cashregister.mvp.presenter.CashLoginPresenter;
//import com.zx.api.api.utils.AppLog;
//import com.zx.api.api.utils.LangUtils;
//import com.zx.mvplibrary.MvpActivity;
//import com.zx.pictruemodel.ImageLoader;
//import com.zx.usblib.UsbConnectionManager;
//import com.zx.usblib.UsbTransferManager;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
//
//public class TestActivity extends MvpActivity<CashLoginPresenter> implements CashLoginContact.view {
//    private final String TAG = TestActivity.class.getSimpleName();
//    @BindView(R.id.lan_btn)
//    Button mTestBtn;
//    @BindView(R.id.download_btn)
//    Button mDownLoadBtn;
//    @BindView(R.id.test_scale_img)
//    SubsamplingScaleImageView mImageView;
//    @BindView(R.id.test_img)
//    ImageView imageView;
//    private String TEST_IMG = "http://pic1.win4000.com/wallpaper/c/58492dd7d7dba.jpg";
//    private String TEST_DOWN_LOAD = "/big_buck_bunny.mp4";
//    private UsbConnectionManager manager;
//    private UsbTransferManager transferManager;
//
//    @Override
//    protected CashLoginPresenter onCtreatPresenter() {
//        return new CashLoginPresenter(this);
//    }
//
//    @Override
//    protected int initLayout() {
//        return R.layout.test_main;
//    }
//
//    @Override
//    protected void onCreateView() {
//        ButterKnife.bind(this);
//    }
//
//    @Override
//    protected void onInitData(Bundle savedInstanceState) {
//        manager = new UsbConnectionManager(this);
//        manager.registerPermission(mReceiver);
//        transferManager = new UsbTransferManager(this);
//        transferManager.setOnRecieveListener(new UsbTransferManager.OnRecieveListener() {
//            @Override
//            public void onRecieve(byte[] bytes) {
//
//
//            }
//        });
//
////        mPresenter.doNetWork(ApiServer.MAIN_TEST_URL, Param.Keys.id, Param.Keys.value);
//        ImageLoader.with(this).from(TEST_IMG).placeHolder(R.mipmap.home_pic_logo).into(mImageView);
////        ImageLoader.with(this).from(TEST_IMG).placeHolder(R.mipmap.ic_launcher_round).onError(R.mipmap.ic_launcher).into(imageView);
//
//        singleClickOnMinutes(mTestBtn, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (getCurrentLan() == LangUtils.SIMPLE_CHINESE) {
//                    setLanguage(LangUtils.ENGLISH);
//                } else {
//                    setLanguage(LangUtils.SIMPLE_CHINESE);
//                }
//            }
//        });
//
//    }
//
//    @OnClick({R.id.download_btn})
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//            case R.id.download_btn:
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        List<UsbDevice> devices = manager.getDeviceList();
//                        if (devices != null && devices.size() != 0) {
//                            for (UsbDevice device : devices) {
//                                if (49948 == device.getProductId()) {
//                                    manager.execAttached(device);
//                                }
//                            }
//                        }
//                    }
//                }).start();
////                File outputFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
////                        "test" + ".mp4");
////                DownLoadManager.getInstance().downLoad(TEST_DOWN_LOAD, outputFile, new DownloadProgressListener() {
////                    @Override
////                    public void onSuc() {
////
////                    }
////
////                    @Override
////                    public void onError() {
////
////                    }
////
////                    @Override
////                    public void onProcess(long total, long process) {
////                        AppLog.print(TAG, process + " TOTAL:  " + total);
////                        showToast(process + "");
////                    }
////                });
//                break;
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        manager.unregister();
//    }
//
//
//// --- BroadcastReceiver ---
//    /**
//     * --- BroadcastReceiver ---
//     */
//    BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            AppLog.i("onReceive ", action);
//            Toast.makeText(TestActivity.this, action, Toast.LENGTH_SHORT).show();
//            UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
//            if (device == null) return;
//            if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
//                manager.execAttached(device);
//            } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
//                manager.receiveDetached(device);
//            } else if (UsbConnectionManager.ACTION_USB_PERMISSION.equals(action)) {
//                synchronized (this) {
//                    boolean perm = intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false);
//                    if (perm) {
//                        UsbDeviceConnection connection = manager.openDevice(device);
//                        if (connection != null) {
//                            transferManager.setConnection(connection);
//                        }
//                    }
//                }
//            }
//        }
//    };
//
//    @Override
//    public void loginCallbak(String code) {
//
//    }
//}
