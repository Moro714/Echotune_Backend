package echotune_server.db;

import java.util.HashMap;
import java.util.Map;

import echotune_server.entities.User;
import echotune_server.jpa.JPAService;
import echotune_server.sql.SQLQueryBuilder;
import echotune_server.sql.SQLCondition;
import echotune_server.sql.SQLOperator;

public class UserQueryHandler {
	public static void addUser(User user) {
		SQLQueryBuilder queryBuilder = new SQLQueryBuilder();
		Map<String, String> _user = new HashMap<String, String>();
		
		_user.put("username", user.getUsername());
		_user.put("password_hash", user.getPasswordHash());
		
		String query = queryBuilder.insertInto("users").values(_user).build();
		
		JPAService jpaService = JPAService.getInstance();
		jpaService.runTransaction(entityManager -> {
			return entityManager.createNativeQuery(query).executeUpdate();
		});
	}
	
	public static User getUserById(int id) {
		try {
		SQLQueryBuilder queryBuilder = new SQLQueryBuilder();
		String query = queryBuilder.selectAll().from("users").where(
			new SQLCondition("id", SQLOperator.EQUALS, String.valueOf(id))
		).build();
		
		System.out.println(query);
		
		//Get single instance of JPAService to handle our query
		JPAService jpaService = JPAService.getInstance();
		User user = (User) jpaService.runTransaction(entityManager -> {
			//Use entity manager to run query in table, get single result since we have only one user to get
			return entityManager.createNativeQuery(query, User.class).getSingleResult();
		});
		
		return user;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return new User();
		}
	}
}
