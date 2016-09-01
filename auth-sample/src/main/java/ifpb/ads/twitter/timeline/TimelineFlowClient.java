package ifpb.ads.twitter.timeline;

import ifpb.ads.ImmutableToken;
import ifpb.ads.Token;
import ifpb.ads.auth.twitter.ClientTwitter;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.Scanner;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 28/08/2016, 00:51:18
 */
public class TimelineFlowClient {

    public static void main(String[] args) throws IOException {
        
        ClientTwitter client = new ClientTwitter();
        Token token = client.startFlow();

        Desktop d = Desktop.getDesktop();
        d.browse(URI.create("https://api.twitter.com/oauth/authenticate?oauth_token=" + token.oauth()));

        Scanner scanner = new Scanner(System.in);
        String key = scanner.next();
        String secret = scanner.next();

        Token tokenAutenticated = ImmutableToken
                .builder()
                .oauth(key)
                .verifier(secret)
                .build();
        Token acessToken = client.finishFlow(tokenAutenticated);

        System.out.println(client.timeline(acessToken)); 

    }
}
