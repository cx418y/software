package fudan.se.lab2.Assist;

import fudan.se.lab2.domain.MailBean;
import fudan.se.lab2.exception.MailSendFailException;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Component
public class MailUtil {
    public static void sendSimpleTextEmail(MailBean mailBean) throws EmailException {
        // 发送简单的email,不能添加附件
        Email email = new SimpleEmail();
        // 邮件服务器域名
        email.setHostName("smtp.qq.com");
        email.setAuthentication("1419689421@qq.com","lgoldzwhrppejafd");
        email.setCharset("UTF-8");
        email.setFrom("1419689421@qq.com");

        email.setSmtpPort(465);
        email.setSSLOnConnect(true);
        System.out.println(mailBean.getRecipient());
        email.addTo(mailBean.getRecipient());
        email.setSubject(mailBean.getSubject());
        email.setMsg(mailBean.getContent());

        try {
            email.send();
        }catch (Exception e){
            throw new MailSendFailException("send email to" + mailBean.getRecipient() + "fail");
        }

         }

}
