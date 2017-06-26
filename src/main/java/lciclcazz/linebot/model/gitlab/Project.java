package lciclcazz.linebot.model.gitlab;

/**
 * Created by IciclcAzz on 2017/06/26 18:00
 * Last Update 2017/06/26 18:00 | 1.
 */
public class Project {

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private String description;

    public String getDescription() { return this.description; }

    public void setDescription(String description) { this.description = description; }

    private String web_url;

    public String getWebUrl() { return this.web_url; }

    public void setWebUrl(String web_url) { this.web_url = web_url; }

    private Object avatar_url;

    public Object getAvatarUrl() { return this.avatar_url; }

    public void setAvatarUrl(Object avatar_url) { this.avatar_url = avatar_url; }

    private String git_ssh_url;

    public String getGitSshUrl() { return this.git_ssh_url; }

    public void setGitSshUrl(String git_ssh_url) { this.git_ssh_url = git_ssh_url; }

    private String git_http_url;

    public String getGitHttpUrl() { return this.git_http_url; }

    public void setGitHttpUrl(String git_http_url) { this.git_http_url = git_http_url; }

    private String namespace;

    public String getNamespace() { return this.namespace; }

    public void setNamespace(String namespace) { this.namespace = namespace; }

    private int visibility_level;

    public int getVisibilityLevel() { return this.visibility_level; }

    public void setVisibilityLevel(int visibility_level) { this.visibility_level = visibility_level; }

    private String path_with_namespace;

    public String getPathWithNamespace() { return this.path_with_namespace; }

    public void setPathWithNamespace(String path_with_namespace) { this.path_with_namespace = path_with_namespace; }

    private String default_branch;

    public String getDefaultBranch() { return this.default_branch; }

    public void setDefaultBranch(String default_branch) { this.default_branch = default_branch; }

    private String homepage;

    public String getHomepage() { return this.homepage; }

    public void setHomepage(String homepage) { this.homepage = homepage; }

    private String url;

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    private String ssh_url;

    public String getSshUrl() { return this.ssh_url; }

    public void setSshUrl(String ssh_url) { this.ssh_url = ssh_url; }

    private String http_url;

    public String getHttpUrl() { return this.http_url; }

    public void setHttpUrl(String http_url) { this.http_url = http_url; }
}
