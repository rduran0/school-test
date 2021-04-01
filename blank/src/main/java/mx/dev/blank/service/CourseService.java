package mx.dev.blank.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.dao.CourseDAO;
import mx.dev.blank.entity.Course;
import mx.dev.blank.exceptions.ResourceNotFound;
import mx.dev.blank.web.controller.request.CourseRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

	private final CourseDAO courseDAO;
	
	@Transactional
	public List<Course> getCourses(){
		return courseDAO.getCourses();
	}
	
	@Transactional
	public Course findCourse(final long courseId) {
       final Course course = courseDAO.findById(courseId);
       if(course == null) {
         throw new ResourceNotFound("Course not found: " + courseId);
       }
       return course;
	}
	
	@Transactional(readOnly = false)
	public void createCourse(final CourseRequest request) {
		Course course = new Course(request.getName(), request.getKeycode());
		courseDAO.create(course);
		
		log.info("Create course: {}", course);
	}
	
	@Transactional(readOnly = false)
	public void editCourse(final long courseId, final CourseRequest request) {
       final Course course = courseDAO.findById(courseId);
       if(course == null) {
         throw new ResourceNotFound("Course not found: " + courseId);
       }
       course.update(request);
	   courseDAO.update(course);
		
		log.info("Updating course: {}", course);
	}
	
	@Transactional(readOnly = false)
	public void deleteCourse(final long courseId) {
       final Course course = courseDAO.findById(courseId);
       if(course == null) {
         throw new ResourceNotFound("Course not found: " + courseId);
       }
	   courseDAO.delete(course);
		
		log.info("Delete course: {}", course);
	}
	
	@Transactional
	public int countTeachersByCourseId(final long courseId) {
		return 0;
	}
}
