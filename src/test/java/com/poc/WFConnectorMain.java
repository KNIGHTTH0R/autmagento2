package com.poc;

import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class WFConnectorMain {
	
	@Test
	public static void main(){
		try
        {
        String name,add,city,eid;
        double sal;
        
        InputStream inputStream = new URL("http://BARM01:_N!v2s34N_@148.251.178.207:7061/PjAutomation/WS/PippaJeanAut/Page/CustLedgerEntry").openStream();
        
        String theString = IOUtils.toString(inputStream, "application/xml"); 
//        String theString = IOUtils.toString(inputStream, "UTF-8"); 
        
        System.out.println("The String: " + theString);
        
//        InputStream br = new FileInputStream("http://148.251.178.207:7061/PjAutomation/WS/PippaJeanAut/Page/CustLedgerEntry");
//        BufferedReader br=new BufferedReader(new FileInputStream("http://148.251.178.207:7061/PjAutomation/WS/PippaJeanAut/Page/CustLedgerEntry"));
//        BufferedReader br=new BufferedReader(new FileInputStream("http://148.251.178.207:7061/PjAutomation/WS/PippaJeanAut/Page/CustLedgerEntry"));
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

//        System.out.println("Enter EID");
//        eid=br.readLine();

//        System.out.println("Enter Name");
//
//        name=br.readLine();
//
//        System.out.println("Enter Address");
//        add=br.readLine();
//
//        System.out.println("Enter City");
//        city=br.readLine();
//
//        System.out.println("Enter Salary");
//        sal=Double.parseDouble(br.readLine());

//        addNewEmployee(eid,name,add,city,sal);
        }
        catch(Exception e1)
        {
            System.out.println(e1.getMessage());
        }
	}

}
