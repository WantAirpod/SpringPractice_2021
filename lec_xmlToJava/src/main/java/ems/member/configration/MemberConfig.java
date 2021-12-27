package ems.member.configration;

import ems.member.DataBaseConnectionInfo;
import ems.member.dao.StudentDao;
import ems.member.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 2021-12-27 cjy9249
 * applicationContext를 대체할 config(java)파일
 */
@Configuration
public class MemberConfig {
	/**
	 <bean id="studentDao" class="ems.member.dao.StudentDao" ></bean>
	 * @return
	 */
	@Bean
	public StudentDao studentDao() {
		return new StudentDao();
	}

	/**
	 * 	<bean id="registerService" class="ems.member.service.StudentRegisterService">
	 * 		<constructor-arg ref="studentDao" ></constructor-arg>
	 * 	</bean>
	 * @return
	 */
	@Bean
	public StudentRegisterService registerService() {
		return new StudentRegisterService(studentDao());
	}
	
	@Bean
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}
	
	@Bean
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}
	
	@Bean
	public StudentDeleteService deleteService() {
		return new StudentDeleteService(studentDao());
	}
	
	@Bean
	public StudentAllSelectService allSelectService() {
		return new StudentAllSelectService(studentDao());
	}

	/**
	 * <bean id="dataBaseConnectionInfoDev" class="ems.member.DataBaseConnectionInfo">
	 * 		<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
	 * 		<property name="userId" value="scott" />
	 * 		<property name="userPw" value="tiger" />
	 * 	</bean>
	 * @return
	 */
	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
		infoDev.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		infoDev.setUserId("scott");
		infoDev.setUserPw("tiger");
		
		return infoDev;
	}
	
	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo infoReal = new DataBaseConnectionInfo();
		infoReal.setJdbcUrl("jdbc:oracle:thin:@192.168.0.1:1521:xe");
		infoReal.setUserId("masterid");
		infoReal.setUserPw("masterpw");
		
		return infoReal;
	}

	/**
	 * <bean id="informationService" class="ems.member.service.EMSInformationService">
	 * 		<property name="info">
	 * 			<value>Education Management System program was developed in 2015.</value>
	 * 		</property>
	 * 		<property name="copyRight">
	 * 			<value>COPYRIGHT(C) 2015 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.</value>
	 * 		</property>
	 * 		<property name="ver">
	 * 			<value>The version is 1.0</value>
	 * 		</property>
	 * 		<property name="sYear">
	 * 			<value>2015</value>
	 * 		</property>
	 * 		<property name="sMonth">
	 * 			<value>1</value>
	 * 		</property>
	 * 		<property name="sDay">
	 * 			<value>1</value>
	 * 		</property>
	 * 		<property name="eYear" value="2015" />
	 * 		<property name="eMonth" value="2" />
	 * 		<property name="eDay" value="28" />
	 * 		<property name="developers">
	 * 			<list>
	 * 				<value>Cheney.</value>
	 * 				<value>Eloy.</value>
	 * 				<value>Jasper.</value>
	 * 				<value>Dillon.</value>
	 * 				<value>Kian.</value>
	 * 			</list>
	 * 		</property>
	 * 		<property name="administrators">
	 * 			<map>
	 * 				<entry>
	 * 					<key>
	 * 						<value>Cheney</value>
	 * 					</key>
	 * 					<value>cheney@springPjt.org</value>
	 * 				</entry>
	 * 				<entry>
	 * 					<key>
	 * 						<value>Jasper</value>
	 * 					</key>
	 * 					<value>jasper@springPjt.org</value>
	 * 				</entry>
	 * 			</map>
	 * 		</property>
	 * 		<property name="dbInfos">
	 * 			<map>
	 * 				<entry>
	 * 					<key>
	 * 						<value>dev</value>
	 * 					</key>
	 * 					<ref bean="dataBaseConnectionInfoDev"/>
	 * 				</entry>
	 * 				<entry>
	 * 					<key>
	 * 						<value>real</value>
	 * 					</key>
	 * 					<ref bean="dataBaseConnectionInfoReal"/>
	 * 				</entry>
	 * 			</map>
	 * 		</property>
	 * 	</bean>
	 * @return
	 */
	@Bean
	public EMSInformationService informationService() {
		EMSInformationService info = new EMSInformationService();
		info.setInfo("Education Management System program was developed in 2015.");
		info.setCopyRight("COPYRIGHT(C) 2015 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		info.setVer("The version is 1.0");
		info.setsYear(2015);
		info.setsMonth(1);
		info.setsDay(1);
		info.seteYear(2015);
		info.seteMonth(2);
		info.seteDay(28);
		/**
		 * List쪽 설정
		 */
		ArrayList<String> developers = new ArrayList<String>();
		developers.add("Cheney.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		info.setDevelopers(developers);
		/**
		 * map 설정
		 */
		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Cheney", "cheney@springPjt.org");
		administrators.put("Jasper", "jasper@springPjt.org");
		info.setAdministrators(administrators);
		/**
		 * map 설정
		 */
		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
		dbInfos.put("dev", dataBaseConnectionInfoDev());
		dbInfos.put("real", dataBaseConnectionInfoReal());
		info.setDbInfos(dbInfos);
		
		return info;
	}
	
}
