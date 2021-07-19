package org.pierre48.kafka.connect.request;

import org.pierre48.kafka.connect.HttpSinkConnectorConfig;
import org.pierre48.kafka.connect.KafkaRecord;

public interface RequestBuilder {

    Request createRequest(HttpSinkConnectorConfig config, KafkaRecord kafkaRecord);
}
