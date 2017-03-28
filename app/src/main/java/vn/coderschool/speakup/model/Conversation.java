package vn.coderschool.speakup.model;

import org.parceler.Parcel;

import java.util.Date;
import java.util.List;

@Parcel
public class Conversation {
    public String id;

    public String name;

    public String description;

    // Conversation length in minute
    public String length;

    // Time to prepare before conversation in second
    public String prepare;

    public List<String> participantsId;

    public String topic;

    public Date createdAt;

    public String videoUrl;

    public String audioUrl;

    public float rating;

    public boolean publish;

    public int viewsCount;

    public int commentCount;

    public List<Script> scripts;

    public Conversation() {

    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Script> getScripts() {
        return scripts;
    }
}
