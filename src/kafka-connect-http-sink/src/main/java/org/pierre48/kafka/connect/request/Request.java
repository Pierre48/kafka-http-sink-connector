package org.pierre48.kafka.connect.request;

public interface Request {

    Request setHeaders(String headers, String traceId, String separator);
    void sendPayload(String payload);
}
