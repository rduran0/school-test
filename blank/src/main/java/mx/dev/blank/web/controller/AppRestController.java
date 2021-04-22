package mx.dev.blank.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.model.CourseRoomDTO;
import mx.dev.blank.model.CourseRoomRequest;
import mx.dev.blank.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class AppRestController {

  private final CourseService courseService;

  @GetMapping(value = "courses/{courseId}/rooms")
  public ResponseEntity<CourseRoomDTO> getCourseRoom(
          @PathVariable(name = "courseId") final String courseId
  ) {
    final StopWatch watch = new StopWatch("getCourseRoomController");

    watch.start("getCourse");
    log.info("[COURSE-ROOM] Retrieving course room with id {}", courseId);
    final CourseRoomDTO result = courseService.getCourseRoom(courseId);
    log.info("[COURSE-ROOM] Retrieved course room with value {}", result);
    watch.stop();

    log.info("[COURSE-ROOM] metrics {}", watch.prettyPrint());

    return ResponseEntity.ok(result);
  }

  @PutMapping(value = "courses/{courseId}/rooms")
  public ResponseEntity<CourseRoomDTO> UpdateCourseRoom(
          @PathVariable(name = "courseId") final String courseId,
          @RequestBody CourseRoomRequest courseRoomRequest
  ) {

    final CourseRoomDTO result = courseService.updateRoom(courseRoomRequest);

    return ResponseEntity.ok(result);
  }

  @DeleteMapping(value = "courses/{courseId}/rooms")
  public ResponseEntity<CourseRoomDTO> deleteCourseRoom(
          @PathVariable(name = "courseId") final String courseId
  ) {

    courseService.deleteRoom(courseId);

    return ResponseEntity.ok().build();
  }

  @DeleteMapping(value = "courses/rooms/all")
  public ResponseEntity<CourseRoomDTO> deleteCourseRoom() {

    courseService.deleteAllRoom();

    return ResponseEntity.ok().build();
  }
}

