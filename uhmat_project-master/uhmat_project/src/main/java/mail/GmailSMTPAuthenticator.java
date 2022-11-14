package mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

 
public class GmailSMTPAuthenticator extends Authenticator{
    
    PasswordAuthentication pa;
    
    public GmailSMTPAuthenticator() {
        String mail_id = "asw4265";
        String mail_pw = "vlkczgadpllguhkq";
        
        pa = new PasswordAuthentication(mail_id, mail_pw);
    }
    
    public PasswordAuthentication getPasswordAuthentication() {
        return pa;
    }
}
