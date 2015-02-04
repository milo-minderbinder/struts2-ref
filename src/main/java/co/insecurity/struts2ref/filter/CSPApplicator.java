package co.insecurity.struts2ref.filter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;


public class CSPApplicator implements Filter {
	
	private static final Logger logger = 
			Logger.getLogger(CSPApplicator.class.getName());
	
	public static final String CSP_SCRIPT_NONCE = "CSP_SCRIPT_NONCE";

	private List<String> cspHeaders = new ArrayList<String>();
	private String policies = null;
	private SecureRandom prng = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		try {
			this.prng = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			logger.fatal("Unable to initialize CSPApplicator filter - "
					+ "SecureRandom algorithm not found");
			throw new ServletException(e);
		}
		
		// Set supported CSP headers
		this.cspHeaders.add("Content-Security-Policy");
		this.cspHeaders.add("X-Content-Security-Policy");
		this.cspHeaders.add("X-WebKit-CSP");
		
		String policyConfig = filterConfig.getInitParameter("policy-config");
		if (policyConfig != null) {
			Properties directives = new Properties();
			try {
				directives.load(this.getClass().getClassLoader().getResourceAsStream(policyConfig));
				this.policies = directives.toString()
						.replaceAll("(^\\{|\\}$)", "")
						.replaceAll(",", ";")
						.replaceAll("=", " ");
			} catch (FileNotFoundException e) {
				logger.fatal("Unable to initialize CSPApplicator filter - "
						+ "Properties file not found: " + policyConfig);
				throw new ServletException(e);
			} catch (IOException e) {
				logger.fatal("Unable to initialize CSPApplicator filter - "
						+ "IOException during properties file load");
				throw new ServletException(e);
			}
		}
	}
	
	@Override
	public void destroy() {
		// Not used
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		logger.info("In CSPApplicator.doFilter() method");
		HttpServletRequest httpRequest = ((HttpServletRequest) request);
		HttpServletResponse httpResponse = ((HttpServletResponse) response);
		
		// Add script-nonce policy directive
		String randomNum = new Integer(this.prng.nextInt()).toString();
		MessageDigest sha;
		try {
			sha = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			logger.fatal("Failed to apply CSPApplicator filter - "
					+ "MessageDigest algorithm not found");
			throw new ServletException(e);
		}
		byte[] digest = sha.digest(randomNum.getBytes());
		String scriptNonce = Hex.encodeHexString(digest);
		String cspHeaderVal = this.policies + ";script-nonce " + scriptNonce;
		httpRequest.setAttribute(CSP_SCRIPT_NONCE, scriptNonce);
		
		// Add policy directives to HTTP headers
		for (String header : this.cspHeaders)
			httpResponse.setHeader(header, cspHeaderVal);
		
		filterChain.doFilter(request, response);
	}
}
