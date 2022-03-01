package model;

import gui.MainFrame;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

public class EmailRead {

    public static void main(String[] args) {
        String host = "imap.gmail.com";// change accordingly
        String mailStoreType = "pop3";
        String username = "info.saruwata@gmail.com";// change accordingly
        String password = "saruwatainfo9803";// change accordingly

//        check(host,username, password);
    }

    static MainFrame mf;

    public static void check(MainFrame mf, String host, String user, String password) {
        EmailRead.mf = mf;
        try {

            //create properties field
            Properties properties = new Properties();

            properties.setProperty("mail.imap.host", "imap.gmail.com");
            properties.setProperty("mail.imap.port", "993");
            properties.setProperty("mail.imap.connectiontimeout", "5000");
            properties.setProperty("mail.imap.timeout", "5000");

            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("imaps");

            store.connect(host, user, password);

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);
            mf.setMailCount(messages.length);

            for (int i = 0, n = messages.length; i < n; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");

//                System.out.println("Subject: " + message.getSubject());
//                System.out.println("From: " + message.getFrom()[0]);
//                System.out.println("Date: " + message.getSentDate());
            }

            emailFolder.close(false);
            store.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
