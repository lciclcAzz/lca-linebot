package lciclcazz.linebot;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.linecorp.bot.client.LineMessagingServiceBuilder;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.response.BotApiResponse;
import lciclcazz.linebot.model.gitlab.Commit;
import lciclcazz.linebot.utils.Constant;
import lciclcazz.linebot.utils.Tools;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import retrofit2.Response;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * Created by IciclcAzz on 2017/06/26 17:11
 * Last Update 2017/06/26 17:11 | 1.
 */
@WebServlet("/gitlab-hook")
public class GitlabHook extends HttpServlet {

    private static final String APP_NAME = System.getenv("APP_NAME");
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) {

        PrintWriter out;
        try {
            out = res.getWriter();
            out.print(System.getenv("APP_NAME")+"/webhook/gitlab");
            out.println("APP_NAME : "+APP_NAME);
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        res.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        System.out.println("From : "+req.getRemoteAddr());
        String xHeader = req.getHeader(Constant.GITLAB_HEADER);

        JsonNode events = Tools.getEvent(Tools.getAllReq(req));
        ObjectMapper mapper = new ObjectMapper();

        Commit commit = new Commit();//mapper.convertValue(events.path("commits").asText(),Commit.class);
        String pushMsg = "";
//        try {
//            TextMessage textMessage = new TextMessage(
//                    events.path(0).path("user_avatar").asText()
////                    commit.getId() +"\n"+
////                            commit.getMessage() +"\n"+
////                            commit.getTimestamp() +"\n"+
////                            commit.getAuthor().getName() +"\n"+
////                            commit.getAuthor().getEmail()
//            );
//            System.out.println(events.path(0).path("user_avatar").asText());
//            PushMessage pushMessage = new PushMessage( Constant.IciclcAzz, textMessage );
//
//            Response<BotApiResponse> response = LineMessagingServiceBuilder
//                            .create(Constant.TOKEN)
//                            .build()
//                            .pushMessage(pushMessage)
//                            .execute();
//            System.out.println(response.code() + " " + response.message());
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//        }

 /*
 curl -X POST \
-H 'Content-Type:application/json' \
-H 'Authorization: Bearer {ENTER_ACCESS_TOKEN}' \
-d '{
    "to": "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx",
    "messages":[
        {
            "type":"text",
            "text":"Hello, world1"
        },
        {
            "type":"text",
            "text":"Hello, world2"
        }
    ]
}' https://api.line.me/v2/bot/message/push
  */
        HttpPost httpPost = new HttpPost("POST https://api.line.me/v2/bot/message/push");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Authorization", "Bearer " + Constant.TOKEN);

        StringBuffer pushBody = new StringBuffer("{\"to\":\""+Constant.IciclcAzz+"\",")
                .append("\"messages\":[")
                .append("{\"type\":\"text\",")
                .append("\"text\":\""+events.path(0).path("user_avatar").asText()+"\"")
                .append("}]")
                .append("}");

        System.out.println("pushBody : "+pushBody);
        httpPost.setEntity(new StringEntity(pushBody.toString(), StandardCharsets.UTF_8));

        try (CloseableHttpResponse resp = HttpClients.createDefault().execute(httpPost)) {
        } catch (IOException e) {}

        res.setStatus(HttpServletResponse.SC_OK);
    }

}
