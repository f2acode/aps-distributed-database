package storage_server2;

import models.Gender;
import models.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper2 {
	private FileWriter fr;
	private String localPath;
	
	public FileHelper2() {
		fr = null;
		localPath = System.getProperty("user.home") + "/DistributedDatabase2/persons/";
	}
	
	public String personToTxtData(Person person) {
		String data = person.getName() + "\r\n" + 
				person.getAge() + "\r\n" +
				person.getGender() + "\r\n" +
				person.getAddress();
		return data;
	}
	
	public long getNextId(String path) {
		File folder = new File(path);
		long nextId = 0L;
	    for (final File fileEntry : folder.listFiles()) {
            nextId = Integer.valueOf(fileEntry.getName().split("\\.")[0]) + 1;
	    }
	    return nextId;
	}
	
	public Person create(Person person) {
		String data = personToTxtData(person);
		long id = getNextId(localPath);
		person.setId(id);
		
		File file = new File(localPath + id + ".txt");
        file.getParentFile().getParentFile().mkdir();
        file.getParentFile().mkdir();
        
        try {
            file.createNewFile();
        	fr = new FileWriter(file);
            fr.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        return person;
	}
	
	public Person read(long id) throws IOException {
		String currentStrLine;
		Person person = new Person(-1);
		int lineIndex = 0;
		BufferedReader br = null;
		
		File file = new File(localPath + id + ".txt");
		
		if(file.exists()) {
			try {
				br = new BufferedReader(new FileReader(file));
				person.setId(id);
				while ((currentStrLine = br.readLine()) != null) {
					switch(lineIndex) {
						case 0: person.setName(currentStrLine); break;
						case 1: person.setAge(Integer.valueOf(currentStrLine)); break;
						case 2: person.setGender(Gender.valueOf(currentStrLine)); break;
						case 3: person.setAddress(currentStrLine); break;
					}
					lineIndex++;
			  	}
			}catch(Exception e) {
				
			}finally {
				br.close();
			}	
		}else {
			return null;
		}
		
		return person;
	}
	
	public Person update(Person person) {
		String data = personToTxtData(person);		
		File file = new File(localPath + person.getId() + ".txt");
        file.getParentFile().getParentFile().mkdir();
        file.getParentFile().mkdir();
		
		if(file.exists()) {
	        try {
	            file.createNewFile();
	        	fr = new FileWriter(file);
	            fr.write(data);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            try {
	                fr.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
		}else {
			return null;
		}
        return person;
	}
	public boolean delete(long id) {
		File file = new File(localPath + id + ".txt");
        return file.delete();
	}
}
