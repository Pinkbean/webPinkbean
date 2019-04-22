package com.pinkbean.ga.web.login.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * LoginSocialController
 * name		: 소셜 로그인 컨트롤러
 * desc		: 소셜 로그인을 수행하는 컨트롤러이다.
 * date		: 2019.04.04
 * ---------------------------------------------
 * @author admin
 *
 */
@Controller
public class LoginSocialController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginSocialController.class);
	
	@Autowired
	private ConnectionFactoryRegistry connectionFactoryLocator;	
	
	@Autowired
	private RestTemplate restTemplate;	
	
	//private static final String BASE_URL = "https://192.168.0.230:8443";
	private static final String BASE_URL = "https://www.pinkbean.ga:443";
	
	/**
	 * loginHome
	 * 로그인 홈
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginHome(Locale locale, Model model) {
		System.out.println("Home Page Requested, locale = " + locale);
		ModelAndView mv = new ModelAndView();

		mv.setViewName("login");
		return mv;
	}	
	
	/**
	 * paycoLogin
	 * payco 간편로그인 
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */	
	@RequestMapping(value = "/login/{providerId}", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public void paycoLogin(HttpServletResponse response, @PathVariable String providerId) {

		OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
		OAuth2Operations oAuthOperations = connectionFactory.getOAuthOperations();
		
		// Parameter
		OAuth2Parameters parameters = new OAuth2Parameters();		
		parameters.setRedirectUri(this.BASE_URL + "/login/"+providerId+"/callback");
		parameters.set("serviceProviderCode", "FRIENDS");
		
		String authorizeUrl = oAuthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, parameters);

		try {
			response.sendRedirect(authorizeUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}	
	
	/**
	 * paycoLoginCallback
	 * payco auto code 발급
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */		
	@SuppressWarnings({ "unchecked", "static-access", "rawtypes" })
	@RequestMapping(value = "/login/{providerId}/callback", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
	public void paycoLoginCallback(HttpServletResponse response, 
			   @RequestParam(value = "code", required = false) String authorizeCode,
			   @RequestParam(value = "access_token", required = false) String access_token,
			   @RequestParam(value = "refresh_token", required = false) String refresh_token,
			   @PathVariable String providerId,
								   HttpSession session) {	
		
		OAuth2ConnectionFactory<?> connectionFactory = (OAuth2ConnectionFactory<?>) connectionFactoryLocator.getConnectionFactory(providerId);
		OAuth2Operations oAuthOperations = connectionFactory.getOAuthOperations();

		AccessGrant accessGrant = null;
		for(int i = 0; i < 3; i++) {
			try {
				accessGrant = oAuthOperations.exchangeForAccess(authorizeCode, this.BASE_URL + "/login/"+providerId+"/callback", null);
				break;		// accessGrant 가 성공하면 for loop 즉시 종료
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}	

		if(accessGrant == null) {
			System.out.println(" ::: TODO error :: no Grant 예외처리 필요");
			return;
		}
		
		session.setAttribute("access_token", 	accessGrant.getAccessToken());
		session.setAttribute("refresh_token", 	accessGrant.getRefreshToken());
		session.setAttribute("expires_in", 		accessGrant.getExpireTime());		
		
		Connection connection = connectionFactory.createConnection(accessGrant);
		ConnectionData connectionData = connection.createData();
			
		// UserProfile userprofile = connection.fetchUserProfile();	
		if (connectionData != null) {
			session.setAttribute("user_id", connectionData.getProviderUserId());
		}
		
		// session에 ID가 존재하는 경우를 기존에 가입했던 회원이 있는 경우라고 가정한다.
		// 아이디가 존재할 경우, 회원가입 페이지로 이동해야 한다. 이 때 각 소셜마다 서로 다른 페이지로 이동한다.
		
		try {
			response.sendRedirect("/login");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	/**
	 * logout
	 * 로그아웃
	 * 
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(Locale locale, Model model, HttpSession session) {
		System.out.println("Home Page Requested, locale = " + locale);
		ModelAndView mv = new ModelAndView();

		session.removeAttribute("access_token");
		session.removeAttribute("refresh_token");
		session.removeAttribute("expires_in");		
		
		mv.setViewName("login");
		return mv;
	}		
}
