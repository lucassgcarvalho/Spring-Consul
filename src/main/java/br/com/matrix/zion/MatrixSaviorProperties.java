package br.com.matrix.zion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
//@ConfigurationProperties(prefix = "savior")
public class MatrixSaviorProperties {


    @Value("${savior.name}")
    private String saviorName;

    @Value("${savior.action}")
    private String saviorAction;

    @Override
    public String toString() {
        return "MatrixSaviorProperties {" +
                "saviorName='" + saviorName + '\'' +
                ", saviorAction='" + saviorAction + '\'' +
                '}';
    }

    public String getSaviorAction() {
        return saviorAction;
    }

    public void setSaviorAction(String saviorAction) {
        this.saviorAction = saviorAction;
    }

    public String getSaviorName() {
        return saviorName;
    }

    public void setSaviorName(String saviorName) {
        this.saviorName = saviorName;
    }
}
