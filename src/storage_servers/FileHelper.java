package storage_servers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHelper {
	private File file;
	
	public FileHelper() {
		
	}
	
	public void Write() {
		String data = "I will write this String to File in Java";
        FileWriter fr = null;
        
        try {
        	file = new File(System.getProperty("user.home") + "/DistributedDatabase/test.txt");
            file.getParentFile().mkdir();
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
	}
}
