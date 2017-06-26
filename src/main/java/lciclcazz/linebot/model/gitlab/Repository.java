package lciclcazz.linebot.model.gitlab;

/**
 * Created by IciclcAzz on 2017/06/26 18:01
 * Last Update 2017/06/26 18:01 | 1.
 */
public class Repository {
    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private String url;

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private String homepage;

    public String getHomepage() { return this.homepage; }

    public void setHomepage(String homepage) { this.homepage = homepage; }

    private String git_http_url;

    public String getGitHttpUrl() { return this.git_http_url; }

    public void setGitHttpUrl(String git_http_url) { this.git_http_url = git_http_url; }

    private String git_ssh_url;

    public String getGitSshUrl() { return this.git_ssh_url; }

    public void setGitSshUrl(String git_ssh_url) { this.git_ssh_url = git_ssh_url; }

    private int visibility_level;

    public int getVisibilityLevel() { return this.visibility_level; }

    public void setVisibilityLevel(int visibility_level) { this.visibility_level = visibility_level; }
}
