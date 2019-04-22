package com.pinkbean.ga.payco.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.mornya.lib.spring.social.naver.api.abstracts.UserOperation;
import com.pinkbean.ga.payco.api.Payco;

public class PaycoAdapter implements ApiAdapter<Payco> {

	@Override
	public boolean test(Payco api) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setConnectionValues(Payco api, ConnectionValues values) {
		UserProfile userProfile = api.userProfile();
		values.setProviderUserId(userProfile.getId());
	}

	@Override
	public UserProfile fetchUserProfile(Payco api) {
		return api.userProfile();
	}

	@Override
	public void updateStatus(Payco api, String message) {
		// TODO Auto-generated method stub
	}
}
