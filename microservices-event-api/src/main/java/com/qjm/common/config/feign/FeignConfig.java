package com.qjm.common.config.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author qianjm
 * @date 2018/9/10
 */
@Configuration
public class FeignConfig implements RequestInterceptor {

  @Override
  public void apply(RequestTemplate requestTemplate) {
    requestTemplate.header("token","67f98e6f-4942-42bc-a8e5-1ffa070aafdb");
  }

  private HttpServletRequest getHttpServletRequest() {
    try {
      RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
      return ((ServletRequestAttributes) requestAttributes).getRequest();
    } catch (Exception e) {
      return null;
    }
  }

  private Map<String,String> getHeaders(HttpServletRequest request){
    Map<String, String> map = new LinkedHashMap<>();

    Enumeration<String> enumeration = request.getHeaderNames();
    while (enumeration.hasMoreElements()){
      String key = enumeration.nextElement();
      String value = request.getHeader(key);
      map.put(key,value);
    }
    return map;

  }
}
