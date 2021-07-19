package org.pierre48.kafka.connect.handler;

import org.pierre48.kafka.connect.request.CallBackApiException;

public interface ExceptionHandler {

    void handle(CallBackApiException e);

    /**
     * Reset the handlers retry index if present
     * In-case the message was processed successfully in one of the retry
     * the next message retry should not start from previously incremented index
     */
    default void reset(){}
}
