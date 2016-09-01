package ifpb.ads.oauth;

import ifpb.ads.Authenticator;
import ifpb.ads.Credentials;
import ifpb.ads.ImmutableCredentials;
import ifpb.ads.Pair;
import ifpb.ads.Token;
import ifpb.ads.core.Request;
import ifpb.ads.core.Response;
import ifpb.ads.core.Connection;
import ifpb.ads.auth.twitter.TwitterAuthenticator;
import ifpb.ads.core.ImmutableRequest;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ResourceBundle;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/08/2016, 03:18:24
 * 
 */
public class StartFlowOAuth {

    public static void main(String[] args) throws IOException {
 
        ResourceBundle bundle = ResourceBundle.getBundle("client");
        String url = "https://api.twitter.com/oauth/request_token";
        String method = "POST";
 
        Credentials credentials = ImmutableCredentials
                .builder()
                .acessKey(bundle.getString("acess_key"))
                .acessSecret(bundle.getString("acess_secret"))
                .build();
 
        Request requisicao = ImmutableRequest
                .builder()
                .url(url)
                .method(method)
                .credentials(credentials)
                .addHeader(Pair.create("Content-Type", "text/html"))
                .addHeader(Pair.create("charset", "utf-8"))
                .build();

        Authenticator authenticator = new TwitterAuthenticator(credentials, url, method);

        Connection con = new Connection(requisicao, authenticator);
        Response response = con.response();
        response.pairs().forEach(p -> {
            System.out.println(String.format("k:%s, V:%s", p.key(), p.value()));
        });
 
        Token token = response.token();
 
        Desktop d = Desktop.getDesktop();
        //The method callback configured in twitter-app will be invoked 
        d.browse(URI.create("https://api.twitter.com/oauth/authenticate?oauth_token=" + token.oauth()));
        
    }
}
