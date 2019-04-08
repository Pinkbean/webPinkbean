package com.pinkbean.ga.payco.connect;

import org.springframework.social.oauth2.OAuth2Template;

/**
 * PaycoOAuth2Template
 * name		: Payco의 인증 전용 탬플릿
 * desc		: Access Tocken을 할당받을 때까지 사용한다.
 * date		: 2019.04.08
 * ---------------------------------------------
 * @author admin
 *
 */
public class PaycoOAuth2Template extends OAuth2Template {	
	
	public PaycoOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		
		// client_id와 client_secret을 사용할 것인가?
		super.setUseParametersForClientAuthentication(true);
	}
}
