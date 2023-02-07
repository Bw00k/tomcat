package com.yedam.emp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.emp.vo.EmpVO;

//EmpServiceImpl : jdbc
//EmpServiceMybatis : mybatis
public class EmpServiceMybatis implements EmpService{

	SqlSessionFactory sessionFactory = 	DataSource.getInstance();
	SqlSession session = sessionFactory.openSession(true);
	
	@Override
	public List<EmpVO> empList() {
		return session.selectList("com.yedam.emp.mapper.EmpMapper.empList");
	}

	@Override
	public int addEmp(EmpVO emp) {
		// A ->B 송금.
		//return session.insert("com.yedam.emp.mapper.EmpMapper.addEmp", emp);
		int r = session.insert("com.yedam.emp.mapper.EmpMapper.addEmp", emp);
		if(r>0) {
			session.commit();
		}else {
			session.rollback();
		}
		return r;
	}

	@Override
	public EmpVO getEmp(int empId) {
		// TODO Auto-generated method stub
		return session.selectOne("com.yedam.emp.mapper.EmpMapper.getEmp",empId);
	}

	@Override
	public int updateEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, String> jobList() {
		// TODO Auto-generated method stub
		return null;
//			resultMap.put(key, (String) map.get(key));
		}


	@Override
	public int modEmp(EmpVO emp) {
		// TODO Auto-generated method stub
		return session.update("com.yedam.emp.mapper.EmpMapper.modEmp",emp);
	}

	@Override
	public int removeEmp(int id) {
		// TODO Auto-generated method stub
		return session.delete("com.yedam.emp.mapper.EmpMapper.removeEmp", id);
	}
	

}
