package mx.dev.blank.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import mx.dev.blank.dao.UserDAO;
import mx.dev.blank.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDAO userDAO;

  @Transactional(readOnly = true)
  public User getById(final String id) {
    return userDAO.getById(id);
  }

  // readonly false, is default value. You can omit it if you want
  @Transactional(readOnly = false)
  public void update(final String id, final String name) {
    final User user = getById(id);

    user.updateName(name);
    userDAO.update(user);
  }

  @Transactional
  public void updateInDao(final String id, final String name) {
    userDAO.updateNameById(name, id);
  }

  @Transactional(readOnly = true)
  public List<User> getUserPaginatedList(final int currentPos, final int size) {
    return userDAO.getPaginated(currentPos, size);
  }
}
