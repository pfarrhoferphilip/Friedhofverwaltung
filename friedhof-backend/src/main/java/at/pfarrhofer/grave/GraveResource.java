package at.pfarrhofer.grave;

import at.pfarrhofer.person.Person;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.List;

@Path("graves")
public class GraveResource {
    @Inject
    GraveRepository graveRepository;
    @Inject
    GraveMapper graveMapper;

    @GET
    public Response getGraves() {
        List<Grave> graves = graveRepository.findAll().stream().toList();
        return Response.ok(graves.stream().map(graveMapper::toResource)).build();
    }

    @Path("/{id}")
    @GET
    public Response getGrave(@PathParam("id") long id) {
        Grave grave = graveRepository.findById(id);
        return Response.ok(graveMapper.toResource(grave)).build();
    }

    @Path("/whole/{id}")
    @GET
    public Response getWholeGrave(@PathParam("id") long id) {
        Grave grave = graveRepository.findById(id);
        return Response.ok(graveMapper.toCreateResource(grave)).build();
    }

    @POST
    @Transactional
    @Path("/{password}")
    public Response addGrave(GraveCreateDTO dto, @PathParam("password") String password) {
        if (!ConfigProvider.getConfig().getValue("admin.password", String.class).equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Grave grave = new Grave(dto.sector(), dto.row(), dto.col(), dto.col2(), dto.special_name(),
                dto.pos_x(), dto.pos_y());
        graveRepository.persist(grave);
        return Response.ok(graveMapper.toResource(grave)).build();
    }

    @PUT
    @Transactional
    @Path("/{password}")
    public Response updateGrave(GraveCreateDTO dto, @PathParam("password") String password) {
        if (!ConfigProvider.getConfig().getValue("admin.password", String.class).equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        Grave grave = graveRepository.findById(dto.id());
        grave.setSector(dto.sector());
        grave.setRow(dto.row());
        grave.setCol(dto.col());
        grave.setCol2(dto.col2());
        grave.setSpecialName(dto.special_name());
        grave.setPosX(dto.pos_x());
        grave.setPosY(dto.pos_y());

        graveRepository.persist(grave);
        return Response.ok(graveMapper.toResource(grave)).build();
    }

    @DELETE
    @Transactional
    @Path("/{id}/{password}")
    public Response deleteGrave(@PathParam("id") long id, @PathParam("password") String password) {
        if (!ConfigProvider.getConfig().getValue("admin.password", String.class).equals(password)) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        graveRepository.deleteById(id);
        return Response.noContent().entity(id).build();
    }
}
