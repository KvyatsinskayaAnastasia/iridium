package com.iridium.configuration;

import com.iridium.library.repository.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.iridium", repositoryBaseClass = BaseRepositoryImpl.class)
public class DataSourceConfiguration {
}
