package com.zx.network.OKHttp;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Name: ProgressResponseBody
 * Author: zhouxue
 * Email: 194093798@qq.com
 * Comment: //TODO
 * Date: 2018-11-15 16:33
 */
public class ProgressResponseBody extends ResponseBody {
    private ResponseBody responseBody;
    DownloadProgressListener mListener;
    private BufferedSource bufferedSource;

    public ProgressResponseBody(ResponseBody responseBody, DownloadProgressListener mListener) {
        this.responseBody = responseBody;
        this.mListener = mListener;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        try {
            return responseBody.contentLength();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            try {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) {
            long bytesReaded = 0;
            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
                //实时发送总字节和当前已读取的字节
                if(mListener!=null){
                    mListener.onProcess(contentLength(),bytesReaded);
                }
                return bytesRead;
            }
        };
    }
}
