package mx.dev.blank.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.model.CourseRoomDTO;
import mx.dev.blank.model.CourseRoomRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseService {

  @Cacheable(value = "courseRoom")
  public CourseRoomDTO getCourseRoom(final String courseId) {
    simulateSlowService();

    return
        CourseRoomDTO.builder()
            .courseName("Curso ")
            .keycode(courseId)
            .roomName("R-0"+courseId)
            .build();
  }

  @CachePut(value = "courseRoom", key = "#courseRoomRequest.courseId")
  public CourseRoomDTO updateRoom (final CourseRoomRequest courseRoomRequest){
    return
            CourseRoomDTO.builder()
                .courseName("Curso ")
                .keycode(courseRoomRequest.getCourseId())
                .roomName(courseRoomRequest.getRoomName())
                .build();
  }

  @CacheEvict(value = "courseRoom")
  public void deleteRoom(final String courseId){
    log.info("[COURSE-ROOM] Deleting course id {}", courseId);
  }

  @CacheEvict(value = "courseRoom", allEntries = true)
  public void deleteAllRoom(){
    log.info("[COURSE-ROOM] Deleting All");
  }

  private void simulateSlowService() {
    try {
      long time = 3000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }
}
