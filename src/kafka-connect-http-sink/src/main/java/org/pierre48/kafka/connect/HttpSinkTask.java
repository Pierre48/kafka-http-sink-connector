package org.pierre48.kafka.connect;

import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.util.*;

import org.pierre48.kafka.connect.request.ApiRequestInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.source.SourceRecord;


    public class HttpSinkTask extends SinkTask {

        private static Logger log = LoggerFactory.getLogger(HttpSinkTask.class);

        private String connectorName;
        private HttpSinkConnectorConfig config;
        private List<String> sources;
        private ApiRequestInvoker apiRequestInvoker;

        @Override
        public String version() {
            return PropertiesUtil.getConnectorVersion();
        }








        @Override
        public void start(Map<String, String> properties) {
            connectorName = context.configs().get("name");
            config = new HttpSinkConnectorConfig(properties);
            String sourcesStr = properties.get("sources");
            sources = Arrays.asList(sourcesStr.split(","));
            apiRequestInvoker = new ApiRequestInvoker(config, context);
        }

        @Override
        public void put(Collection<SinkRecord> records) {
            long start = System.currentTimeMillis();

            int batchSize = records.size();
            log.debug("Totals records:{}", batchSize);
            if (records.isEmpty()) {
                return;
            }
            apiRequestInvoker.invoke(records);

            //Request a commit of the processed message
            //else the commit is triggered after the  'offset.flush.interval.ms'
            context.requestCommit();

            long executionTime = System.currentTimeMillis() - start;
            log.info("Metrics=Latency metricSystem=kafka-connector-{} metricMeasure=batch-processing-time metricValue={} batchSize={}", connectorName, executionTime, batchSize);
        }


        @Override
        public void stop() {
        }

    }