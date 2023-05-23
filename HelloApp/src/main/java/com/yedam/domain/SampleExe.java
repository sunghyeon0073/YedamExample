package com.yedam.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class SampleExe {
	public static void main(String[] args) {
		
		SqlSessionFactory sqlSessionFactory = 
				com.yedam.common.DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession(true)) {
//			Employee emp = session.selectOne("com.yedam.common.NoticeMapper.getEmp", 100);
//			session.delete("com.yedam.common.NoticeMapper.delEmp", 213);
			
			Employee empl = new Employee();
			
			empl.setEmployeeId(300);
			empl.setLastName("Hong");
			empl.setEmail("hong@email");
			empl.setJobId("IT_PROG");
			session.insert("com.yedam.common.NoticeMapper.addEmp",empl);
			List<Employee> emp = session.selectList("com.yedam.common.NoticeMapper.empList");
			System.out.println(emp);
		}
		
	}
}
