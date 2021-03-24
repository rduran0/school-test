package mx.dev.blank.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  public String authenticateByUsername() {

    return "Hell world";
  }
}
