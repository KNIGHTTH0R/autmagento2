package com.tools.env.variables;

import java.io.File;
import java.net.UnknownHostException;

import com.connectors.mongo.MongoConnector;
import com.tools.persistance.MongoReader;


public class EnvContextSetter {
	
	
	public static void main(String args[]) throws UnknownHostException{
		MongoConnector mongoConnector = new MongoConnector();
		doUrlStuff();
	}
	
	public static void doUrlStuff() throws UnknownHostException{
//		String pathDeclaration = "src" + File.separator + "main" + File.separator + "resources" + File.separator;
		
		String a  = MongoReader.getContext();
		System.out.println(a);
		System.out.println(MongoReader.getEnvironment());
	}
	
	
	

}
