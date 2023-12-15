package me.saniukvyacheslav.core.logging;

import me.saniukvyacheslav.Logger;
import me.saniukvyacheslav.conf.LoggersConfiguration;
import me.saniukvyacheslav.message.LoggingMessageLevel;

public class LoggingConfiguration {

    private static LoggingConfiguration INSTANCE;
    private final LoggersConfiguration loggersConfiguration;

    private LoggingConfiguration() {
        this.loggersConfiguration = LoggersConfiguration.LoggerConfigurationBuilder.ofName("sizeAnalyzerLoggersConfiguration")
                .loggingMessagePattern("[%LEVEL%] %TIME% %NAME%: %MSG%")
                .useCanonicalLoggersName(false)
                .minimalLevelOfMessages(LoggingMessageLevel.TRACE)
                .enableConsoleOutput(true)
                .enableLoggers(true)
                .build();
    }

    public static Logger getLogger(Class<?> aLoggableClass) {
        return LoggingConfiguration.getInstance().loggersConfiguration.getLogger(aLoggableClass);
    }

    private static LoggingConfiguration getInstance() {
        if (LoggingConfiguration.INSTANCE == null) LoggingConfiguration.INSTANCE = new LoggingConfiguration();
        return LoggingConfiguration.INSTANCE;
    }

}
