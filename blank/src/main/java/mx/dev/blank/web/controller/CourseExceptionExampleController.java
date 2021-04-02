package mx.dev.blank.web.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.entity.Course;
import mx.dev.blank.model.CourseDTO;
import mx.dev.blank.service.CourseService;
import mx.dev.blank.web.controller.request.CourseRequest;

/**
 * 
 * Methods that use controller advice to catch error
 *
 */

@Controller
@RequiredArgsConstructor
@RequestMapping("/course/exception/")
@Slf4j
public class CourseExceptionExampleController {


	  private final CourseService courseService;
	 /*
	   * Get a course
	   */
	  @GetMapping(path = "/{courseId}")
	  public @ResponseBody CourseDTO getCourse(@PathVariable("courseId") final long courseId){
				return build(courseService.findCourse(courseId)); 
	  }
	  
	  private CourseDTO build(final Course course) {
		  return new CourseDTO(course.getId(), course.getName(), course.getKeycode(), course.getKeycode().startsWith("MAT"));
	  }
	  
	 
	  /*
	   * Update a course
	   */
	  @PostMapping(path = "/{courseId}")
	  public ResponseEntity<String> updateCourse(
			  @PathVariable("courseId") final long courseId,
			  @Valid
				@RequestBody final CourseRequest request,
				final BindingResult errors) {
			    
				if (errors.hasErrors()) {
				  log.warn("Errors: {}", errors);
				  return ResponseEntity.badRequest().body("Incorrect inputs");
			    }
	    
			courseService.editCourse(courseId, request);
			return ResponseEntity.ok("Course updated"); 
	  }
	  
	  /*
	   * Delete a course
	   */
	  @DeleteMapping(path = "/{courseId}")
	  public ResponseEntity<String> deleteCourse(
			  @PathVariable("courseId") final long courseId) {
	    
			courseService.deleteCourse(courseId);
			return ResponseEntity.ok("Course deleted"); 
	  }
}
