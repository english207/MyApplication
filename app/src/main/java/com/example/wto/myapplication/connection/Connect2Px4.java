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
    private static final int interval_ping = 20;
    private static final int interval = 50;
    private static final int retry_max = 5;
    private static final int retry_interval = 3000;
    private int retry = 0;
    private Socket server = null;
    private PrintWriter out = null;
    public static boolean isCanRun = false;
    private static final boolean isRetry = true;
    private Passage[] passages = {Passage.ONE, Passage.TWO, Passage.THREE, Passage.FOUR, Passage.FIVE, Passage.SIX, Passage.SEVEN, Passage.EIGHT};

    private Handler handler;

    public Connect2Px4(Handler handler) throws Exception
    {
        this.handler = handler;
    }

    private void init() throws Exception
    {
        String host = SendData.host;
        int port = SendData.port;
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
        while (isRetry)
        {
            try
            {
                init();
            }
            catch (Exception e) { e.printStackTrace(); }

            while (isCanRun)
            {
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
                    // Log.d(TAG, String.format("send_data is %s", send_data));
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
                    if (communicate_total % interval_ping == 0 && !ping()) {
                        init();
                        retry ++;
                        if (retry == retry_max) {       // when it on, stop the thread
                            break;
                        }
                    }
                }
                catch (Exception e) { e.printStackTrace(); }
            }

            try
            {
                Thread.sleep(retry_interval);
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
