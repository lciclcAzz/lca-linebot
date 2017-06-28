package lciclcazz.linebot.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IciclcAzz on 2017/06/26 17:28
 * Last Update 2017/06/26 17:28 | 1.
 */
public class Constant {

    public static final String APP_NAME = System.getenv("APP_NAME");
    public static final String SECRET_KEY = System.getenv("LINE_BOT_CHANNEL_SECRET");
    public static final String TOKEN = System.getenv("LINE_BOT_CHANNEL_TOKEN");

    public static final String DEFAULT_HEADER="X-HEADER";
    public static final String GITLAB_HEADER="X-Gitlab-Event";
    public static final String LINE_HEADER="X-Line-Signature";

    public static HashMap LINE_USER_ID=new HashMap();
    static {
        LINE_USER_ID.put("lciclcAzz", "U286d471884e10b385774885526bdea35");
        LINE_USER_ID.put("xDreamsBox", "U6bf578ca3ebe7005bdd7e41d7725725e");
    }

}
