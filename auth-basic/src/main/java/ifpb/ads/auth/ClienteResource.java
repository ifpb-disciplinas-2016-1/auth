package ifpb.ads.auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 23/08/2016, 14:14:33
 */
@Path("cliente")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response hello() {

        return Response
                .ok()
                .entity("{"
                        + "\"nome\":\"Chaves\","
                        + "\"idade\": 12"
                        + "}")
                .build();
    }
}
