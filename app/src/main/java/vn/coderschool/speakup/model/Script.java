package vn.coderschool.speakup.model;

import org.parceler.Parcel;

@Parcel
public class Script {
    private String origin;
    private String translated;
    private int role;
    private int second;

    public Script() {

    }

    public String getOrigin() {
        return origin;
    }

    public String getTranslated() {
        return translated;
    }

    public int getRole() {
        return role;
    }

    public int getSecond() {
        return second;
    }
}
