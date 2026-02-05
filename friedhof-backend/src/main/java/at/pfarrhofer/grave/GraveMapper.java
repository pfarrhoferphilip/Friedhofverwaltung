package at.pfarrhofer.grave;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class GraveMapper {
    public GraveDTO toResource(Grave grave) {
        return new GraveDTO(grave.id, grave.getGraveName(), grave.getPosX(), grave.getPosY());
    }

    public GraveCreateDTO toCreateResource(Grave grave) {
        return new GraveCreateDTO(grave.getId(), grave.getSector(), grave.getRow(), grave.getCol(), grave.getCol2(),
                grave.getSpecialName(), grave.getPosX(), grave.getPosY());
    }
}
