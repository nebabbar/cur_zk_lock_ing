package com.paytm.coordination.framework;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class FeatureRegistration extends ResourceConfig{
	
	public FeatureRegistration() {
		packages("com.paytm.coordination.framework").register(JacksonFeature.class);
	}

}
