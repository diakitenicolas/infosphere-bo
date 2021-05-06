package io.infosphere.bo.configuration;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/** Properties are configured in the {@code ${spring.config.location}/application.yml} file. */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = true)
@PropertySource("file:${spring.config.location}/application-h2.yml")
@EnableEncryptableProperties
@Profile("!test")
@NoArgsConstructor
public class ApplicationProperties {

  // To make property annotated by {@code @Value} injectable using placeholder
  @Bean
  public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }
}
