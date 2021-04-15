package mx.dev.blank.model;

import lombok.Getter;

@Getter
public class StudentDTO {

  private String uuid;

  private String name;

  private String surnames;

  protected StudentDTO() {}

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private final StudentDTO dto;

    protected Builder() {
      dto = new StudentDTO();
    }

    public Builder uuid(final String uuid) {
      dto.uuid = uuid;
      return this;
    }

    public Builder name(final String name) {
      dto.name = name;
      return this;
    }

    public Builder surnames(final String surnames) {
      dto.surnames = surnames;
      return this;
    }

    public StudentDTO build() {
      return dto;
    }
  }
}
