package br.com.muranodesign.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class JWTFilter implements Filter{
	
	private FilterConfig filterConfig;
	
	@Override
	public void destroy() {	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {	
		
		
		filterChain.doFilter(request, response);
		
//		HttpServletRequest httpRequest = (HttpServletRequest) request;
//		HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//		Enumeration<String> headerNames = httpRequest.getHeaderNames();
//		while (headerNames.hasMoreElements()) {
//			String headerName = headerNames.nextElement();
//		    System.out.println(headerName);
//		    Enumeration<String> headers = httpRequest.getHeaders(headerName);
//		    while (headers.hasMoreElements()) {
//		    	String headerValue = headers.nextElement();
//		    	System.out.println(headerValue);
//		    }
//		}
//		
//		filterChain.doFilter(request, response);
//		
//		if (httpRequest.getRequestURI().startsWith("/plataformaAmorim/Blog/Teste/")){
//			System.out.println("okie");
//			filterChain.doFilter(request, response);
//		}
//		else{
//			String secret = "O8zBTpnHhlcgJuTimlEhNeiq7ZWhitG0";
//			HmacKey key = new HmacKey(secret.getBytes("UTF-8"));
//			
//			JwtConsumer jwtConsumer = new JwtConsumerBuilder()
//            .setRequireExpirationTime() // the JWT must have an expiration time
//            .setAllowedClockSkewInSeconds(30) // allow some leeway in validating time based claims to account for clock skew
//            .setExpectedIssuer("plataformaAmorim") // whom the JWT needs to have been issued by
//            .setVerificationKey(key) // verify the signature with the public key
//            .build(); // create the JwtConsumer instance
//			
//			JsonWebSignature jws = new JsonWebSignature();
//			jws.setPayload(httpRequest.getHeader("porquinho"));
//			jws.setKey(key);
//			jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
//			String jwt;
//			try {
//				jwt = jws.getCompactSerialization();
//				JwtClaims jwtClaims;
//				jwtClaims = jwtConsumer.processToClaims(jwt);
//				System.out.println("JWT validation succeeded! " + jwtClaims);
//				filterChain.doFilter(request, response);
//			} catch (JoseException e1) {
//				e1.printStackTrace();
//				httpResponse.sendError(401, "Unauthorized");
//			} catch (InvalidJwtException e1) {
//				e1.printStackTrace();
//				httpResponse.sendError(401, "Unauthorized");
//			}
//		}
		
	}

	
	public FilterConfig getFilterConfig() {
        return filterConfig;
    }

	@Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }
	
	
	
}
