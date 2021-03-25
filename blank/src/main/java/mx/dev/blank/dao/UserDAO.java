package mx.dev.blank.dao;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import mx.dev.blank.entity.User;

public interface UserDAO {

  User getById(@NotBlank String id);

  User getByName(@NotBlank String name);

  List<User> getPaginated(int currentPos, int size);

  List<User> getByNameCoincidence(@NotBlank String coincidence);

  List<String> getSortedNames();

  void update(@NotNull User user);

  void updateNameById(@NotBlank String name, @NotBlank String id);
}
