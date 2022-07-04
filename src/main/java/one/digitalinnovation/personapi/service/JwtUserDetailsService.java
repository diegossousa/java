package one.digitalinnovation.personapi.service;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.model.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  private final PersonRepository personRepository;

  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    Person personByLogin = personRepository.findPersonByLogin(login);

    if (personByLogin != null) {
      return User.builder()
          .username(personByLogin.getLogin())
          .authorities(personByLogin.getAuthorities())
          .password(personByLogin.getPassword())
          .build();
    } else {
      throw new UsernameNotFoundException("User not found with email: " + login);
    }
  }
}
