package mx.dev.blank.web.controller;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.util.Collections;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import mx.dev.blank.entity.Course;
import mx.dev.blank.service.CourseService;
import mx.dev.blank.web.controller.request.CourseRequest;

@Test
public class CourseRestControllerMockMvcTest {

	private CourseService courseService;
	
	private MockMvc mvc;

	  @BeforeMethod
	  public void setUp() throws Exception {
		  courseService = mock(CourseService.class);
	    
		  final CourseRestController controller =
	        new CourseRestController(
	        		courseService);

	    mvc =
	        MockMvcBuilders.standaloneSetup(controller)
	            .setControllerAdvice(new ExceptionControllerAdvice())
	            .build();
	  }
	  
	  @Test
	  public void getCourses() throws Exception {
		final Course testCourse = new Course("Historia", "HIS");
		testCourse.setId(1);
	    when(courseService.getCourses()).thenReturn(Collections.singletonList(testCourse));

	    mvc.perform(
	            get(URI.create("/course"))
	                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	    	.andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
	        .andExpect(jsonPath("$").exists())
	        .andExpect(jsonPath("$.courses").exists())
	        .andExpect(jsonPath("$.courses").value(hasSize(1)))
	        .andExpect(jsonPath("$.courses[*].id").value(contains(1)));
	  }
	  
	  @Test
	  public void updateCourses_failed() throws Exception {

	    mvc.perform(
	            post(URI.create("/course/1"))
	                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	    			.content(new ObjectMapper().writeValueAsString(new CourseRequest())))
	    	.andDo(print())
	        .andExpect(status().isBadRequest())
	        .andExpect(jsonPath("$").exists())
	        .andExpect(jsonPath("$").value("Incorrect inputs"));
	  }
	  
	  @Test
	  public void updateCourses() throws Exception {
		  final CourseRequest request = new CourseRequest();
		  request.setKeycode("ING");
		  request.setName("Ingles");
	    mvc.perform(
	            post(URI.create("/course/1"))
	                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
	    			.content(new ObjectMapper().writeValueAsString(request)))
	    	.andDo(print())
	        .andExpect(status().isOk())
	        .andExpect(jsonPath("$").exists())
	        .andExpect(jsonPath("$").value("Course updated"));
	  }
	  
	  
	  
}
