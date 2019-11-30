package ufrn.microservice.question.docs;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import ufrn.microservice.core.docs.BaseSwaggerConfig;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    public SwaggerConfig(){
        super("ufrn.microservice.question.endpoint.controller");
    }
}
