package com.poc;

import java.net.UnknownHostException;

import com.connectors.mongo.MongoConnector;

public class WipeDb {
	
	public static void main(String args[]) throws UnknownHostException{
		MongoConnector mongoQA = new MongoConnector();
		System.out.println("---------- Mongo DBs CleanUp");
		for (String dbNameNow : mongoQA.getDbNames()) {
			if(dbNameNow.contains("Test")){
				mongoQA.deleteDb(dbNameNow);
			}
		}
		
		System.out.println("---------- Remaining DBs after CleanUp");
		for (String dbNameNow : mongoQA.getDbNames()) {
			System.out.println(dbNameNow);
		}
	}

}
