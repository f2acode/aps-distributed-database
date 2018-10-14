package Server;

import Models.Person;

public class PeopleService {
	public void insert(Person person) {}
	public void update(Person person) {}
	public void delete(Person person) {}
	public Person get(long id) {
		return new Person(0L, null, 0, null, null, null);
	}
}
