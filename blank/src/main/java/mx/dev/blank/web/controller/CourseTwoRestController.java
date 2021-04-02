package mx.dev.blank.web.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.entity.Course;
import mx.dev.blank.exceptions.ResourceNotFound;
import mx.dev.blank.model.CourseDTO;
import mx.dev.blank.service.CourseService;
import mx.dev.blank.web.controller.assemblers.CourseResourceAssambler;
import mx.dev.blank.web.controller.response.CourseResponse;


/**
 * Used to explain the RestController/Controller difference
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/course2")
@Slf4j
public class CourseTwoRestController {

  private final CourseService courseService;
  private final CourseResourceAssambler.Factory assemblerFactory;
  
  /*
   * Get a Course using an assembler
   */
  
  @GetMapping(path = "/{courseId}")
  public CourseDTO getCourse(@PathVariable("courseId") final long courseId){
	  final CourseResourceAssambler assembler = assemblerFactory.create(Collections.emptyList());
	  try {
		return assembler.toDto(courseService.findCourse(courseId)); 
	  } catch(final ResourceNotFound ex) {
		log.warn("Incorrect courseId: {}", courseId);
		return null;
	  }
  }
  
  /*
   * Get a list of courses using an assambler with expand
   */
  @GetMapping
  public ResponseEntity<CourseResponse> getCourses(
		  @RequestParam(required = false, value = "expand", defaultValue = "")
		  final List<String> expand){
	  final CourseResourceAssambler assembler = assemblerFactory.create(expand);
	  
	  final CourseResponse response = new CourseResponse();
	  final List<Course> courses = courseService.getCourses();
	  response.setCourses(courses.stream().map(entry -> assembler.toDto(entry)).collect(Collectors.toList()));
	  
	  return ResponseEntity.ok(response);
  }
  
  /*
   * Method to test a Custom Resolver
   */
  @GetMapping(path = "/special/{fullCourseId}")
  public CourseDTO getSpecialCourse(@PathVariable("fullCourseId") final Course course){
	  if(course != null) {
		  return null;
	  }
	    final CourseResourceAssambler assembler = assemblerFactory.create(Collections.emptyList());
		
			return assembler.toDto(course); 
  }
  

  
}
