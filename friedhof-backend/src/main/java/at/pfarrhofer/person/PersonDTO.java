package at.pfarrhofer.person;

import java.time.LocalDate;

public record PersonDTO(
        long id,
        String firstname,
        String lastname,
        String housename,
        int birthyear,
        LocalDate date_of_death,
        int age,
        String grave
) {
}
