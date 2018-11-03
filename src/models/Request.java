package models;

import java.io.Serializable;

public class Request implements Serializable {

	private static final long serialVersionUID = 1L;
	private Type type;
	private Person person;

    public Request(Type type, Person person) {
        this.type = type;
        this.person = person;
    }

    public Request() {}
    
    public Type getType() {
    	return this.type;
    }
    
    public void setType(Type type) {
    	this.type = type;
    }
    
    public Person getPerson() {
    	return this.person;
    }
    
    public void setPerson(Person person) {
    	this.person = person;
    }
}
