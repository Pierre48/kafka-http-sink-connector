package org.pierre48.kafka.connect.request;


import org.pierre48.kafka.connect.KafkaRecord;

public class CallBackApiException extends RuntimeException {
    public CallBackApiException(String msg) {
        super(msg);
    }
    public KafkaRecord getRecord(){
        return null;
    }
}
