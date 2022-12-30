package fudan.se.lab2.exception;

public class MailSendFailException extends RuntimeException {

        public MailSendFailException(String s) {
            super("Fail to send email to " + s);
        }

}
