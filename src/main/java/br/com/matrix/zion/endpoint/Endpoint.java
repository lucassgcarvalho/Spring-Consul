package br.com.matrix.zion.endpoint;

import br.com.matrix.zion.MatrixProperties;
import br.com.matrix.zion.MatrixSaviorProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Endpoint {

    @Value("${bullet.main}")
    String value;

    @Autowired
    private MatrixProperties properties;

    @Autowired
    private MatrixSaviorProperties matrixSaviorProperties;

    @GetMapping(value = "/", produces = "Application/json")
    public String getConfigFromValue() {
        return properties.toString() + "\n"+matrixSaviorProperties.toString();
    }

    @GetMapping("/static")
    public String getConfigFromProperty() {
        return value;
    }
}
