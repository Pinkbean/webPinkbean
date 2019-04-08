package com.pinkbean.ga.payco.connect;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;

import com.pinkbean.ga.payco.api.Payco;

public class PaycoConnectionFactory extends OAuth2ConnectionFactory<Payco> {

    public PaycoConnectionFactory(String clientId, String clientSecret) {
        super("payco", new PaycoServiceProvider(clientId, clientSecret), new PaycoAdapter());
    }
}
