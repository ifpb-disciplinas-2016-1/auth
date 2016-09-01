package ifpb.ads.auth;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 23/08/2016, 14:53:17
 */
@Provider
public class ClienteRequestFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext)
            throws IOException {

        String authorization = "Authorization";

        //Basic a2FpcXVlOjEyMw==
        String value = requestContext.getHeaderString(authorization);

        if (value != null) {

            //a2FpcXVlOjEyMw==
            value = value.replaceAll("Basic ", "");

            //kaique:123
            String decodedString = new String(Base64.getDecoder().decode(value));

            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            String user = tokenizer.nextToken();
            String password = tokenizer.nextToken();

            if (!acessoPermitido(user, password)) {
                encerrarFiltro(requestContext);
            }

        }else{
            encerrarFiltro(requestContext);
        }
        
        //curl --header "Authorization: Basic a2FpcXVlOjEyMw=="  http://localhost:8088/filter-jaxrs/api/cliente
    }

    private void encerrarFiltro(ContainerRequestContext requestContext) {
        Response response = Response
                .status(Response.Status.UNAUTHORIZED)
                .entity("{\"msg\":\"Acesso não autorizado\"}")
                .build();

        requestContext.abortWith(response);
    }

    private static boolean acessoPermitido(String user, String password) {
        return "user".equals(user) && "123".equals(password);
    }

}
