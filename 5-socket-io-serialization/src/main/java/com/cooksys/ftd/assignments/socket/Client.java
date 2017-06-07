package com.cooksys.ftd.assignments.socket;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {
	public static final String filepath = "C:/Users/ftd-0/code/combined-assignments/5-socket-io-serialization/config/config.xml";
    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     * @throws IOException
     * @throws JAXBException 
     */
    public static void main(String[] args) throws IOException, JAXBException{
    	Config config = null;
    	
 
    	config = Utils.loadConfig(filepath, Utils.createJAXBContext());
    	
    	
    	//Create a Socket
    	try{
    		Socket sock= new Socket(config.getRemote().getHost(),config.getRemote().getPort());
    		DataInputStream in = new DataInputStream(sock.getInputStream());
    		
    		Unmarshaller unmarshaller = Utils.createJAXBContext().createUnmarshaller();
    		Student student = (Student) unmarshaller.unmarshal(in);
    		
    		System.out.print(student);
    		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        // TODO
    }
}
