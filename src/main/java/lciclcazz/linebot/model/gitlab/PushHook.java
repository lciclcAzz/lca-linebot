package lciclcazz.linebot.model.gitlab;

import java.util.ArrayList;

/**
 * Created by IciclcAzz on 2017/06/26 17:47
 * Last Update 2017/06/26 17:47 | 1.
 */
public class PushHook {

    private String object_kind;

    public String getObjectKind() { return this.object_kind; }

    public void setObjectKind(String object_kind) { this.object_kind = object_kind; }

    private String before;

    public String getBefore() { return this.before; }

    public void setBefore(String before) { this.before = before; }

    private String after;

    public String getAfter() { return this.after; }

    public void setAfter(String after) { this.after = after; }

    private String ref;

    public String getRef() { return this.ref; }

    public void setRef(String ref) { this.ref = ref; }

    private String checkout_sha;

    public String getCheckoutSha() { return this.checkout_sha; }

    public void setCheckoutSha(String checkout_sha) { this.checkout_sha = checkout_sha; }

    private int user_id;

    public int getUserId() { return this.user_id; }

    public void setUserId(int user_id) { this.user_id = user_id; }

    private String user_name;

    public String getUserName() { return this.user_name; }

    public void setUserName(String user_name) { this.user_name = user_name; }

    private String user_username;

    public String getUserUsername() { return this.user_username; }

    public void setUserUsername(String user_username) { this.user_username = user_username; }

    private String user_email;

    public String getUserEmail() { return this.user_email; }

    public void setUserEmail(String user_email) { this.user_email = user_email; }

    private String user_avatar;

    public String getUserAvatar() { return this.user_avatar; }

    public void setUserAvatar(String user_avatar) { this.user_avatar = user_avatar; }

    private int project_id;

    public int getProjectId() { return this.project_id; }

    public void setProjectId(int project_id) { this.project_id = project_id; }

    private Project project;

    public Project getProject() { return this.project; }

    public void setProject(Project project) { this.project = project; }

    private Repository repository;

    public Repository getRepository() { return this.repository; }

    public void setRepository(Repository repository) { this.repository = repository; }

    private ArrayList<Commit> commits;

    public ArrayList<Commit> getCommits() { return this.commits; }

    public void setCommits(ArrayList<Commit> commits) { this.commits = commits; }

    private int total_commits_count;

    public int getTotalCommitsCount() { return this.total_commits_count; }

    public void setTotalCommitsCount(int total_commits_count) { this.total_commits_count = total_commits_count; }
}
