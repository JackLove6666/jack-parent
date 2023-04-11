package com.cloud.jack.oauth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;

@Configuration
@EnableAuthorizationServer
public class Oauth2ServerConfig  extends AuthorizationServerConfigurerAdapter{


    /**
     * 用来配置令牌端点的安全约束
     * @param security
     * @throws Exception
     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security.checkTokenAccess("permitAll()").allowFormAuthenticationForClients().passwordEncoder(NoOpPasswordEncoder.getInstance());
//    }

    /**
     * 配置客戶端詳情
     * 允许客户端自己申请ClientId
     * @param clients
     * @throws Exception
     */
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//        builder.withClient("client")
//                .redirectUris("http://www.baidu.com")
//                .secret("secret")
//                .authorizedGrantTypes("password","authorization_code","refresh_token")
//                .accessTokenValiditySeconds(7200)
//                .scopes("all","read","write")
//                .refreshTokenValiditySeconds(60 * 60 * 24 * 30);
//    }

    /**
     * 用来配置令牌(token)的访问端点和令牌服务(tokenServices)
     * @param endpoints
     * @throws Exception
     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        super.configure(endpoints);
//    }
}
