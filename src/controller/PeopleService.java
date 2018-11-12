package controller;

import java.io.IOException;
import java.net.Socket;

import helpers.Connection;
import models.Person;
import models.Request;
import models.Response;
import models.Type;

public class PeopleService {

	private static Socket controllerStorageServer_socket1;
	private static Socket controllerStorageServer_socket2;

	public PeopleService(long personId) {
		try {
			int port = serverPortResolver(personId);
			switch (port) {
				case 1:
					controllerStorageServer_socket1 = new Socket("localhost", 9601);
					controllerStorageServer_socket2 = new Socket("localhost", 9602);
					break;
				case 2:
					controllerStorageServer_socket1 = new Socket("localhost", 9602);
					controllerStorageServer_socket2 = new Socket("localhost", 9603);
					break;
				case 3:
					controllerStorageServer_socket1 = new Socket("localhost", 9603);
					controllerStorageServer_socket2 = new Socket("localhost", 9601);

			}
		} catch (IOException e) {
			System.out.println("PeopleService nao consegui resolver o host...");
		}
	}
	
	public int serverPortResolver(long personId) {
		return (int) (personId % 3) + 1;
	}

	public Response create1(Person person) {
		Request request = new Request(Type.POST, person);
		Connection.send(controllerStorageServer_socket1, request);
		return (Response) Connection.receive(controllerStorageServer_socket1);
	}

	public Response create2(Person person) {
		Request request = new Request(Type.POST, person);
		Connection.send(controllerStorageServer_socket2, request);
		return (Response) Connection.receive(controllerStorageServer_socket2);
	}
	
	public Response read(long id) {
		Person person = new Person(id);
		Request request = new Request(Type.GET, person);
		Connection.send(controllerStorageServer_socket1, request);
		return (Response) Connection.receive(controllerStorageServer_socket1);
	}

	public Response update1(Person person) {
		Request request = new Request(Type.PUT, person);
		Connection.send(controllerStorageServer_socket1, request);
		return (Response) Connection.receive(controllerStorageServer_socket1);
	}

	public Response update2(Person person) {
		Request request = new Request(Type.PUT, person);
		Connection.send(controllerStorageServer_socket2, request);
		return (Response) Connection.receive(controllerStorageServer_socket2);
	}

	public Response delete1(long id) {
		Person person = new Person(id);
		Request request = new Request(Type.DELETE, person);
		Connection.send(controllerStorageServer_socket1, request);
		return (Response) Connection.receive(controllerStorageServer_socket1);
	}

	public Response delete2(long id) {
		Person person = new Person(id);
		Request request = new Request(Type.GET, person);
		Connection.send(controllerStorageServer_socket2, request);
		return (Response) Connection.receive(controllerStorageServer_socket2);
	}
}
