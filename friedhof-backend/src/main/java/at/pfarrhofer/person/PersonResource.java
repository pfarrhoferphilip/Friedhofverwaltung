package at.pfarrhofer.person;

import at.pfarrhofer.grave.Grave;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.ConfigProvider;

@Path("/persons")
public class PersonResource {
    @Inject
    PersonRepository personRepository;
    @Inject
    PersonMapper personMapper;

    @GET
    public Response getPersons() {
        return Response.ok(personRepository.findAllSorted().stream().map(personMapper::toResource)).build();
    }

    @Path("/name/{name}")
    @GET
    public Response getPersonByName(@PathParam("name") String name) {
        return Response.ok(personRepository.findByName(name).stream().map(personMapper::toResource)).build();
    }

    @Path("/{id}")
    @GET
    public Response getPersonById(@PathParam("id") long id) {
        return Response.ok(personMapper.toResource(personRepository.findById(id))).build();
    }

    @POST
    @Transactional
    @Path("/{password}")
    public Response createPerson(PersonDTO dto, @PathParam("password") String password) {
        if (!ConfigProvider.getConfig().getValue("admin.password", String.class).equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Person person = new Person(dto.firstname(), dto.lastname(), dto.housename(), dto.birthyear(), dto.date_of_death(), dto.age(),
                personRepository.getEntityManager().find(Grave.class, dto.grave_id()));
        personRepository.persist(person);
        return Response.status(Response.Status.CREATED).entity(personMapper.toResource(person)).build();
    }

    @PUT
    @Transactional
    @Path("/{password}")
    public Response updatePerson(PersonDTO dto, @PathParam("password") String password) {
        if (!ConfigProvider.getConfig().getValue("admin.password", String.class).equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Person person = personRepository.findById(dto.id());
        person.setFirstname(dto.firstname());
        person.setLastname(dto.lastname());
        person.setHousename(dto.housename());
        person.setBirthyear(dto.birthyear());
        person.setGrave(personRepository.getEntityManager().find(Grave.class, dto.grave_id()));
        person.setDateOfDeath(dto.date_of_death());
        person.setAge(dto.age());
        personRepository.persist(person);
        return Response.ok(personMapper.toResource(person)).build();
    }

    @Path("/{id}/{password}")
    @DELETE
    @Transactional
    public Response deletePerson(@PathParam("id") long id, @PathParam("password") String password) {
        if (!ConfigProvider.getConfig().getValue("admin.password", String.class).equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        personRepository.deleteById(id);
        return Response.noContent().entity(id).build();
    }

}
