package pl.sternik.as.projekt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.context.annotation.*;
import org.springframework.context.annotation.ComponentScan.Filter;

@Configuration
@ComponentScan(basePackages = {"pl.sternik.as.projekt"}, excludeFilters = {
        @Filter(type = FilterType.REGEX, pattern = "pl\\.sternik\\.as\\.projekt\\.web\\..*")})
@ImportResource({"classpath:/applicationContext*.xml"})//,"classpath:/database-config.xml"})
public class SpringBusinessConfig {

    @Bean
    @Scope("prototype")
    public Logger logger(InjectionPoint injectionPoint) {
        Class<?> containingClass = injectionPoint.getField().getDeclaringClass();
        return LoggerFactory.getLogger(containingClass);
    }

}
