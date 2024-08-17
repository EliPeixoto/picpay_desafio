package tech.buildrun.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InsuficientBalanceException extends PicPayException {
    @Override
    public ProblemDetail toProblemDetail() {
        var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        pb.setTitle("Insuficient balance!!");
        pb.setDetail("You cannot transfer a value bigger than your current balance");
        return pb;
    }
}
