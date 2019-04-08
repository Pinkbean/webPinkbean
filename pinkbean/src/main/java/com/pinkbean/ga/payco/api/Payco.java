package com.pinkbean.ga.payco.api;

import org.springframework.social.ApiBinding;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.facebook.api.UserOperations;

public interface Payco extends ApiBinding {

	UserProfile userProfile();
}
