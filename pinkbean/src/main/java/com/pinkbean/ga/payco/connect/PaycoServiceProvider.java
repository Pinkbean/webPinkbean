package com.pinkbean.ga.payco.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.pinkbean.ga.payco.api.Payco;
import com.pinkbean.ga.payco.api.impl.PaycoTempate;

/**
 * PaycoServiceProvider
 * name		: Payco의 서비스 제공자 클래스
 * desc		: Payco 서비스를 제공한다. Template를 생성하여 가지고 있으며, 요청 시 전달.
 * date		: 2019.04.08
 * ---------------------------------------------
 * @author admin
 *
 */
public class PaycoServiceProvider extends AbstractOAuth2ServiceProvider<Payco> {
	
	private PaycoTempate paycoTemplate;
	
	/**
	 * PaycoServiceProvider
	 * 서비스제공자의 생성자
	 * 
	 * @param clientId
	 * @param clientSecret
	 */
    public PaycoServiceProvider(String clientId, String clientSecret) {
    	
    	// 권한Template를 생성
        super(new PaycoOAuth2Template(clientId, clientSecret,
                "https://id.payco.com/oauth2.0/authorize",
                "https://id.payco.com/oauth2.0/token"));
        
        // 그 외의 payco api를 이용하는 Tempate 생성
        this.paycoTemplate = new PaycoTempate(clientId, clientSecret,
        		"https://apis3.krp.toastoven.net/payco/friends/getIdNoByFriendsToken.json",
        		"https://apis3.krp.toastoven.net/payco/friends/getMemberProfileByFriendsToken.json",
        		"https://apis3.krp.toastoven.net/payco/friends/removeServiceOfferByIdNoAndConsumerKeyAndServiceProviderCode.json");
    }

    /**
     * getApi
     * api getter
     */
	@Override
	public Payco getApi(String accessToken) {
		this.paycoTemplate.setAccessToken(accessToken);
		return this.paycoTemplate;
	}
}
