package controller;

import java.io.IOException;
import java.net.Socket;

import helpers.Connection;
import models.Person;
import models.Request;
import models.Response;
import models.Type;

public class PeopleService {
	
	private static Socket socket;
	
	public PeopleService() {
		try {
			socket = new Socket("localhost", 9601);
		} catch (IOException e) {
			System.out.println("PeopleService nao consegui resolver o host...");
		}
	}

	public Response create(Person person) {
		Request request = new Request(Type.POST, person);
		Connection.send(socket, request);
		return (Response) Connection.receive(socket);
	}
	
	public Response read(long id) {
		Person person = new Person(id);
		Request request = new Request(Type.GET, person);
		Connection.send(socket, request);
		return (Response) Connection.receive(socket);
	}

	public Response update(Person person) {
		Request request = new Request(Type.PUT, person);
		Connection.send(socket, request);
		return (Response) Connection.receive(socket);
	}

	public Response delete(long id) {
		Person person = new Person(id);
		Request request = new Request(Type.GET, person);
		Connection.send(socket, request);
		return (Response) Connection.receive(socket);
	}
}
