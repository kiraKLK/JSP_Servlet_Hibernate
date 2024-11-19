
package com.kira.util;

import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class Email {
    //Email: tungvanmmo@gmail.com
    //Password: jiymejdlmxajropi
    
        static final String from = "tungvanmmo@gmail.com";
        static final String password = "jiymejdlmxajropi";
        
        public static void sendEmail(String to,String subject,String content)throws MessagingException {
             // properties : khai bao cac thuoc tinh
        Properties pops = new Properties();
        pops.put("mail.smtp.host", "smtp.gmail.com"); //SMTP HOST
        pops.put("mail.smpt.port", "587"); //TLS 587, SSL 465 
        pops.put("mail.smtp.auth", "true");
        pops.put("mail.smtp.starttls.enable","true");
                
        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from,password);
            }
        };
        
        // phien lam viec
        Session session = Session.getInstance(pops,auth);
        // gui Email
         //final String to = "tungvanmmo@gmail.com";
        MimeMessage msg = new MimeMessage(session);
        msg.addHeader("Content-type", "text/html; charset=UTF-8");
        // set nguoi gui
        msg.setFrom(from);
        // set address
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to,false));
        // tieu de
        msg.setSubject(subject);
        // quy dinh ngay gui
        msg.setSentDate(new Date());
        // quy dinh Email nhan phan hoi
        //msg.setReplyTo(InternetAddress.parse(from,false));
        // noi dungss
        //msg.setText(content,"UTF-8");
        msg.setContent(content,"text/html; charset=UTF-8"); // co the hieu html
        Transport.send(msg);
        }
        
//    public static void main(String[] args) throws MessagingException {
//        Email.sendEmail(from, from, from);
//        
//    }
}
