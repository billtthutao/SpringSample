package example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import example.db.UserRepository;
import example.domain.User;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

@Controller
public class HomeController {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/", method=GET)
	public String home() {
		test1();
		test2();
		return "home";
	}
	
	@RequestMapping(value="/index", method=GET)
	public String index() {
		return "index";
	}
	
	private void test1() {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement("select * from User");
			ResultSet result = stmt.executeQuery();
			while(result.next()) {
				//System.out.println(result.getString(0));
				System.out.println(result.getString(1));
				System.out.println(result.getString(2));
				System.out.println(result.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if (stmt != null) {
				stmt.close();
				}
				if (conn != null) {
				conn.close();
				}
				} catch (SQLException e) {
				// I'm even less sure about what to do here
				}
		}
	}
	
	private void test2() {
		userRepository.save(new User("hutao","123456"));
		List<User> users = userRepository.findAll();
		for(User user:users) {
			System.out.println(user);
		}
	}
}
