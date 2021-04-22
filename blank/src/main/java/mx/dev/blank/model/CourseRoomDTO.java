package mx.dev.blank.model;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class CourseRoomDTO {

  private String courseName;
  private String keycode;
  private String roomName;
}
