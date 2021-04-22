package mx.dev.blank.service;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mx.dev.blank.model.CourseRoomDTO;
import mx.dev.blank.model.CourseRoomRequest;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceManager {

  private static final String CACHE_NAME = "test2";
  private final CacheManager cacheManager;

  public CourseRoomDTO getCourseRoom(final String courseId) {
    final Cache.ValueWrapper cache = cacheManager.getCache(CACHE_NAME).get(courseId);

    if(cache != null){
      return (CourseRoomDTO) cache.get();
    }

    final CourseRoomDTO courseRoomDTO = CourseRoomDTO.builder()
            .courseName("Curso ")
            .keycode(courseId)
            .roomName("R-0"+courseId)
            .build();

    cacheManager.getCache(CACHE_NAME).put(courseId, courseRoomDTO);
    simulateSlowService();

    return courseRoomDTO;
  }


  public CourseRoomDTO updateRoom (final CourseRoomRequest courseRoomRequest){

    final CourseRoomDTO courseRoomDTO = CourseRoomDTO.builder()
            .courseName("Curso ")
            .keycode(courseRoomRequest.getCourseId())
            .roomName(courseRoomRequest.getRoomName())
            .build();

    cacheManager.getCache(CACHE_NAME).put(courseRoomRequest.getCourseId(), courseRoomDTO);
    return courseRoomDTO;

  }

  public void deleteRoom(final String courseId){
    log.info("[COURSE-ROOM] Deleting course id {}", courseId);
    cacheManager.getCache(CACHE_NAME).evict(courseId);
  }

  @CacheEvict(value = "courseRoom", allEntries = true)
  public void deleteAllRoom(){
    log.info("[COURSE-ROOM] Deleting All");
    cacheManager.getCache(CACHE_NAME).clear();
  }

  private void simulateSlowService() {
    try {
      long time = 3000L;
      Thread.sleep(time);
    } catch (InterruptedException e) {
      throw new IllegalStateException(e);
    }
  }

  @Configuration
  public static class Config{
    @Bean
    public CacheManager cacheManager() {
      CaffeineCacheManager cacheManager = new CaffeineCacheManager();

      cacheManager.setCaffeine(Caffeine.newBuilder()
              //.initialCapacity(200)
              //.maximumSize(500)
              //.weakKeys()
              .expireAfterAccess(4, TimeUnit.HOURS)
              //.recordStats()
      );
      return cacheManager;
    }
  }
}
