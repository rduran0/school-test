package mx.dev.blank.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AppRestController {

  @GetMapping
  public ResponseEntity<String> getAppDetails() {

    return new ResponseEntity<>("Hello World", HttpStatus.OK);
  }
}
