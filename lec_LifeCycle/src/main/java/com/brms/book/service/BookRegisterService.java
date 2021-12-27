package com.brms.book.service;

import com.brms.book.Book;
import com.brms.book.dao.BookDao;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 책을 등록하는 서비스
 */
public class BookRegisterService implements InitializingBean, DisposableBean {

	@Autowired
	private BookDao bookDao;
	
	public BookRegisterService() { }
	
	public void register(Book book) {
		bookDao.insert(book);
	}

	/**
	 * 1. 메소드 방식 xml에 등록하고 구현하기
	 */
	public void initMethod() {
		System.out.println("BookRegisterService 빈(Bean)객체 생성 단계");
	}
	
	public void destroyMethod() {
		System.out.println("BookRegisterService 빈(Bean)객체 소멸 단계");
	}

	/**
	 * implement에서 오버라이드 받아서 사용하는 방법
	 * @throws Exception
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("bean 객체 생성 cjy9249");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("bean 객체 소멸 cjy9249");
	}
}