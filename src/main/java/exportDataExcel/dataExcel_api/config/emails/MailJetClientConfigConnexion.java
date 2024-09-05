package exportDataExcel.dataExcel_api.config.emails;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;

public class MailJetClientConfigConnexion {
    private static MailjetClient mailjetClientInstance;

    private MailJetClientConfigConnexion(){

    }
    public static MailjetClient getMailjetClientInstance(){
        if(mailjetClientInstance == null){
            synchronized (MailJetClientConfigConnexion.class){
                if(mailjetClientInstance == null){
                    ClientOptions options=ClientOptions.builder()
                            .apiKey("46a85c968c5c827adc61feab117c528e")
                            .apiSecretKey("8200e7e1fb62162b3dc32b40db12cfef")
                            .build();
                    mailjetClientInstance = new MailjetClient(options);
                }
            }

        }
        return mailjetClientInstance;
    }
}
