package Server;

import Models.Person;

public class PeopleService {
	public Person create(Person person) {
		return new Person(0L, null, 0, null, null, null);
	}
	public Person update(Person person) {
		return new Person(0L, null, 0, null, null, null);
	}
	public void delete(long id) {}
	public Person read(long id) {
		return new Person(0L, null, 0, null, null, null);
	}
}
