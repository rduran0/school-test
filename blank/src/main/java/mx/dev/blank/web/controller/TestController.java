package mx.dev.blank.web.controller;

import lombok.RequiredArgsConstructor;
import mx.dev.blank.response.GenericResponse;
import mx.dev.blank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

  private final UserService userService;

  @GetMapping("/user")
  public ResponseEntity<GenericResponse> testUserService() {

    userService.getUserPaginatedList(1, 5);

    return ResponseEntity.ok(GenericResponse.builder().success(true).build());
  }
}
