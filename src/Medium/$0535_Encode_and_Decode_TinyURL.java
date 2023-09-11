package Medium;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//https://leetcode.com/problems/encode-and-decode-tinyurl/
/*
 * 	This is a system design problem.
 * 
 * 	It's about how are we going to store the long URL in our database so that when the server
 * 	receives the short url request, we are able to retrieve and return the long url to the user
 * 	
 * 	--------------------------------------------------------------
 * 
 * 	The main focus of the problem is about the short URL generation. How can we generate a short
 * 	url that is unique, non-colliding to other long urls, and corresponds to our given long url?
 * 
 * 	One such way is to do it like 'counter-style'. Each url is essentially assigned a unique ID,
 * 	probably incrementing. So the first url gets ID of 0, second gets 1, third gets 2 and so on...
 * 	We store them in our database, and the short URL will consist of that unique ID. When we
 * 	receive back the unique ID, simply decode the unique ID and retrieve from database.
 * 
 * 	There are downsides for this solution.
 * 		>	Easily decodable. People easily notice the pattern and can visit previous websites
 * 			easily. They can also know how many websites you've served so far
 * 		>	People may spam the service to obtain the short url they desired
 * 		>	People may give the server similar long URL multiple times, which duplicates are
 * 			not detected by the server, and mulitple entries are made.
 * 
 * 	-------------------------------------------------------------------
 * 
 * 	To prevent incrementing IDs, we can choose to generate random ID each time and check if it
 * 	is used or not.
 * 	In case you question about time complexity, say the URL we use 7 characters of character space
 * 	size 62 (a-zA-Z0-9), it results in 62^7 total permutations available. Therefore, if we are
 * 	serious, it would take a long time before the chances of running out available IDs.
 * 
 * 	-----------------------------------------------------------------------
 * 
 * 	As keys, we might also just use the hashCode() to encode the Url and store it as key. However,
 * 	do remind that hashcode might just have collision issues, where 2 or more long urls result in
 * 	same hashcode.
 */

public class $0535_Encode_and_Decode_TinyURL {
	
	final static String base = "https://tinyurl.com/";
	final static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	Map<Long, String> map = new HashMap<>();
	
	//	Encoding process - Incrementing ID
	public String encode(String longUrl) {
		String encoded = encodeTo7CharString(map.size() );
		map.put((long)map.size(), longUrl);
		return base + encoded;
		
	}
	
	public String decode(String shortUrl) {
		String[] split = shortUrl.split("/");
		return map.get( decodeCharString( split[split.length - 1] ) );
	}
	
	
	//	Random Generation
	public String encode2(String longUrl) {
		Random rand = new Random();
		long gen = Math.abs(rand.nextInt() );
		while ( map.containsKey(gen) ) gen = Math.abs(rand.nextLong() );
		
		map.put(gen, longUrl);
        
        
		return base + encodeTo7CharString(gen);
	}
	
	public String decode2(String shortUrl) {
		String[] split = shortUrl.split("/");
		return map.get( decodeCharString( split[split.length - 1] ) );
	}
	
	
	//	Optional but recommended: Encoder and Decoder
	//	Encodes an integer to 7 character string
	private String encodeTo7CharString(long code) {
		String res = "";
		for (int i = 0; i < 7; ++i) {
			res += chars.charAt( (int)(code % chars.length() ) );
			code /= chars.length();
        }
		return res;
	}
	//	Decodes 7 character string back to integer
	private long decodeCharString(String str) {
		long res = 0;
		for (int i = 6; i >= 0; --i ) {
            res *= chars.length();
            char c = str.charAt(i);
			if (c >= 'a' && c <= 'z') res += c - 'a';
			else if (c >= 'A' && c <= 'Z') res += c - 'A' + 26;
			else res += c - '0' + 52;
		}
		return res;
	}
	
}
