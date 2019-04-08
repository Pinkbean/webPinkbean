package com.pinkbean.ga.payco.connect;

import org.springframework.social.oauth2.OAuth2Template;

/**
 * PaycoOAuth2Template
 * name		: Payco�� ���� ���� ���ø�
 * desc		: Access Tocken�� �Ҵ���� ������ ����Ѵ�.
 * date		: 2019.04.08
 * ---------------------------------------------
 * @author admin
 *
 */
public class PaycoOAuth2Template extends OAuth2Template {	
	
	public PaycoOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
		super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
		
		// client_id�� client_secret�� ����� ���ΰ�?
		super.setUseParametersForClientAuthentication(true);
	}
}
