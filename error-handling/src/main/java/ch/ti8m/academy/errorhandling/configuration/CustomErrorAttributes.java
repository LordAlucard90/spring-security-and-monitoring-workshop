package ch.ti8m.academy.errorhandling.configuration;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Configuration
public class CustomErrorAttributes extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                  ErrorAttributeOptions options) {
        var errorAttributesMap = super.getErrorAttributes(webRequest, options);
        errorAttributesMap.put("locale", webRequest.getLocale());
        //  errorAttributesMap.remove("status");
        return errorAttributesMap;
    }
}
