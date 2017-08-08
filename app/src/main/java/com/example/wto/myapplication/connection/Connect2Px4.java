package com.example.wto.myapplication.connection;

import android.util.Log;
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
    private static final int interval = 2;
    private static final int retry_max = 5;
    private int retry = 0;
    private String host;
    private Integer port;
    private Socket server = null;
    private PrintWriter out = null;

    public Connect2Px4(String host, Integer port) throws Exception
    {
        this.host = host;
        this.port = port;
    }

    private void init() throws Exception
    {
        try
        {
            close(server);
            close(out);
            server = new Socket(host, port);
            out = new PrintWriter(server.getOutputStream());
        }
        catch (Exception e)
        {
            Log.e(TAG, "create socket connect is fail", e);
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

        while (true)
        {
            Log.e(TAG, "run");
            try
            {
                StringBuilder sb = new StringBuilder();
                for (int size : SendData.mapping)
                {
                    int passage_data = size * 10 + 1000;
                    sb.append(passage_data);
                    sb.append(",");
                }
                String send_data = sb.substring(0, sb.lastIndexOf(","));
//                Log.d(TAG, String.format("send_data is %s", send_data));
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
