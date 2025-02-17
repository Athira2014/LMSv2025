package com.athira.demo.util;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.athira.demo.common.APIResponse;
import com.athira.demo.common.AccessDeniedException;
import com.athira.demo.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	// secret key
	private String secret = "This_Is_Hilton_Hills";

	// Expiration duration
	private static long expirationDuration = 60 * 60;

	// Generate Token : header.payload.signature
	public String generateJwt(User user) {

		long milliTime = System.currentTimeMillis();
		long expiryTime = milliTime + expirationDuration * 1000;

		// Set issuedTime and ExpiryTime
		Date issuedAt = new Date(milliTime);
		System.out.println(issuedAt);
		Date expiryAt = new Date(expiryTime);
		System.out.println(expiryAt);

		// claims
		Claims claims = Jwts.claims().setIssuer(user.getUserId().toString())
				.setIssuedAt(issuedAt)
				.setExpiration(expiryAt);

		// generate and return jwt using claims
		return Jwts.builder().setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();

	}
	
	// AccessDenied for Claims -- User-Define Exception
	public Claims verify(String authorization) throws Exception {
		// Authorization: eyJhbGciOiJIUzUxMiJ9
		// .eyJpc3MiOiI3IiwiaWF0IjoxNjcxMDE2NzM5LCJleHAiOjE2NzEwMjAzMzksInVzZXJuYW1lIjoiQW51IiwiZW1haWxJZCI6ImFudUBnbWFpbC5jb20ifQ
		// .34wBjIVRzuu4_bTWUgnpac12zUHMJoOSPXfgptNeaarxuBNlf57VhLAqsgLvlxUcbEB4YuN0Hw5lo4bRC2_3VA

		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(authorization).getBody();
			return claims;
		} catch (Exception e) {
			// TODO: handle exception
			throw new AccessDeniedException("Sorry! Access Denied");
		}

	}

	public ResponseEntity<APIResponse> verifyToken(String auth) {
	    APIResponse apiResponse = new APIResponse();
	    try {
	        //verify() verfify and throws an exception on invalid/expired token
	        verify(auth);
	        return null; // Return null when token is valid to continue further processing
	    } catch (Exception e) {
	        // Token verification failed (expired or invalid)
	        apiResponse.setStatus(401); // Unauthorized
	        apiResponse.setData("Invalid or expired token");
	        return ResponseEntity.status(401).body(apiResponse);
	    }
	}

}
