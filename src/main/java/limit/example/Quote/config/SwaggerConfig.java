package limit.example.Quote.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI quoteApi() {
    return new OpenAPI()
        .info(new Info()
            .title("Quote API Working")
            .description("A simple inspirational Quote API with rate limiting per IP")
            .version("1.0"));
  }
}
