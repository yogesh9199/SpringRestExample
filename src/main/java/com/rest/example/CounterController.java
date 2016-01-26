package com.rest.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * Handles requests for word counter
 */
@Controller
public class CounterController {
	
	private static final Logger logger = LoggerFactory.getLogger(CounterController.class);
	
	
	Map<String,Integer> wordMap = new HashMap<String, Integer>();

	/**
	 * Counter word takes input parameter as word map which contains word and its counter (initially zero)
	 * will call word counter processor get word count.
	 * @param requestCounter
	 * @return
	 */
	@RequestMapping(value = RestURIConstants.COUNTER_WORD, method = RequestMethod.POST)
	public @ResponseBody ResponseCounter getCounterWord(@RequestBody ResponseCounter requestCounter ) {
		
		WordCountProcessor wordCountProcessor = new WordCountProcessor();
		
		
		List<Entry<String, Integer>> responseList =	wordCountProcessor.getCounterInformation(requestCounter.getRequestWordMap(),false);
		ResponseCounter responseCounter = new ResponseCounter();
		responseCounter.setReponseList(responseList);
		return responseCounter ;
	}
	
	/**
	 * Get Top 20 words with their count.
	 * 
	 * @return String csv formatted string
	 */
	@RequestMapping(value = RestURIConstants.COUNTER_PATH, method = RequestMethod.GET)
	public @ResponseBody String getTop20() {
		StringBuilder responseString = new StringBuilder();
		
		WordCountProcessor wordCountProcessor = new WordCountProcessor();
		
		Map<String,Integer> requestMap = new LinkedHashMap<String,Integer>();
		List<Entry<String, Integer>> responseList =	wordCountProcessor.getCounterInformation(requestMap,true);
		
		int counter = 0;
		if(counter<= 20){
		for (Map.Entry<String, Integer> entry : responseList) {
			responseString.append(entry.getKey()).append("|").append(entry.getValue()).append("\n");
			counter++;
		}
		}
		
	
	
		return responseString.toString();
	}

				
}
