package br.com.matrix.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@RefreshScope
public class PropertiesConfig {
    @Value("${validation.entity.user.age.min:1}")
    private Integer ageMin;

    @Value("${validation.entity.user.age.max:120}")
    private Integer ageMax;
}
