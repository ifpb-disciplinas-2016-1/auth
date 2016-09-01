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
 * @since 30/08/2016, 15:31:29
 */
public class TimelineDynamicFlowClient {
    public static void main(String[] args) throws IOException {
        // set your key and secret ofapp
        ClientTwitter clientTwitter  = new ClientTwitter(
                "xxx",
                "xx");
        
        Token tokenApp = clientTwitter.startFlow();
        
        Desktop d = Desktop.getDesktop();
        d.browse(URI.create("https://api.twitter.com/oauth/authenticate?oauth_token=" + tokenApp.oauth()));

        Scanner scanner = new Scanner(System.in);
        String key = scanner.next();
        String secret = scanner.next();

        Token tokenAutenticated = ImmutableToken
                .builder()
                .oauth(key)
                .verifier(secret)
                .build();
        Token acessToken = clientTwitter.finishFlow(tokenAutenticated);

        System.out.println(clientTwitter.timeline(acessToken));
    }
}
