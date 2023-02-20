package com.socialmediaapplications.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount = 0;
	static {
		users.add(new User(++usersCount, "Priyanka", LocalDate.now().minusYears(26)));
		users.add(new User(++usersCount, "Prayog", LocalDate.now().minusYears(31)));
		users.add(new User(++usersCount, "Vamshi", LocalDate.now().minusYears(30)));
	}
	
	public List<User> getAll() {
		return users;
	}
	
	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}
	
	public User findOne(Integer id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	public void deleteById(Integer id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		users.removeIf(predicate);
	}
}
