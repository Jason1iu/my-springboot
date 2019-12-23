package com.example.myspringboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

/** 
* @author LiuJie 
* @date 2019年12月23日 下午5:05:05 
* web全局异常返回处理器
*/
@Configuration
public class WebResponseExceptionTranslateConfig {

    /**
     * 自定义登录或者鉴权失败时的返回信息
     */
    @Bean(name = "webResponseExceptionTranslator")
    public WebResponseExceptionTranslator<OAuth2Exception> webResponseExceptionTranslator() {
        return new DefaultWebResponseExceptionTranslator() {
            @SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
            public ResponseEntity translate(Exception e) throws Exception {
				ResponseEntity responseEntity = super.translate(e);
                OAuth2Exception body = (OAuth2Exception) responseEntity.getBody();
                HttpHeaders headers = new HttpHeaders();
                headers.setAll(responseEntity.getHeaders().toSingleValueMap());
                if ( 400 == responseEntity.getStatusCode().value() ) {
                    System.out.println(body.getMessage());
                    if ( "Bad credentials".equals(body.getMessage()) ) {
                        return new ResponseEntity("您输入的用户名或密码错误" , headers , HttpStatus.OK);
                    }
                }
                return new ResponseEntity(body , headers , responseEntity.getStatusCode());
            }
        };
    }
}
