package one.digitalinnovation.personapi.controllers;

import lombok.RequiredArgsConstructor;
import one.digitalinnovation.personapi.exceptions.message.ExceptionMessage;
import one.digitalinnovation.personapi.model.JwtRequest;
import one.digitalinnovation.personapi.model.JwtResponse;
import one.digitalinnovation.personapi.security.JwtTokenUtil;
import one.digitalinnovation.personapi.service.JwtUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = {"http://localhost:3000", "http://192.168.1.13:3000"}, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final JwtUserDetailsService userDetailsService;

    private final JmsTemplate jmsTemplate;
    private final JmsTemplate jmsTemplateTopic;

    @PostMapping("/auth/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(Authentication auth) throws RuntimeException{
        try {
            Authentication authenticate = authenticationManager.authenticate(auth);

            SecurityContextHolder.getContext().setAuthentication(authenticate);

            jmsTemplate.convertAndSend("queue.log", "{user: " + auth.getPrincipal() + ", usando: " + auth.getCredentials() + "}");

            jmsTemplateTopic.convertAndSend("topic.sendEmail", "{teste:'Message Topic'}");

        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
