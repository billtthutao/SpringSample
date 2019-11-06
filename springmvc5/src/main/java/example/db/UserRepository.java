package example.db;

import java.util.List;

import example.domain.User;

public interface UserRepository {

	  long count();
	  
	  User save(User user);

	  User findByUsername(String username);

	  List<User> findAll();
}
