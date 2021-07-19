package org.pierre48.kafka.connect.handler;

import org.apache.kafka.connect.sink.SinkTaskContext;
import org.pierre48.kafka.connect.HttpSinkConnectorConfig;

public class RequestExceptionStrategyHandlerFactory {

    public enum ExceptionStrategy{
        PROGRESS_BACK_OFF_STOP_TASK,
    }

    public static ExceptionHandler getInstance(ExceptionStrategy exceptionStrategy, HttpSinkConnectorConfig config, SinkTaskContext context){
        switch(exceptionStrategy){
            case PROGRESS_BACK_OFF_STOP_TASK: return new ProgressiveBackoffStopTaskHandler(config,context, config.requestRetryBackoffsec);
            default: throw new StrategyNotFoundException();
        }
    }


    private static class StrategyNotFoundException extends RuntimeException{
    }
}
