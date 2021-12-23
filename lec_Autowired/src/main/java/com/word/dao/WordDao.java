package com.word.dao;

import com.word.WordSet;

import java.util.HashMap;
import java.util.Map;

public class WordDao {

	/**
	 * 자료구조가 db 역할을 한다.
	 */
	private Map<String, WordSet> wordDB = 
			new HashMap<String, WordSet>();

	public void insert(WordSet wordSet) {
		wordDB.put(wordSet.getWordKey(), wordSet);
	}

	public WordSet select(String wordKey) {
		return wordDB.get(wordKey);
	}

	public void update(WordSet wordKey) {
		wordDB.put(wordKey.getWordKey(), wordKey);
	}

	public void delete(String sNum) {
		wordDB.remove(sNum);
	}

	public Map<String, WordSet> getWordDB() {
		return wordDB;
	}

}