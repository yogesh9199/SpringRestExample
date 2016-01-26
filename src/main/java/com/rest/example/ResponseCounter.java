package com.rest.example;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ResponseCounter {
	
	private Map<String,Integer> requestWordMap ;
	
	private List<Entry<String, Integer>> reponseList;

	public Map<String, Integer> getRequestWordMap() {
		return requestWordMap;
	}

	public void setRequestWordMap(Map<String, Integer> requestWordMap) {
		this.requestWordMap = requestWordMap;
	}

	public List<Entry<String, Integer>> getReponseList() {
		return reponseList;
	}

	public void setReponseList(List<Entry<String, Integer>> reponseList) {
		this.reponseList = reponseList;
	}
	

		

}
