package mx.dev.blank.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.entity.Course;
import mx.dev.blank.exceptions.ResourceNotFound;
import mx.dev.blank.model.CourseDTO;
import mx.dev.blank.service.CourseService;
import mx.dev.blank.web.controller.request.CourseFilterRequest;
import mx.dev.blank.web.controller.request.CourseRequest;
import mx.dev.blank.web.controller.response.CourseResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/course")
@Slf4j
public class CourseRestController {

  private final CourseService courseService;
  
  /*
   * Get a list of courses
   */
  @GetMapping
  public ResponseEntity<CourseResponse> getCourses(){
	  
	  final CourseResponse response = new CourseResponse();
	  final List<Course> courses = courseService.getCourses();
	  response.setCourses(courses.stream().map(this::build).collect(Collectors.toList()));
	  
	  return ResponseEntity.ok(response);
  }
  
  /*
   * Get a course
   */
  @GetMapping(path = "/{courseId}")
  public @ResponseBody CourseDTO getCourse(@PathVariable("courseId") final long courseId){
	  
	  try {
		return build(courseService.findCourse(courseId)); 
	  } catch(final ResourceNotFound ex) {
		log.warn("Incorrect courseId: {}", courseId);
		return null;
	  }
  }
  
  private CourseDTO build(final Course course) {
	  return new CourseDTO(course.getId(), course.getName(), course.getKeycode(), course.getKeycode().startsWith("MAT"));
  }
  
  /*
   * Create a course
   */
  @PutMapping
  public ResponseEntity<String> createCourse(          
	  @Valid
	  @RequestBody final CourseRequest request,
	  final BindingResult errors) {
    
	if (errors.hasErrors()) {
	  log.warn("Errors: {}", errors);
	  return ResponseEntity.badRequest().body("Incorrect inputs");
    }
    
	courseService.createCourse(request);
	return ResponseEntity.ok("Course created"); 
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
    
	try {
	  courseService.editCourse(courseId, request);
      return ResponseEntity.ok("Course updated"); 
	} catch(final ResourceNotFound ex) {
	  log.warn("Incorrect courseId: {}", courseId);
	  return ResponseEntity.badRequest().body("Incorrect courseId");
	}
  }
  
  /*
   * Delete a course
   */
  @DeleteMapping(path = "/{courseId}")
  public ResponseEntity<String> deleteCourse(
	  @PathVariable("courseId") final long courseId) {
    
	try {
	  courseService.deleteCourse(courseId);
	  return ResponseEntity.ok("Course deleted"); 
	} catch(final ResourceNotFound ex) {
	  log.warn("Incorrect courseId: {}", courseId);
	  return ResponseEntity.badRequest().body("Incorrect courseId");
	}
  }
  
  @InitBinder("CourseFilter")
  private void initBinderRouteCoverageFilterRequest(final WebDataBinder binder) {
    binder.setAllowedFields("keycode");
  }
  
  /*
   * Get a list of courses
   */
  @GetMapping(path = "/filter")
  public ResponseEntity<CourseResponse> getCoursesFilters(@ModelAttribute("CourseFilter")
  final CourseFilterRequest filters){
	  log.info("Request {}", filters);
	  final CourseResponse response = new CourseResponse();
	  final List<Course> courses = courseService.getCourses(filters);
	  response.setCourses(courses.stream().map(this::build).collect(Collectors.toList()));
	  
	  return ResponseEntity.ok(response);
  }
  
}
