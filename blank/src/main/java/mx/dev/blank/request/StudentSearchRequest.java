package mx.dev.blank.request;

import java.util.Date;
import lombok.Data;

@Data
public class StudentSearchRequest {

  /**
   * Query param that might contain a fraction of a student name
   */
  private String name;
  /**
   * Query param that might contain a fraction of a student uuid
   */
  private String uuid;

  /**
   * Start of birthday search range
   */
  private Date start;

  /**
   * End of birthday search range
   */
  private Date end;
}
