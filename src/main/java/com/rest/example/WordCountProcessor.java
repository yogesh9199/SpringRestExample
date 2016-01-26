package com.rest.example;

import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
 
import java.util.Collections;
import java.util.Comparator;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class WordCountProcessor {


	public static final String fileUrl = "D:\\Aabha_Data\\TexonomixFolder\\WorkSpace\\CounterApi\\Input.txt";
	/**
	 * Get Counter information for word 
	 * @param wordMap
	 *          word map to search particular string from file. 
	 * @param flagTop20
	 *           flag for top 20 word (true will search top 20 words) 
	 * @return
	 */
	public List<Entry<String, Integer>> getCounterInformation (Map<String,Integer> wordMap,boolean flagTop20){
		
		Map<String, Integer> wordMapCount = getWordCount(
				fileUrl,wordMap,flagTop20);
		List<Entry<String, Integer>> list = new LinkedList<Entry<String,Integer>>();
		if(!flagTop20){
			
			for (Entry<String,Integer> entrySet : wordMapCount.entrySet()) {
				list.add(entrySet);
			}
		}else {
			list = sortByValue(wordMapCount);	
		}
				
		return list;
	}
	/**
	 * Get Word Count in map which contains string and its count.
	 * @param fileName
	 *             Input file name under which string need to be search 
	 * @param wordMap
	 *          Input word map need to be search 
	 * @param flagTop20 
	 *           Search top 20 words from file (word map will be empty if flag = true)
	 * @return Map Contains word and its count.
	 */
	public Map<String, Integer> getWordCount(String fileName,
			Map<String, Integer> wordMap,boolean flagTop20) {

		FileInputStream fis = null;
		DataInputStream dis = null;
		BufferedReader br = null;

		try {
			fis = new FileInputStream(fileName);
			dis = new DataInputStream(fis);
			br = new BufferedReader(new InputStreamReader(dis));
			String line = null;
			while ((line = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(line, " ");
				while (st.hasMoreTokens()) {
					String tmp = st.nextToken().toLowerCase();
					if (wordMap.containsKey(tmp)) {
						wordMap.put(tmp, wordMap.get(tmp) + 1);
					} else {
						if(flagTop20){
							wordMap.put(tmp, 1);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (Exception ex) {
			}
		}
		return wordMap;
	}
	/**
	 * Sort it map based on word count 
	 * @param wordMap
	 * @return List <Entry<String,Integer>> list contains String Integer
	 */
	public List<Entry<String, Integer>> sortByValue(Map<String, Integer> wordMap) {

		Set<Entry<String, Integer>> set = wordMap.entrySet();
		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(
				set);
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1,
					Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		return list;
	}
	
	

}
