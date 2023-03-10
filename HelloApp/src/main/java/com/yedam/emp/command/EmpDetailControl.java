package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.service.EmpServiceMybatis;
import com.yedam.emp.vo.EmpVO;

public class EmpDetailControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		//์์ธ์กฐํ : service -> serviceImpl -> dao
		String id = req.getParameter("eid");
		
		EmpService service = new EmpServiceMybatis();//EmpServiceImpl();
		EmpVO emp = service.getEmp(Integer.parseInt(id));
		
		req.setAttribute("searchVO", emp);
		req.setAttribute("myAge", 100);
		req.setAttribute("loginId", "user1");
		
		try {
			req.getRequestDispatcher("WEB-INF/result/empDetail.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
