package at.pfarrhofer.person;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/persons")
public class PersonResource {
    @Inject
    PersonRepository personRepository;
    @Inject
    PersonMapper personMapper;

    @GET
    public Response getPersons() {
        return Response.ok(personRepository.findAll().stream().map(personMapper::toResource)).build();
    }

    @Path("/name/{name}")
    @GET
    public Response getPersonByName(@PathParam("name") String name) {
        return Response.ok(personRepository.findByName(name).stream().map(personMapper::toResource)).build();
    }

}
