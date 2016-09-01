package ifpb.ads.twitter.timeline;

import ifpb.ads.auth.twitter.ClientTwitter;
import java.io.IOException;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 28/08/2016, 00:51:18
 */
public class TimelineWithClient {

    public static void main(String[] args) throws IOException {
        ClientTwitter twitter = new ClientTwitter();
        System.out.println(twitter.timeline());

    }
}
