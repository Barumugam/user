package com.ison.app.message.response;

import java.sql.Timestamp;
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String roles;
    private String expirySeconds;
    private Timestamp expiryDate;
 
    
    public JwtResponse(String accessToken, String roles, String expirySeconds, Timestamp expiryDate) {
        this.token = accessToken;
        this.roles = roles;
        this.expirySeconds = expirySeconds;
        this.expiryDate = expiryDate;
    }
 
    public String getAccessToken() {
        return token;
    }
 
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }
 
    public String getTokenType() {
        return type;
    }
 
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getExpirySeconds() {
		return expirySeconds;
	}

	public void setExpirySeconds(String expirySeconds) {
		this.expirySeconds = expirySeconds;
	}

	public Timestamp getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Timestamp expiryDate) {
		this.expiryDate = expiryDate;
	}
}