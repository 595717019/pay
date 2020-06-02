package com.tre.bill.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {
    //	@Bean
//	public Docket webApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//		        .groupName("支付后台API接口文档")
//		        .apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.itstyle.modules.web"))
//				.paths(PathSelectors.any()).build();
//	}
//	@Bean
//	public Docket alipayApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//		        .groupName("1. 支付宝API接口文档")
//		        .apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.itstyle.modules.alipay"))
//				.paths(PathSelectors.any()).build();
//	}
//	@Bean
//	public Docket weixinpayApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("2. 微信API接口文档")
//				.apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.itstyle.modules.weixinpay"))
//				.paths(PathSelectors.any()).build();
//	}
//	@Bean
//	public Docket suixingpayApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("3. 随行付API接口文档")
//				.apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.itstyle.modules.suixingpay"))
//				.paths(PathSelectors.any()).build();
//	}
//	@Bean
//	public Docket xorpayApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("4. XorPay API接口文档")
//				.apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.itstyle.modules.xorpay"))
//				.paths(PathSelectors.any()).build();
//	}
//	@Bean
//	public Docket unionpayApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("5. 银联API接口文档")
//				.apiInfo(apiInfo())
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.itstyle.modules.unionpay"))
//				.paths(PathSelectors.any()).build();
//	}
    @Bean
    public Docket payApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("1. 支付API接口文档")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tre.bill.web.controller"))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("对账系统")
                .description("微信、支付宝、银联、第三方对账服务")
                .termsOfServiceUrl("")
                .contact(new Contact("林乐锋 ", "", "595717019@qq.com"))
                .version("1.0").build();
    }

}