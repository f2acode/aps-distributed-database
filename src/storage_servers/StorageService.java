package storage_servers;

import java.io.IOException;

import models.Person;
import models.Response;
import models.Status;

public class StorageService {
	private FileHelper fileHelper;
	public StorageService() {
		fileHelper = new FileHelper();
	}
	
	public Response create(Person person) {
		//TODO validations
		fileHelper.create(person);
		return new Response(new Person(0L, null, 0, null, null), Status.VALID);
	}

	public Response read(long id) {
		Response response = new Response(null, null);
		try {
			response.setPerson(fileHelper.read(id));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public Response update(Person person) {
		return new Response(new Person(0L, null, 0, null, null), Status.VALID);
	}

	public Response delete(long id) {
		return new Response(null, Status.VALID);
	}
}
