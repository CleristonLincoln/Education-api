package com.education.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@ConfigurationProperties("education")
public class EducationApiProperty {
	
	private String originEnable = "http://localhost:8080";
	
	private final Security security = new Security();
	
	private final S3 s3 = new S3();
	
	
public static class Security {
		
		private boolean enableHttps;
		
		public boolean isEnableHttps() {
			return enableHttps;
		}
		
		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
		
	}

	
	public S3 getS3() {
		return s3;
	}
	
	@Getter@Setter
	public static class S3 {
		
		private String accessKeyId;
		private String secretAccessKey;
		private String bucket = "cl-education-files";

		
	}

	
	
	public String getOriginEnable() {
		return originEnable;
	}
	
	public void setOriginEnable(String originEnable) {
		this.originEnable = originEnable;
	}
	
	public Security getSecurity() {
		return security;
	}
	
	
	
	
}
