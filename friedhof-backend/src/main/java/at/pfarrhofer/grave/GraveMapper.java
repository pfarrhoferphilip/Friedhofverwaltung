package at.pfarrhofer.grave;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GraveMapper {
    public GraveDTO toResource(Grave grave) {
        return new GraveDTO(grave.id, grave.getGraveName(), grave.getPosX(), grave.getPosY());
    }
}
