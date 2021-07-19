package org.pierre48.kafka.connect.request;

import org.pierre48.kafka.connect.KafkaRecord;

public class ApiResponseErrorException extends CallBackApiException {
    KafkaRecord record;
    public ApiResponseErrorException(String msg) {
        super(msg);
    }

    public ApiResponseErrorException(String msg, KafkaRecord record) {
        super(msg);
        this.record = record;
    }

    public KafkaRecord getRecord(){
        return record;
    }
}
