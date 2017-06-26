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
import retrofit2.Response;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by IciclcAzz on 2017/06/26 17:11
 * Last Update 2017/06/26 17:11 | 1.
 */
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

        Commit commit = mapper.convertValue(events.path("commits").asText(),Commit.class);
        String pushMsg = "";
        try {
            TextMessage textMessage = new TextMessage(
                    commit.getId() +"\n"+
                            commit.getMessage() +"\n"+
                            commit.getTimestamp() +"\n"+
                            commit.getAuthor().getName() +"\n"+
                            commit.getAuthor().getEmail()
            );
            PushMessage pushMessage = new PushMessage( Constant.IciclcAzz, textMessage );

            Response<BotApiResponse> response = LineMessagingServiceBuilder
                            .create(Constant.TOKEN)
                            .build()
                            .pushMessage(pushMessage)
                            .execute();
            System.out.println(response.code() + " " + response.message());

        }catch (Exception e){

            e.printStackTrace();
        }


//        HttpPost httpPost = new HttpPost("https://api.line.me/v2/bot/message/reply");
//        httpPost.setHeader("Content-Type", "application/json");
//        httpPost.setHeader("Authorization", "Bearer " + Constant.TOKEN);
//
//        StringBuffer replyBody = new StringBuffer("{\"replyToken\":\"")
//                .append(events.path(0).path("replyToken").asText())
//                .append("\",")
//                .append(replyMess)
//                .append("}");
//
//        httpPost.setEntity(new StringEntity(replyBody.toString(), StandardCharsets.UTF_8));
//
//        try (CloseableHttpResponse resp = HttpClients.createDefault().execute(httpPost)) {
//        } catch (IOException e) {}
//
//        res.setStatus(HttpServletResponse.SC_OK);
    }

}
