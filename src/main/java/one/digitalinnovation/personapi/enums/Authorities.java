package one.digitalinnovation.personapi.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public enum Authorities {
    USER("USER"),
    ADMIN("ADMIN"),
    GUEST("GUEST");

    private String authority;
}
