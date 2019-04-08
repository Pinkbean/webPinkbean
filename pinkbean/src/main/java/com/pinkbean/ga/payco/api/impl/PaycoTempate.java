package com.pinkbean.ga.payco.api.impl;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.pinkbean.ga.payco.api.Payco;

public class PaycoTempate extends AbstractOAuth2ApiBinding implements Payco{

	private final String getIdNoUrl;
	private final String getUserProfileUrl;
	private final String removeServiceUrl;
	private final String clientId;
	private String accessToken;
	
	private RestTemplate restTemplate;
    
	/**
     * PaycoTempate
     * 클래스 생성자.
	 * 
	 * @param clientId
	 * @param clientSecret
	 * @param getIdNoUrl
	 * @param getUserProfileUrl
	 * @param removeServiceOfferUrl
	 */
    public PaycoTempate(String clientId, String clientSecret, String getIdNoUrl, String getUserProfileUrl, String removeServiceUrl) {
    	this.getIdNoUrl	  			= getIdNoUrl;
    	this.getUserProfileUrl	  	= getUserProfileUrl;
    	this.removeServiceUrl		= removeServiceUrl;
    	this.clientId	  = clientId;
    }
    
    /**
     * PaycoTempate
     * 클래스 생성자.
     * 
     * @param clientId
     * @param clientSecret
     * @param getIdNoUrl
     * @param getUserProfileUrl
     * @param removeServiceOfferUrl
     * @param accessToken
     */
    public PaycoTempate(String clientId, String clientSecret, String getIdNoUrl, String getUserProfileUrl, String removeServiceUrl, String accessToken) {
    	this.getIdNoUrl	  			= getIdNoUrl;
    	this.getUserProfileUrl	  	= getUserProfileUrl;
    	this.removeServiceUrl		= removeServiceUrl;
    	this.accessToken  			= accessToken;
    	this.clientId	  			= clientId;
    }  
    
    /**
     * setAccessToken
     * 로직상 accessToken을 할당받는 시점이 클래스 생성 이후이기에 별도 추가한다.
     * 
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
    	this.accessToken = accessToken;
    }
    
    /**
     * userProfile
     * userProfile을 조회하는 메소드
     * 
     */
    @Override
	@SuppressWarnings("unchecked")
	public UserProfile userProfile() {
		
    	HttpEntity<MultiValueMap<String, String>> request = null;
    	MultiValueMap<String, String> params = null;
    	HttpHeaders headers = null;
    	
    	// header Setting
		headers = new HttpHeaders();
		headers.add("access_token", this.accessToken);
		headers.add("client_id", this.clientId);
		
		// body Setting
		params = new LinkedMultiValueMap<String, String>();
		params.set("access_token", this.accessToken);
		params.set("client_id", this.clientId);
		
		// create httpEntity
		request = new HttpEntity(params, headers);
		
		// call rest
		return extractUserProfile(super.getRestTemplate().postForObject(this.getUserProfileUrl, request, Map.class));
	}	    
    
    /**
     * extractUserProfile
     * 전달받은 return값을 파싱하여 UserProfile객체를 만들어 넘겨준다.
     * 
     * @param result
     * @return
     */
	private UserProfile extractUserProfile(Map<String, Object> result) {	
		System.out.println("result :: "+result.toString());
		return new UserProfile(((Map)result.get("memberProfile")).get("id").toString(), null, null, null, null, null);
	}

	@Override
	public boolean isAuthorized() {
		return this.accessToken!=null;
	}	
}
