package com.counterrest.example;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.rest.example.ResponseCounter;
import com.rest.example.RestURIConstants;
/**
 * Note :- Basic authentication is not implemented.
 *         Test word count is using request object 
 * @author Yogesh Patil 
 *
 */
public class TestSpringRestExample {

	public static final String SERVER_URI = "http://localhost:8443/SpringRestExample";
	
	public static void main(String args[]){
		
		testWordCount();
		System.out.println("*****");
		testTop20();
	}

	/**
	 * get word with count.
	 */
	private static void testWordCount() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseCounter requestCounter
		 = new ResponseCounter();
		 int zero = 0;
		Map<String, Integer> wordMap = new LinkedHashMap<String, Integer>();
		wordMap.put("Pune", zero);
//		wordMap.put("eget", zero);
//		wordMap.put("vel", zero);
//
//		wordMap.put("in", zero);
//		wordMap.put("ut", zero);
//		wordMap.put("eget", zero);
		requestCounter.setRequestWordMap(wordMap);
		
		ResponseCounter responseCounter = restTemplate.postForObject(SERVER_URI+RestURIConstants.COUNTER_WORD,requestCounter, ResponseCounter.class);
		
		
		for (Map.Entry<String, Integer> entry : responseCounter.getReponseList()) {
			System.out.println(entry.getKey() + " ==== " + entry.getValue());
		}		
	}
	
	/*
	 * Top 20 words with count
	 */
	private static void testTop20(){
		RestTemplate restTemplate = new RestTemplate();
		String response = restTemplate.getForObject(SERVER_URI+ RestURIConstants.COUNTER_PATH, String.class);
	}

	}
