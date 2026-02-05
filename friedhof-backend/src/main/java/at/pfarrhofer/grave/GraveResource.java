package at.pfarrhofer.grave;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

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

    @POST
    @Transactional
    public Response addGrave(GraveCreateDTO dto) {
        Grave grave = new Grave(dto.sector(), dto.row(), dto.col(), dto.col2(), dto.special_name(),
                dto.pos_x(), dto.pos_y());
        graveRepository.persist(grave);
        return Response.ok(graveMapper.toResource(grave)).build();
    }
}
