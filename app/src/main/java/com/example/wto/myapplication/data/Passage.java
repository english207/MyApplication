package com.example.wto.myapplication.data;

/**
 * Created by WTO on 2017/8/4 0004.
 *
 */
public enum Passage
{
    ONE(0, "左右", 1000, 2000, 50),
    TWO(1, "前后", 1000, 2000, 50),
    THREE(2, "转向", 1000, 2000, 50),
    FOUR(3, "油门", 1000, 2000, 0),
    FIVE(4, "未知", 1000, 2000, 50),
    SIX(5, "未知", 1000, 2000, 50),
    SEVEN(6, "未知", 1000, 2000, 50),
    EIGHT(7, "未知", 1000, 2000, 50);

    private int num;
    private String name;
    private int min;
    private int max;
    private int init;

    Passage(int num, String name, int min, int max, int init)
    {
        this.num = num;
        this.name = name;
        this.min = min;
        this.max = max;
        this.init = init;
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

    public int getInit() {
        return init;
    }

    public void setInit(int init) {
        this.init = init;
    }
}
