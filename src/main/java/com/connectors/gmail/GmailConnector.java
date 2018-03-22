package com.connectors.gmail;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.ArrayUtils;

import com.sun.mail.imap.protocol.FLAGS;
import com.tools.constants.EmailConstants;
import com.tools.data.email.EmailCredentialsModel;
import com.tools.data.email.EmailModel;

/**
 * Due to new implementation Gmail Connector needs to be initialized with
 * EmailCredentialsModel, that contains: host, protocol, username and password.
 * 
 * @author voicu.vac
 *
 */
public class GmailConnector {

	private static String host;
	private static String protocol;
	private static String username;
	private static String password;

	public GmailConnector(EmailCredentialsModel emailData) {
		host = emailData.getHost();
		protocol = emailData.getProtocol();
		username = emailData.getUsername();
		password = emailData.getPassword();
	}

	/**
	 * Return a list of e-mails as EmailModel. Note: To be used for mongo
	 * persistance if needed.
	 * 
	 * @return
	 * @throws MessagingException
	 */
	public List<EmailModel> readGmail() throws MessagingException {

		List<EmailModel> emailList = new ArrayList<EmailModel>();
		Message message[] = connect();

		for (int i = 0; i < message.length; i++) {
			EmailModel modelNow = new EmailModel();
			modelNow.setSubject(message[i].getSubject());

			modelNow.setRecievedDate(message[i].getReceivedDate());
			modelNow.setSentDate(message[i].getSentDate());

			modelNow.setContent(getStringFromMessage(message[i]));

			System.out.println("------------------------");
			System.out.println(message[i].getSubject());
			System.out.println(message[i].getReceivedDate());
			System.out.println(message[i].getSentDate());
			System.out.println(modelNow.getContent());

			// message[i].setFlag(FLAGS.Flag.DELETED, true);
			emailList.add(modelNow);
		}

		return emailList;
	}

	/**
	 * Search for email by searchKey - email content, also filter by
	 * emailAddress. Flag for Delete found email.
	 * 
	 * @param emailAddress
	 * @param searchKey
	 * @param deleteAfter
	 * @return
	 */
	public String searchForMail(String emailAddress, String searchKey, boolean deleteAfter) {
		Message message[] = connect();

		boolean found = false;
		String returnMessage = null;
		for (int i = message.length - 1; i >= 0; i--) {
			try {
				Address[] addresses = message[i].getAllRecipients();
				for (Address address : addresses) {
					if (address.toString().contains(emailAddress)) {
						String allText = getStringFromMessage(message[i]);
						if (allText.contains(searchKey)) {
							returnMessage = allText;
							if (deleteAfter) {
								message[i].setFlag(FLAGS.Flag.DELETED, true);
							}
							found = true;
							break;
						}
					}
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			if (found) {
				break;
			}
		}
		// Assert.assertTrue("The searched email was not found", found);
		return returnMessage;
	}

	/**
	 * Connect to email account and return INBOX and SPAM messages.
	 * 
	 * @return
	 */
	private Message[] connect() {
		Properties props2 = System.getProperties();
		Session session2 = Session.getDefaultInstance(props2, null);
		props2.setProperty(EmailConstants.EMAIL_STORE, protocol);

		Message message[] = null;
		Message imboxMessage[] = null;
		Message spamMessage[] = null;

		try {

			Store store = session2.getStore(protocol);
			store.connect(host, username, password);
			Folder imboxFolder = store.getFolder("INBOX");
			imboxFolder.open(Folder.READ_WRITE);
			Folder spamFolder = store.getFolder("[Gmail]/Spam");
			spamFolder.open(Folder.READ_WRITE);

			imboxMessage = imboxFolder.getMessages();
			spamMessage = spamFolder.getMessages();

			message = ArrayUtils.addAll(imboxMessage, spamMessage);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
	}

	/**
	 * Will return the message as a string from multipart type of content.
	 * 
	 * @param message
	 * @return
	 */
	private String getStringFromMessage(Message message) {
		StringBuilder textContent = new StringBuilder("");
		try {
			textContent = new StringBuilder((message.getSubject() + " "));
			if (message.getContentType().startsWith("multipart")) {
				MimeMultipart content = (MimeMultipart) message.getContent();
				for (int i = 0; i < content.getCount(); i++) {
					BodyPart part = content.getBodyPart(i);
					textContent.append(part.getContent());
				}
			} else {
				textContent.append(message.getContent().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return textContent.toString();
	}
	
	
	public  void deleteInbox() {
		Properties props2 = System.getProperties();
		Session session2 = Session.getDefaultInstance(props2, null);
		props2.setProperty(EmailConstants.EMAIL_STORE, protocol);

		try {

			Store store = session2.getStore(protocol);
			store.connect(host, username, password);
			Folder imboxFolder = store.getFolder("INBOX");
			imboxFolder.open(Folder.READ_WRITE);
			

			Message[] msgList = imboxFolder.getMessages();
			for (Message msg : msgList) {
				msg.setFlag(Flags.Flag.DELETED, true);
				System.out.println("deleted mail");
			}
		
			imboxFolder.close(true);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
