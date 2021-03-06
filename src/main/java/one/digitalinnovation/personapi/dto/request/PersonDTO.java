package one.digitalinnovation.personapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.model.Phone;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
  private Long id;
  @NotEmpty
  private String login;

  @NotEmpty
  private String password;

  @NotEmpty
  private String authorities;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String firstName;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String lastName;

  @NotEmpty
  private String cpf;

  private String birthDate;

  @Valid
  @NotEmpty
  private List<Phone> phones;
}
