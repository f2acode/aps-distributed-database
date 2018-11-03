package controller;

import models.Person;
import models.Response;
import models.Status;

public class PeopleService {
	public Response create(Person person) {
		return new Response(new Person(0L, null, 0, null, null), Status.VALID);
	}
	
	public Response update(Person person) {
		return new Response(new Person(0L, null, 0, null, null), Status.VALID);
	}
	
	public Response delete(long id) {
		return new Response(null, Status.VALID);
	}
	
	public Response read(long id) {
		return new Response(new Person(0L, null, 0, null, null), Status.VALID);
	}
}
