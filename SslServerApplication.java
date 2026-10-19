package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.MessageDigest;

@SpringBootApplication
public class SslServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SslServerApplication.class, args);
	}

}

@RestController
class ServerController{
	
	// Function to return the checksum value for the data string that contains my name.    
    @RequestMapping("/hash")
    public String myHash(){
    	String data = "Richard Clark - Artemis Financial";
    	
    	try {
            // Create the MessageDigest object
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            
            // Takes the string, converts it to bytes, and runs it through the SHA-256 hashing algorithm.
            byte[] hashBytes = md.digest(data.getBytes());
            
            // Convert the hash bytes to a readable hex string
            String hexHash = bytesToHex(hashBytes);
            
            // Return both the data and the hex hash
            return "<p>Data: " + data + "</p>"
                 + "<p>SHA-256: Checksum Value: " + hexHash + "</p>";
        
    	} catch (Exception e) {
    		return "Error generating checksum: " + e.getMessage();
    		}
    	}
    	
    	// bytesToHex function
    	private static String bytesToHex(byte[] bytes) {
   
    		// Create a StringBuilder to build the hex string
    	    StringBuilder hex = new StringBuilder();
    	    
    	    // Loop through each byte in the input array
    	    for (byte b : bytes) {
    	    	
    	    	// Convert the byte to a two digit lowercase hex value and append it
    	        hex.append(String.format("%02x", b));
    	    }
    	    
    	    // Return the full hex string
    	    return hex.toString();
    	}
}
