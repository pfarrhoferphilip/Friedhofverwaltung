package at.pfarrhofer.grave;

public record GraveCreateDTO(
        long id,
        int sector,
        int row,
        int col,
        int col2,
        String special_name,
        double pos_x,
        double pos_y
) {
}
