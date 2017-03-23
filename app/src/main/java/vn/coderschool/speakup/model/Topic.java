package vn.coderschool.speakup.model;

/**
 * Created by kenp on 23/03/2017.
 */

public class Topic {
    private String name;
    private int length;
    private int prepareTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getPrepareTime() {
        return prepareTime;
    }

    public void setPrepareTime(int prepareTime) {
        this.prepareTime = prepareTime;
    }
}
