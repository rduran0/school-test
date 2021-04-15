package mx.dev.blank.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import mx.dev.blank.dao.UserDAO;
import mx.dev.blank.entity.User;
import mx.dev.blank.factory.FakeDataFactory;
import org.assertj.core.util.Lists;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class UserServiceTest {

  private UserDAO userDAO;

  private UserService service;

  @BeforeMethod
  public void beforeMethod() {
    userDAO = mock(UserDAO.class);
    service = new UserService(userDAO);
  }

  @DataProvider
  public Object[][] getUserPaginatedList_dataProvider() {
    final List<User> users1 = Lists.newArrayList(FakeDataFactory.user("one"), FakeDataFactory.user("two"));
    final List<String> names1 = Lists.newArrayList("one", "two");

    final List<User> users2 = Lists.newArrayList(FakeDataFactory.user());
    final List<String> names2 = Lists.newArrayList("anyone");

    return new Object[][] {
        {
          users1,
            0,
            5,
            names1
        },
        {
          users2,
            0,
            10,
            names2
        }
    };
  }

  @Test(dataProvider = "getUserPaginatedList_dataProvider")
  public void getUserPaginatedList_itWorks(
      final List<User> daoResults,
      final int pos,
      final int size,
      final List<String> expectedUserNames) {

    when(userDAO.getPaginated(anyInt(), anyInt())).thenReturn(daoResults);

    final List<User> users = service.getUserPaginatedList(pos, size);

    assertThat(users).extracting(User::getName).hasSameElementsAs(expectedUserNames);
  }
}
