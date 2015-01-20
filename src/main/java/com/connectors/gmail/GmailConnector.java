package com.connectors.gmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.URLName;

import com.sun.mail.pop3.POP3Folder;
import com.sun.mail.pop3.POP3SSLStore;
import com.tools.EmailConstants;
import com.tools.data.EmailModel;

public class GmailConnector {

//	public static void main(String args[]) {
//		// readGmail();
//		deleteEmails();
//	}

	public static List<EmailModel> readGmail() {

		List<EmailModel> emailList = new ArrayList<EmailModel>();

		Properties props2 = System.getProperties();

		props2.setProperty(EmailConstants.EMAIL_STORE, EmailConstants.PROTOCOL);

		Session session2 = Session.getDefaultInstance(props2, null);

		try {

			Store store = session2.getStore(EmailConstants.PROTOCOL);
			store.connect(EmailConstants.RECEIVING_HOST, EmailConstants.USERNAME, EmailConstants.PASSWORD);
			Folder folder = store.getFolder("INBOX");

			folder.open(Folder.READ_WRITE);
			Message message[] = folder.getMessages();

			for (int i = 0; i < message.length; i++) {

				message[i].setFlag(Flags.Flag.DELETED, true);

			}

			folder.close(true);
			store.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return emailList;
	}
	
	
	/**
	 * public static void deleteEmails() {
	 * 
	 * Session session; POP3SSLStore store = null; POP3Folder folder = null;
	 * 
	 * try { Properties pop3props = new Properties(); String SSL_FACTORY =
	 * "javax.net.ssl.SSLSocketFactory"; Properties pop3Props = new
	 * Properties(); pop3Props.setProperty("mail.pop3.socketFactory.class",
	 * SSL_FACTORY); pop3Props.setProperty("mail.pop3.socketFactory.fallback",
	 * "false"); pop3Props.setProperty("mail.pop3.port", "995");
	 * pop3Props.setProperty("mail.pop3.socketFactory.port", "995"); URLName url
	 * = new URLName("pop3", "pop.gmail.com", 995, "", EmailConstants.USERNAME,
	 * EmailConstants.PASSWORD); session = Session.getInstance(pop3Props, null);
	 * store = new POP3SSLStore(session, url); store.connect(); folder =
	 * (POP3Folder) store.getFolder("INBOX"); folder.open(Folder.READ_WRITE);
	 * Message message[] = folder.getMessages();
	 * System.out.println(message.length); for (int i = 0, n = message.length; i
	 * < n; i++) { message[i].setFlag(Flags.Flag.DELETED, true);
	 * System.out.println("hello world");
	 * 
	 * } folder.close(true); store.close(); } catch (Exception e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * } finally { try { if (folder != null) { folder.close(true); } if (store
	 * != null) { store.close(); } } catch (Exception e) { }
	 * 
	 * } }
	 **/
}
