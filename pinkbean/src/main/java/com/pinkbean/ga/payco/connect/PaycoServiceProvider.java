package com.pinkbean.ga.payco.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

import com.pinkbean.ga.payco.api.Payco;
import com.pinkbean.ga.payco.api.impl.PaycoTempate;

/**
 * PaycoServiceProvider
 * name		: Payco�� ���� ������ Ŭ����
 * desc		: Payco ���񽺸� �����Ѵ�. Template�� �����Ͽ� ������ ������, ��û �� ����.
 * date		: 2019.04.08
 * ---------------------------------------------
 * @author admin
 *
 */
public class PaycoServiceProvider extends AbstractOAuth2ServiceProvider<Payco> {
	
	private PaycoTempate paycoTemplate;
	
	/**
	 * PaycoServiceProvider
	 * ������������ ������
	 * 
	 * @param clientId
	 * @param clientSecret
	 */
    public PaycoServiceProvider(String clientId, String clientSecret) {
    	
    	// ����Template�� ����
        super(new PaycoOAuth2Template(clientId, clientSecret,
                "https://id.payco.com/oauth2.0/authorize",
                "https://id.payco.com/oauth2.0/token"));
        
        // �� ���� payco api�� �̿��ϴ� Tempate ����
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
