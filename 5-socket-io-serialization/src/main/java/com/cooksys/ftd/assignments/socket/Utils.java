package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     * @throws JAXBException 
     */
    public static JAXBContext createJAXBContext() throws JAXBException {
    	JAXBContext context = JAXBContext.newInstance(Config.class, LocalConfig.class, RemoteConfig.class, Student.class);
    	return context; // TODO
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     * @throws JAXBException 
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) throws JAXBException {
        Unmarshaller unmarshaller = jaxb.createUnmarshaller();
        Config unmarshalledConfig = null;
        
        try (FileInputStream in = new FileInputStream(configFilePath)){
        	unmarshalledConfig = (Config) unmarshaller.unmarshal(in);
 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return unmarshalledConfig; // TODO
    	
    }
}
