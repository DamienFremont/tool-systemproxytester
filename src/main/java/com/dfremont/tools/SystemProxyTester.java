package com.dfremont.tools;

import static java.lang.String.format;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;

/**
 * Know the proxy you are behind and have no idea (because of secured network, no admin, etc).
 * 
 * Usage :
 *   launch main()
 * 
 * Output :
 *   proxy type : HTTP
 *   proxy hostname : proxy.toto.net
 *   proxy port : 8080
 */
public class SystemProxyTester {

	/**
	 * Launch this and read the console output.
	 */
	public static void main(String[] args) {
		try {
			System.setProperty("java.net.useSystemProxies", "true");
			String testURI = "http://www.google.com/";
			List<Proxy> l = ProxySelector.getDefault().select(new URI(testURI));
			for (Proxy proxy : l) {
				System.out.println(format("proxy type : %s", proxy.type()));
				InetSocketAddress addr = (InetSocketAddress) proxy.address();
				if (addr == null) {
					System.out.println("No Proxy address");
				} else {
					System.out.println(format("proxy hostname : %s",
							addr.getHostName()));
					System.out
							.println(format("proxy port : %d", addr.getPort()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}