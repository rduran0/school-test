package mx.dev.blank.web.controller;

import java.util.List;
import javax.validation.Valid;
import mx.dev.blank.dto.StudentDTO;
import mx.dev.blank.request.StudentRequest;
import mx.dev.blank.response.StudentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/dummy")
public class DummyController {

  private final int MAX_STUDENTS = 10;
  private final List<StudentDTO> DUMMY_STUDENTS = StudentDTO.listForTest(MAX_STUDENTS);

  @GetMapping(path = "/status")
  public ResponseEntity<String> getControllerStatus() {
    return ResponseEntity.ok("Dummy Controller working");
  }

  @GetMapping(path = "/student/list")
  public ResponseEntity<StudentResponse> getStudents(@RequestParam final int offset,
      @RequestParam final int limit) {

    // Retrieve based on pagination
    final int toPos = offset + limit;
    final List<StudentDTO> retrieved =
        DUMMY_STUDENTS.subList(
            offset,
            Math.min(toPos, DUMMY_STUDENTS.size()));

    return ResponseEntity.ok(new StudentResponse(retrieved, MAX_STUDENTS));
  }

  @PostMapping(path = "/student")
  public ResponseEntity<Boolean> createStudent(@Valid @RequestBody final StudentRequest request) {
    return ResponseEntity.ok(true);
  }

  @PostMapping(path = "/failure/{error}")
  public ResponseEntity<Void> fail500(@PathVariable final int error) {
    switch (error) {
      case 401:
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      case 403:
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
      case 409:
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
      case 417:
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
      case 418:
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();

      case 500:
      default:
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

  @GetMapping(path = "/failure/default")
  public ResponseEntity<Void> fail400() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
  }
}
