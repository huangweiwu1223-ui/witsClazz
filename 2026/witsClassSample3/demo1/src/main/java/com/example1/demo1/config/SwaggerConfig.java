package com.example1.demo1.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger / OpenAPI 設定。
 * <p>
 * 加入全域 Header 參數 {@code X-Auth-Token}，這樣 Swagger UI 上方按
 * "Authorize" 後，每個請求都會自動帶 token。
 * </p>
 */
// @Configuration
public class SwaggerConfig {

    /** Header 名稱 */
    private static final String AUTH_HEADER = "X-Auth-Token";

    @Bean
    public OpenAPI worldOpenAPI() {
        // 定義一個 apiKey 型別的 security scheme，從 Header 帶入
        SecurityScheme tokenScheme = new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name(AUTH_HEADER)
                .description("教學示範用 token，請先呼叫 /api/auth/login 取得");

        // 也加一個全域 HeaderParameter，Swagger UI 會在每個端點都顯示輸入框
        HeaderParameter headerParam = (HeaderParameter) new HeaderParameter()
                .name(AUTH_HEADER)
                .description("X-Auth-Token (教學示範)")
                .required(false);

        return new OpenAPI()
                .info(new Info()
                        .title("World.city 資料表操作")
                        .version("1.0")
                        .description("使用 Spring Boot 3 + MyBatis-Plus 操作 world.city 資料表的教學專案"))
                .addSecurityItem(new SecurityRequirement().addList(AUTH_HEADER))
                .components(new Components()
                        .addSecuritySchemes(AUTH_HEADER, tokenScheme)
                        .addParameters(AUTH_HEADER, headerParam));
    }
}
