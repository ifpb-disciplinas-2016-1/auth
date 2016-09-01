package ifpb.ads.oauth;

import ifpb.ads.Authenticator;
import ifpb.ads.Credentials;
import ifpb.ads.ImmutableCredentials;
import ifpb.ads.ImmutableToken;
import ifpb.ads.Pair;
import ifpb.ads.Token;
import ifpb.ads.auth.twitter.TwitterAuthenticator;
import ifpb.ads.core.Connection;
import ifpb.ads.core.ImmutableRequest;
import ifpb.ads.core.Request;
import ifpb.ads.core.Response;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 28/08/2016, 02:48:25
 */
public class FinishFlowOAuth {

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("client");
        String method = "POST";
        Credentials credentials = ImmutableCredentials
                .builder()
                .acessKey(bundle.getString("acess_key"))
                .acessSecret(bundle.getString("acess_secret"))
                .build();

        String url = "https://api.twitter.com/oauth/access_token";
        Scanner scanner = new Scanner(System.in);
        String oauth_token = scanner.next();
        String oauth_verifier = scanner.next();

        Request requisicao = ImmutableRequest
                .builder()
                .url(url)
                .method(method)
                .credentials(credentials)
                .addHeader(Pair.create("Content-Type", "text/html"))
                .addHeader(Pair.create("charset", "utf-8"))
                .addHeader(Pair.create("oauth_token", oauth_token))
                .addParam(Pair.create("oauth_verifier", oauth_verifier))
                .build();

        Authenticator authenticator = new TwitterAuthenticator(credentials, url, method);

        Token token = ImmutableToken
                .builder()
                .oauth(oauth_token)
                .verifier(oauth_verifier)
                .build();

        authenticator.token(token);

        Connection con = new Connection(requisicao, authenticator);
        Response response = con.response();
        System.out.println(response.string());
        response.pairs().forEach(p -> {
            System.out.println(
                    String.format("k:%s, V:%s", p.key(), p.value()));
        });
        Token tokenResponse = response.token();
        System.out.println(
                String.format("token:%s, verifier:%s", tokenResponse.oauth(), tokenResponse.verifier()));

        con.close();
    }
}
