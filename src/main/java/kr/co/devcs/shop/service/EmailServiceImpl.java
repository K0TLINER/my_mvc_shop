package kr.co.devcs.shop.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import kr.co.devcs.shop.entity.Order;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailServiceImpl implements EmailService {
    @Value("${SENDGRID_SECRET_KEY}") private String SENDGRID_SECRET_KEY;
    @Override
    public void sendOrderEmail(Order order) throws IOException {

//        val mustache = DefaultMustacheFactory().compile("templates/email.html")
//        val variables = mapOf("name" to name, "authNum" to authCode)
//        val writer = StringWriter()
//        mustache.execute(writer, variables).flush()
//        val html = writer.toString()
//
//        val content = Content("text/html", html)
//        val mail = Mail(from, subject, to, content)
//        val sg = SendGrid(API_KEY)
//        val request = Request()
//        request.method = Method.POST
//        request.endpoint = "mail/send"
//        request.body = mail.build()
//        val response: Response = sg.api(request)
        Email from = new Email("admin@em9806.devcs.co.kr", "MVC shop 매니저");
        String subject = "'" + order.getBuyer().getNickname() + "'님 감사합니다.";
        Email to = new Email("lko0365@naver.com");
        Content content = new Content("text/plain", "주문 번호: " + order.getOrderId() + "\n상품명 : " + order.getProduct().getProductName());
        Mail mail = new Mail(from, subject, to ,content);

        SendGrid sg = new SendGrid(SENDGRID_SECRET_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
