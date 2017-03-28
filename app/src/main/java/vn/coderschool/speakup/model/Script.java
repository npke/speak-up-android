package vn.coderschool.speakup.model;

import org.parceler.Parcel;

@Parcel
public class Script {
    public String origin;
    public String translated;
    public int role;

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
}
