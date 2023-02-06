package com.yedam.emp.command;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.vo.EmpVO;

public class EmpRemoveControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		
		String id = req.getParameter("id");
		
		EmpService service = new EmpServiceImpl();
		int r = service.removeEmp(Integer.parseInt(id));
		
		
		
			if(r > 0) {// 정상처리 되면 목록이동.
				try {
					resp.sendRedirect("empList.do");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else {
				try {
					resp.sendRedirect("errorPage.do");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


