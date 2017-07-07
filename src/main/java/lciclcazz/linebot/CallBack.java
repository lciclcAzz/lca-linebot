package lciclcazz.linebot;


import com.fasterxml.jackson.databind.JsonNode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lciclcazz.linebot.utils.Constant;
import lciclcazz.linebot.utils.Tools;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.DateTimeException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.EnumMap;


@WebServlet("/callback")
public class CallBack extends HttpServlet {
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {

		PrintWriter out;
		try {
			out = res.getWriter();
			out.print("lciclcAzz's Line Bot Messages.");
			out.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		res.setStatus(HttpServletResponse.SC_OK);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {

        System.out.println("From : "+req.getRemoteAddr()+" "+req.getQueryString());
		// sign check
		String sig = req.getHeader("X-Line-Signature");
		byte[] reqAll=null;

		try {
			reqAll = Tools.getAllReq(req);
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(new SecretKeySpec(Constant.SECRET_KEY.getBytes(), "HmacSHA256"));
			String csig = Base64.getEncoder().encodeToString(mac.doFinal(reqAll));

			if (!csig.equals(sig)) throw(new IOException());

		} catch (IOException | NullPointerException | NoSuchAlgorithmException | InvalidKeyException e) {
			res.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		JsonNode events = Tools.getEvent(reqAll,"events");
		String xType = events.path(0).path("source").path("type").asText();
		System.out.println("Type : "+xType);
		if("group".equals(xType)) {
			System.out.println("GROUPID : "+events.path(0).path("source").path("groupid").asText());
		}else if("room".equals(xType)){
			System.out.println("ROOMID : "+events.path(0).path("source").path("roomid").asText());
		}else{
			System.out.println("USERID : "+events.path(0).path("source").path("userid").asText());
		}
		String replyMess;
		if ("message".equals(events.path(0).path("type").asText())) {  // received message
			replyMess = createReply(events.path(0).path("message"));

		} else if ("join".equals(events.path(0).path("type").asText())){  // join message
			replyMess = "\"messages\":[{\"type\":\"text\", \"text\":\"Start..\"}]";

		} else {
			res.setStatus(HttpServletResponse.SC_OK);
			return;
		}

		// Analyse message //
		HttpPost httpPost = new HttpPost("https://api.line.me/v2/bot/message/reply");
		httpPost.setHeader("Content-Type", "application/json");
		httpPost.setHeader("Authorization", "Bearer " + Constant.TOKEN);

		StringBuffer replyBody = new StringBuffer("{\"replyToken\":\"")
				.append(events.path(0).path("replyToken").asText())
				.append("\",")
				.append(replyMess)
				.append("}");

		httpPost.setEntity(new StringEntity(replyBody.toString(), StandardCharsets.UTF_8));

		try (CloseableHttpResponse resp = HttpClients.createDefault().execute(httpPost)) {
		} catch (IOException e) {}

		res.setStatus(HttpServletResponse.SC_OK);
	}


	private String createReply(JsonNode message){
		StringBuffer replyMessages = new StringBuffer("\"messages\":[");
		String type = message.path("type").asText();

		if ("text".equals(type)) {
			String[] args;
			args = message.path("text").asText().split(" ", 2);
			if ("qr".equals(args[0])) {
				replyMessages.append("{\"type\":\"text\",\"text\":\"")
						.append(args[0]+"！")
						.append("\"},");
				try {
					String url = createQR(args[1], message.path("id").asText());  // /tmp/hoge.jpg
					replyMessages.append("{\"type\":\"image\",\"originalContentUrl\":\"")
							.append("https://").append(Constant.APP_NAME).append(".herokuapp.com")
							.append(url)
							.append("\",\"previewImageUrl\":\"")
							.append("https://").append(Constant.APP_NAME).append(".herokuapp.com")
							.append(url);

				} catch (ArrayIndexOutOfBoundsException | IOException | WriterException e) {
					replyMessages.append("{\"type\":\"text\",\"text\":\"")
							.append("ผิดพลาด");
				}

			} else if ("time".equals(args[0])) {
				replyMessages.append("{\"type\":\"text\",\"text\":\"")
						.append(System.currentTimeMillis())
						.append("\"},")
						.append("{\"type\":\"text\",\"text\":\"");
				try {
					ZonedDateTime now = ZonedDateTime.now(ZoneId.of(args[1]));
					replyMessages.append(now.format(DateTimeFormatter.ofPattern("MM/dd HH:mm")));

				} catch (ArrayIndexOutOfBoundsException | DateTimeException e) {
					replyMessages.append("xxxx")
							.append(System.getProperty("line.separator"))
							.append("https://git.io/vyqDP");
				}

			} else if ("wow".equals(args[0])) {
				replyMessages.append("{\"type\":\"text\",\"text\":\"")
						.append("lciclcAzz")
						.append("\"},").append("{\"type\":\"text\",\"text\":\"")
						.append("https://lca-linebot.herokuapp.com");
				try {
					String url = URLEncoder.encode(args[1], "UTF-8");
					replyMessages.append("/input/?i=").append(url);
				} catch (ArrayIndexOutOfBoundsException | UnsupportedEncodingException e) {}

			}else {
				replyMessages.append("{\"type\":\"text\",\"text\":\"")
						.append("lciclcAzz Line Bot");
			}

		} else if ("sticker".equals(type)) {
			replyMessages.append("{\"type\":\"text\",\"text\":\"")
					.append("sticker");

		} else if ("image".equals(type)) {
			replyMessages.append("{\"type\":\"text\",\"text\":\"")
					.append("Linkin Park！");
		}
		replyMessages.append("\"}]");

		return replyMessages.toString();
	}


	private String createQR(String arg, String id) throws WriterException, IOException {
		EnumMap<EncodeHintType, Object> hints = new EnumMap<>(EncodeHintType.class);
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

		BitMatrix bitMatrix = new QRCodeWriter().encode(arg, BarcodeFormat.QR_CODE, 185, 185, hints);
		//40 171x171

		BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix);
		StringBuffer fileName = new StringBuffer("/tmp/").append(id).append(".jpg");
		ImageIO.write(image, "JPEG", new File(fileName.toString()));

		return fileName.toString();
	}

}
