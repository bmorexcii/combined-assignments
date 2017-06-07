package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;




public class Server extends Utils {
	public static final String filepath = "C:/Users/ftd-0/code/combined-assignments/5-socket-io-serialization/config/config.xml";

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     * @throws JAXBException 
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) throws JAXBException {
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        Student student = null;
        
        try(FileInputStream in = new FileInputStream(studentFilePath)){
        	
        	student = (Student) unmarshaller.unmarshal(in);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return student; // TODO
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     * @throws IOException 
     * @throws JAXBException 
     */
    public static void main(String[] args) throws IOException, JAXBException{
    	Config config = null;
    	
    	config = Utils.loadConfig(filepath, Utils.createJAXBContext());
    	
    	//create a server socket
    	try(ServerSocket server = new ServerSocket(config.getLocal().getPort())){
    		//Check for receiving connection
			Socket client = server.accept();
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			//create a student then unmarshall		
			Student student = loadStudent(config.getStudentFilePath(), Utils.createJAXBContext());
			//Re-marshals object to xml
			Marshaller marshaller = createJAXBContext().createMarshaller();
			
			marshaller.marshal(student, out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//TO DO
    }
}
