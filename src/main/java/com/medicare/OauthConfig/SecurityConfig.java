package com.medicare.OauthConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@SuppressWarnings("deprecation")
@Configuration
public class SecurityConfig extends AuthorizationServerConfigurerAdapter {

	private String clientid = "gxOauth";
	private String clientSecret = "Galaxy@12345";
	private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIICXgIBAAKBgQCyat8MyFGl22gvQTG+x2GVWsVbZlyS2Wf+guV9rlSoehCS+xbh\r\n"
			+ "kNKyHQxGDS8qcbcKaTPe4HvFQWxUFbrIu9t0n6l57YuGArv5uQb2nAWPG8roOVxL\r\n"
			+ "aMS5rLaNC1wG60o5+1MlCWsthB91FSM941QtMS/fa0hN9Q7T3YTwzB2d2wIDAQAB\r\n"
			+ "AoGADvLbW2PRS3c1pHfu5hAoge4ICR9rdDZY+z8WYVBg8XjAuL/m6+FJdfjvFVN3\r\n"
			+ "dzPt5SZpXSwr0pwnqmfjrXvn6BqVUMRYvkWBskDQ3Sux/WbObpdGqhOhvnlCxTLP\r\n"
			+ "IRlhkf2FNXtdUH9Uckxc7hK4HuhaWCYgjGHR1sKcp/uXaMECQQDsgMu2VZCc1OFM\r\n"
			+ "ROgvesiprzjr47sdxGSsjn3FCQ5nqRJseLeJOS6cWJySiclmJQ7171HKlQp175vp\r\n"
			+ "u/tm5+ShAkEAwSA5xTJi8o/gmEArNcz5Oe/LkaqqYyjjVimHt+Ef7rA+HmRtkTjD\r\n"
			+ "JyNoQP3zgMPP7nRTm7nQRu60dTvyWnf0+wJBAMdmA9s0IxwHY/jg8/3sALkH8e74\r\n"
			+ "b2+2gMb30FRKwWlmpBDuapf4rTLGhyQJpdNIFWgHIaeyp0IuBf84LctiYmECQQC/\r\n"
			+ "fkuqG77/Eui5Lo9p5leNOhdY1afjcMySR9W53zDBhOHjUuNNpsHi0fnyz5weJorL\r\n"
			+ "v26Tr9g0LUakJA+34s/3AkEAtIHuu5NiPtDUsCBdMD12V585lBDU2oceVRLfCReR\r\n"
			+ "yuQgQEwc4H3kR4RvU3abB4mpgTOkqCRliJQEnCzK1agvBg==\r\n" + "-----END RSA PRIVATE KEY-----";

	private String publicKey = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCyat8MyFGl22gvQTG+x2GVWsVb\r\n"
			+ "ZlyS2Wf+guV9rlSoehCS+xbhkNKyHQxGDS8qcbcKaTPe4HvFQWxUFbrIu9t0n6l5\r\n"
			+ "7YuGArv5uQb2nAWPG8roOVxLaMS5rLaNC1wG60o5+1MlCWsthB91FSM941QtMS/f\r\n" + "a0hN9Q7T3YTwzB2d2wIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

	@Autowired
	AuthenticationConfiguration authenticationConfiguration;

	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		converter.setSigningKey(privateKey);
		converter.setVerifierKey(publicKey);
		return converter;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationConfiguration.getAuthenticationManager())
		.tokenStore(tokenStore())
		.accessTokenConverter(tokenEnhancer());
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
		.checkTokenAccess("isAuthenticated()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
		.withClient(clientid)
		.secret(passwordEncoder().encode(clientSecret))
		.scopes("read", "write")
		.authorizedGrantTypes("password","client_credentials")
		.accessTokenValiditySeconds(3000)
		.refreshTokenValiditySeconds(3000);
	}

}
