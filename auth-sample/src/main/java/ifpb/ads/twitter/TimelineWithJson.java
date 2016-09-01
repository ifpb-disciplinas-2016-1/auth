package ifpb.ads.twitter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ifpb.ads.auth.twitter.ClientTwitter;
import java.io.IOException;
import java.util.List;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 29/08/2016, 01:45:03
 */
public class TimelineWithJson {

    public static void main(String[] args) throws IOException {
        ClientTwitter client = new ClientTwitter();
        String response = client.timeline();

        ObjectMapper objectMapper
                = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                        false);
        List<TwitterStatus> twitters = 
                objectMapper.readValue(response,
                        new TypeReference<List<TwitterStatus>>() {
        });

        twitters.forEach(
                tweet -> System.out.println(tweet.created_at() + " -> "+ tweet.user().name()+ " -> " + tweet.text())
        );

    }
}
