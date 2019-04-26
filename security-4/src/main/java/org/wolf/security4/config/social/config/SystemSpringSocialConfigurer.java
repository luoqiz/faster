package org.wolf.security4.config.social.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

public class SystemSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	public SystemSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	/**
	 *
	 * @param object
	 *            放到过滤器链上的filter
	 * @param <T>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
		filter.setFilterProcessesUrl(filterProcessesUrl);
		return (T) filter;
	}

}