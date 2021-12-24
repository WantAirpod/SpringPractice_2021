package com.word.service;

import com.word.WordSet;
import com.word.dao.WordDao;
import org.springframework.beans.factory.annotation.Autowired;

public class WordRegisterServiceUseAutowired {

	/**
	 * 알아서 Autowired해준다.
	 * @Autowired(required = false) : 있으면 넣어주고 없으면 exception 발생하지 않고 넘어간다.
	 */
	@Autowired(required = false)
	/**
	 * @Qualifier를 쓰면 해당 bean을 정확하게 명시해준다. 사실상 이름이 같으면 상관이 없다. 안써도된다. (좋은 방법이 아니다.)
	 */
//	@Qualifier("usedDao")
	private WordDao wordDao;
	
	public WordRegisterServiceUseAutowired() {
		// TODO Auto-generated constructor stub
	}
	
//	@Autowired
	public WordRegisterServiceUseAutowired(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
	public void register(WordSet wordSet) {
		String wordKey = wordSet.getWordKey();
		if(verify(wordKey)) {
			wordDao.insert(wordSet);
		} else {
			System.out.println("The word has already registered.");
		}
	}
	
	public boolean verify(String wordKey){
		WordSet wordSet = wordDao.select(wordKey);
		return wordSet == null ? true : false;
	}
	
//	@Autowired
	public void setWordDao(WordDao wordDao) {
		this.wordDao = wordDao;
	}
	
}
