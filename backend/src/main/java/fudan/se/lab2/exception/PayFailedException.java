package fudan.se.lab2.exception;

import fudan.se.lab2.domain.Fine;

public class PayFailedException extends RuntimeException {
    public PayFailedException(String s){
        super(s);
    }
}
