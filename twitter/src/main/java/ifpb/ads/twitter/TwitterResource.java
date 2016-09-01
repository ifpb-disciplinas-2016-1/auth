package ifpb.ads.twitter;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 30/08/2016, 15:26:44
 */
@Path("/")
public class TwitterResource {

    @GET
    public void requestToken(
            @QueryParam("oauth_token") String oauth_token,
            @QueryParam("oauth_verifier") String oauth_verifier) {
        System.out.println(oauth_token + " | " + oauth_verifier);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String json(@FormParam("nome") String nome, @FormParam("idade") int idade) {
        return "{\"nome\":\"" + nome + "\","
                + "\"idade\":"+idade
                + "}";
    }
}
