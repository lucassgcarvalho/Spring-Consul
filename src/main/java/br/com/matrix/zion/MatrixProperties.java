package br.com.matrix.zion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
//@ConfigurationProperties(prefix = "bullet")
public class MatrixProperties {

    @Value("${bullet.main}")
    private String prop;

    public String getProp() {
        return prop;
    }

    @Override
    public String toString() {
        return "MatrixProperties{" +
                "prop='" + prop + '\'' +
                '}';
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

}
