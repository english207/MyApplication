package com.example.wto.myapplication.data;

/**
 * Created by WTO on 2017/8/4 0004.
 *
 */
public class SendData
{
    // 映射
    public static int[] mapping = new int[Passage.values().length];
    static
    {
        Passage[] passages = Passage.values();
        for (Passage passage : passages)
        {
            int index = passage.getNum();
            int init = passage.getInit();
            mapping[index] = init;
        }
    }

}
