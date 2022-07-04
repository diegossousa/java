package one.digitalinnovation.personapi.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "computer")
public final class Computer implements Serializable {

    @Id
    private String distinguishedName;

    private String name;

    private String cn;

    private String description;

    private String info;

    private boolean enabled;

    private String managedBy;

    private String location;

    private String url;

    private String serialNumber;

    private String type;

    private String givenName;

    private String operatingSystem;

    private String userAccountControl;
}

