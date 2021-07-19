package org.pierre48.kafka.connect.handler;

import org.apache.kafka.connect.sink.SinkTaskContext;
import org.pierre48.kafka.connect.request.CallBackApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropMessageHandler implements ExceptionHandler {
    private final SinkTaskContext sinkContext;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    DropMessageHandler(SinkTaskContext context) {
        log.info("Exception strategy: Drop message Strategy.");
        this.sinkContext = context;
    }

    @Override
    public void handle(CallBackApiException e) {
        log.error("Drop message Strategy: Dropping message {}" , e.getRecord());
        sinkContext.requestCommit();
    }
}
