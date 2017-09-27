package com.example.wto.myapplication.connection;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.example.wto.myapplication.data.Passage;
import com.example.wto.myapplication.data.SendData;

import java.io.Closeable;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by WTO on 2017/8/7 0007.
 *
 */
public class Connect2Px4 implements Runnable
{
    private static final String TAG = "Connect2Px4";
    private int communicate_total = 0;
    private static final int interval = 50;
    private static final int retry_max = 5;
    private int retry = 0;
    private String host;
    private Integer port;
    private Socket server = null;
    private PrintWriter out = null;
    private boolean isCanRun = false;
    private Passage[] passages = {Passage.ONE, Passage.TWO, Passage.THREE, Passage.FOUR, Passage.FIVE, Passage.SIX, Passage.SEVEN, Passage.EIGHT};

    private Handler handler;

    public Connect2Px4(String host, Integer port, Handler handler) throws Exception
    {
        this.host = host;
        this.port = port;
        this.handler = handler;
    }

    private void init() throws Exception
    {
        try
        {
            close(server);
            close(out);
            server = new Socket(host, port);
            out = new PrintWriter(server.getOutputStream());

            Bundle bundle = new Bundle();
            bundle.putString("connect2Px4", String.format("connected success host is [%s]", host));
            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

            isCanRun = true;
        }
        catch (Exception e)
        {
            Log.e(TAG, "create socket connect is fail", e);
            Bundle bundle = new Bundle();
            bundle.putString("connect2Px4", String.format("connected fail host is [%s]", host));
            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

            throw e;
        }
    }

    @Override
    public void run()
    {
        try
        {
            init();
        }
        catch (Exception e) { e.printStackTrace(); }

        while (isCanRun)
        {
            Log.e(TAG, "run");
            try
            {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < SendData.mapping.length; i++)
                {
                    int passage_data = SendData.mapping[i] * passages[i].getRetractable() + 1000;
                    sb.append(passage_data);
                    sb.append(",");
                }
                String send_data = sb.substring(0, sb.lastIndexOf(","));
                Log.d(TAG, String.format("send_data is %s", send_data));
//                StringBuilder sb = new StringBuilder();
//                for (int size : SendData.mapping)
//                {
//                    int passage_data = size * 10 + 1000;
//                    sb.append(passage_data);
//                    sb.append(",");
//                }
//                String send_data = sb.substring(0, sb.lastIndexOf(","));
                out.println(send_data);
                out.flush();
            }
            catch (Exception e) { Log.e(TAG, "send_data is fail", e); }

            communicate_total ++;
            try
            {
                Thread.sleep(interval);
            }
            catch (Exception e) { e.printStackTrace(); }

            try
            {
                if (communicate_total % 200 == 0 && !ping()) {
                    init();
                    retry ++;
                    if (retry == retry_max) {       // when it on, stop the thread
                        break;
                    }
                }
            }
            catch (Exception e) { e.printStackTrace(); }
        }
    }

    private boolean ping()
    {
        boolean flag = false;
        try
        {
            if (server != null) {
                // server.sendUrgentData(0xFF);
            }
            flag = true;
        }
        catch (Exception e)
        {
            Log.e(TAG, "socket connect is lose");
        }
        return flag;
    }

    private void close(Closeable closeable)
    {
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (Exception e) { Log.e(TAG, "socket connect is lose"); }
        }
    }


}
