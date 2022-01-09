package com.bs.lec21.member.dao;

import com.bs.lec21.member.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Map;

@Repository
public class MemberDao implements IMemberDao{
	/**
	 * JDBC 연동 설정
	 */
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String userid = "scott";
	private String userpw = "tiger";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;


	//private HashMap<String, Member> dbMap;
	
	public MemberDao() {
		//dbMap = new HashMap<String, Member>();
	}
/*
	@Override
	public Map<String, Member> memberInsert(Member member) {

		dbMap.put(member.getMemId(), member);
		return dbMap;

	}
*/

	/**
	 * JDBC 연동으로 수정
	 * @param member
	 * @return
	 */
	@Override
	public int memberInsert(Member member) {
		int result = 0 ;
		try {
			Class.forName(driver);
			//계정아이디, 패스워드 이용해서 데이터베이스 접속
			conn = DriverManager.getConnection(url,userid,userpw);
			//query
			String sql = "INSERT INTO member (memId, memPw, memMail values(?,?,?)" ;

			pstmt.setString(1, member.getMemId());
			pstmt.setString(2,member.getMemPw());
			pstmt.setString(3,member.getMemMail());
			result = pstmt.executeUpdate();

		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e){
			e.printStackTrace();
		}finally {
			try {
				if (pstmt != null) pstmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;

	}


	@Override
	public Member memberSelect(Member member) {
		
		//Member mem = dbMap.get(member.getMemId());
		return null;
		
	}

	@Override
	public Member memberUpdate(Member member) {
		
		//dbMap.put(member.getMemId(), member);
		//return dbMap.get(member.getMemId());
		return null;
		
	}

	@Override
	public Map<String, Member> memberDelete(Member member) {
		
		//dbMap.remove(member.getMemId());
		return null;
		
	}

}
