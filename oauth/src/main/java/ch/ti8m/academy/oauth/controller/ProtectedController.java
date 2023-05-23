package ch.ti8m.academy.oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/test")
@RequiredArgsConstructor
@Validated
public class ProtectedController {
  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(OK)
  public ResponseEntity<String> sayHello(Authentication authentication) {
    // TODO Retrieve user email from JWT
    var email = "unknown";
    return ResponseEntity.ok(String.format("Welcome %s, you are authenticated!",
                                           email));
  }
}
