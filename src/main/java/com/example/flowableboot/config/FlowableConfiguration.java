package com.example.flowableboot.config;

import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class FlowableConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }


    @Bean
    public ProcessEngineConfiguration processEngineConfiguration(DataSource dataSource, PlatformTransactionManager transactionManager) {
        SpringProcessEngineConfiguration config = new SpringProcessEngineConfiguration();
        config.setDataSource(dataSource);
        config.setTransactionManager(transactionManager);
        config.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // Async history configuration
        config.setAsyncHistoryEnabled(true);
        config.setAsyncHistoryExecutorActivate(true);

        // Optional tweaking
        config.setAsyncHistoryJsonGroupingEnabled(true);
        config.setAsyncHistoryJsonGzipCompressionEnabled(true);
        config.setAsyncHistoryJsonGroupingThreshold(10);

//        // To speed up the example. Don't use this in production, it'll hammer the db.
//        config.setAsyncExecutorDefaultAsyncJobAcquireWaitTime(50);

        return config;
    }

}
