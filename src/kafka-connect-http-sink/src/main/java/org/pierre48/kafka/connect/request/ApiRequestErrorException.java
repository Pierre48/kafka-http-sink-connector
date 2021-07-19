package org.pierre48.kafka.connect.request;

import org.pierre48.kafka.connect.KafkaRecord;

public class ApiRequestErrorException extends CallBackApiException {
    KafkaRecord record;
    public ApiRequestErrorException(String msg) {
        super(msg);
    }

    public ApiRequestErrorException(String msg, KafkaRecord record) {
        super(msg);
        this.record = record;
    }

    @Override
    public KafkaRecord getRecord(){
        return record;
    }
}
