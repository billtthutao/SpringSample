package example.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import example.domain.User;

@Repository
public class JdbcUserRepository implements UserRepository{

	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select count(id) from Spitter",java.lang.Long.class);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("User");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", user.getUsername());
		args.put("password", user.getPassword());
		if(jdbcInsert.execute(args)>0)
			return user;
		else
			return null;
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select username, password from User where username=?", new UserRowMapper(), username);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select username, password from User", new UserRowMapper());
	}

	private static final class UserRowMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			String username = rs.getString("username");
			String password = rs.getString("password");
			return new User(username, password);
		}		
	}
}
