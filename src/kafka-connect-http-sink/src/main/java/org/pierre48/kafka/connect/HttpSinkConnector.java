package org.pierre48.kafka.connect;

import org.apache.kafka.common.config.Config;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigValue;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.pierre48.kafka.connect.HttpSinkConnectorConfig.*;


public class HttpSinkConnector extends SinkConnector {

    private final Logger log = LoggerFactory.getLogger(HttpSinkConnector.class);

    private Map<String, String> originalProps;
    private HttpSinkConnectorConfig config;

    @Override
    public String version() {
        return PropertiesUtil.getConnectorVersion();
    }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return HttpSinkTask.class;
    }

    @Override
    public Config validate(Map<String, String> connectorConfigs) {
        Config config = super.validate(connectorConfigs);
        List<ConfigValue> configValues = config.configValues();
        boolean missingTopicDefinition = true;
        for (ConfigValue configValue : configValues) {
            if (configValue.name().equals(TARGET_URL_CONFIG)) {
                if (configValue.value() != null) {
                    missingTopicDefinition = false;
                    break;
                }
            }
        }
        if (missingTopicDefinition) {
            throw new ConnectException(String.format(
                    "There is no definition of [XYZ] in the "
                            + "configuration. Either the property "
                            + "'%s'  must be set in the configuration.",
                    TARGET_URL_CONFIG));
        }
        return config;
    }

    @Override
    public void start(Map<String, String> originalProps) {
        this.originalProps = originalProps;
        config = new HttpSinkConnectorConfig(originalProps);
        String urlConfig = config.getString(TARGET_URL_CONFIG);
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        List<Map<String, String>> taskConfigs = new ArrayList<>();
        return taskConfigs;
    }

    @Override
    public void stop() {
    }
}
