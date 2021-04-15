package mx.dev.blank.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class StudentRequest {

  @NotBlank
  private final String name;

  @NotBlank
  private final String lastName;

  @Min(1)
  private final int age;
}
