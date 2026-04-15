package lab.ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName =
                "destinationLookup", propertyValue = "java:app/jms/NewsQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "jakarta.jms.Queue")
})
@JMSDestinationDefinition(name = "java:app/jms/NewsQueue",
        interfaceName = "jakarta.jms.Queue", resourceAdapter = "jmsra",
        destinationName = "NewsQueue")
public class NewsMDB implements jakarta.jms.MessageListener{
    @PersistenceContext
    private EntityManager em;
    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage msg = (TextMessage) message;
                String text = msg.getText();

                String[] parts = text.split("\\|");

                String heading = parts[0];
                String body = parts[1];

                NewsItem e = new NewsItem();
                e.setHeading(heading);
                e.setBody(body);

                em.persist(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
