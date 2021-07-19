package org.pierre48.kafka.connect;

import java.util.Map;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.common.config.ConfigDef.Importance;
import org.apache.kafka.common.config.ConfigDef.Type;
import org.pierre48.kafka.connect.handler.ResponseExceptionStrategyHandlerFactory;

public class HttpSinkConnectorConfig extends AbstractConfig {

    public String headerSeparator;
    public String headers;
    public String[] requestRetryBackoffsec;
    public String[] responseRetryBackoffsec;
    public KafkaRecord requestMethod;
    public int connectTimeout;
    public int readTimeout;
    public String httpApiUrl;
    public ResponseExceptionStrategyHandlerFactory.ExceptionStrategy exceptionStrategy;

    public HttpSinkConnectorConfig(final Map<?, ?> originalProps) {
        super(CONFIG_DEF, originalProps);
    }

    public static final String TARGET_URL_CONFIG = "target.url.param";
    private static final String TARGET_URL_DOC = "The url of the target web-api";


    public static final ConfigDef CONFIG_DEF = createConfigDef();

    private static ConfigDef createConfigDef() {
        ConfigDef configDef = new ConfigDef();
        addParams(configDef);
        return configDef;
    }

    private static void addParams(final ConfigDef configDef) {
        configDef.define(
                TARGET_URL_CONFIG,
                Type.STRING,
                Importance.HIGH,
                TARGET_URL_DOC);
    }

}
