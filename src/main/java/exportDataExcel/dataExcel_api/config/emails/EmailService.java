package exportDataExcel.dataExcel_api.config.emails;

import com.mailjet.client.MailjetClient;
import com.mailjet.client.MailjetRequest;
import com.mailjet.client.MailjetResponse;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.resource.Emailv31;
import com.mailjet.client.transactional.*;
import com.mailjet.client.transactional.response.SendEmailsResponse;
import jakarta.transaction.Transactional;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmailService {
    private MailjetRequest request;
  private MailjetResponse response;

    public String sendEmail() throws MailjetException {
        MailjetClient mailjetClient=MailJetClientConfigConnexion.getMailjetClientInstance();
        request = new MailjetRequest(Emailv31.resource)
                .property(Emailv31.MESSAGES, new JSONArray()
                        .put(new JSONObject()
                                .put(Emailv31.Message.FROM, new JSONObject()
                                        .put("Email", "devwebconsulting@gmail.com")
                                        .put("Name", "Macondo Engeneering"))
                                .put(Emailv31.Message.TO, new JSONArray()
                                        .put(new JSONObject()
                                                .put("Email", "moetezmaddouri@gmail.com")
                                                .put("Name", "You")))
                                .put(Emailv31.Message.SUBJECT, "Alert Temperature")
                                .put(Emailv31.Message.TEXTPART, "Greetings from Mailjet!")
                                .put(Emailv31.Message.HTMLPART, "hello temperature is 33°")));
        response = mailjetClient.post(request);
        System.out.println(response.getStatus());
        System.out.println(response.getData());
        return "email envoiyé avec succe .";

    }
}
