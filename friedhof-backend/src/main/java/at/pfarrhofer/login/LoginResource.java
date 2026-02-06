package at.pfarrhofer.login;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("login")
public class LoginResource {

    @GET
    @Path("/{password}")
    public Response login(@PathParam("password") String input) {
        String password = ConfigProvider.getConfig().getValue("admin.password", String.class);
        //String password = "admin";

        if (input.equals(password)) {
            return Response.ok().entity(true).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity(false).build();
        }
    }
}
