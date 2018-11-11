package controller;

import java.io.IOException;
import java.net.Socket;

import helpers.Connection;
import models.Person;
import models.Request;
import models.Response;
import models.Type;

public class PeopleService {
	
	private static Socket controllerStorageServer_socket;
	
	public PeopleService(long personId) {
		try {
			controllerStorageServer_socket = new Socket("localhost", serverPortResolver(personId));
		} catch (IOException e) {
			System.out.println("PeopleService nao consegui resolver o host...");
		}
	}
	
	public int serverPortResolver(long personId) {
		return (int) (9601 + (personId % 3));
	}

	public Response create(Person person) {
		Request request = new Request(Type.POST, person);
		Connection.send(controllerStorageServer_socket, request);
		return (Response) Connection.receive(controllerStorageServer_socket);
	}
	
	public Response read(long id) {
		Person person = new Person(id);
		Request request = new Request(Type.GET, person);
		Connection.send(controllerStorageServer_socket, request);
		return (Response) Connection.receive(controllerStorageServer_socket);
	}

	public Response update(Person person) {
		Request request = new Request(Type.PUT, person);
		Connection.send(controllerStorageServer_socket, request);
		return (Response) Connection.receive(controllerStorageServer_socket);
	}

	public Response delete(long id) {
		Person person = new Person(id);
		Request request = new Request(Type.GET, person);
		Connection.send(controllerStorageServer_socket, request);
		return (Response) Connection.receive(controllerStorageServer_socket);
	}
}
