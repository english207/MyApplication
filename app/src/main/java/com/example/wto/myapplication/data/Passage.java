package com.example.wto.myapplication.data;

/**
 * Created by WTO on 2017/8/4 0004.
 *
 */

public enum Passage
{
    ONE(1, "左右", 0, 2000),
    TWO(2, "前后", 0, 2000),
    THREE(3, "转向", 0, 2000),
    FOUR(4, "油门", 0, 2000),
    FIVE(5, "未知", 0, 2000),
    SIX(6, "未知", 0, 2000),
    SEVEN(7, "未知", 0, 2000),
    EIGHT(8, "未知", 0, 2000);

    private int num;
    private String name;
    private int min;
    private int max;

    Passage(int num, String name, int min, int max)
    {
        this.num = num;
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
