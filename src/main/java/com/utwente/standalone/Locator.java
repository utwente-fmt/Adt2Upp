package com.utwente.standalone;

import java.net.URI;
import java.net.URISyntaxException;

public class Locator {
	
	public URI getURI() {
		URI binUri;
		URI uri = null;
		try {
			binUri = Locator.class.
					getResource("").toURI();
		
			
			if (binUri.toString().indexOf("bin") > -1) {
					uri = new URI(binUri.toString().replaceAll("bin", "src"));
				
			} else {
				uri = binUri;
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return uri;
	}
}
