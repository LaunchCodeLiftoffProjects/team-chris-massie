package vats.project.premier.models.data;

import org.springframework.data.repository.CrudRepository;
import vats.project.premier.models.User;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);

}
