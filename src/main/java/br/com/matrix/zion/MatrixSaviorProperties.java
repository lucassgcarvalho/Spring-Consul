package br.com.matrix.zion;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
//@ConfigurationProperties(prefix = "savior")
@Data
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
}
