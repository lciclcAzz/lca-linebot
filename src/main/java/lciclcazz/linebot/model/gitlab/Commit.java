package lciclcazz.linebot.model.gitlab;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by IciclcAzz on 2017/06/26 18:02
 * Last Update 2017/06/26 18:02 | 1.
 */
public class Commit {

    private String id;

    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    private String message;

    public String getMessage() { return this.message; }

    public void setMessage(String message) { this.message = message; }

    private Date timestamp;

    public Date getTimestamp() { return this.timestamp; }

    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }

    private String url;

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    private Author author;

    public Author getAuthor() { return this.author; }

    public void setAuthor(Author author) { this.author = author; }

    private ArrayList<String> added;

    public ArrayList<String> getAdded() { return this.added; }

    public void setAdded(ArrayList<String> added) { this.added = added; }

    private ArrayList<String> modified;

    public ArrayList<String> getModified() { return this.modified; }

    public void setModified(ArrayList<String> modified) { this.modified = modified; }

    private ArrayList<Object> removed;

    public ArrayList<Object> getRemoved() { return this.removed; }

    public void setRemoved(ArrayList<Object> removed) { this.removed = removed; }
}
