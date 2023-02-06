package com.yedam.emp.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Command;
import com.yedam.emp.service.EmpService;
import com.yedam.emp.service.EmpServiceImpl;
import com.yedam.emp.vo.EmpVO;

public class EmpControl implements Command {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub

		RequestDispatcher rd = null;

		String method = req.getMethod();
		System.out.println(method + "요청방식:");
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (method.equals("GET")) {

			try {
				resp.sendRedirect("result/empList.jsp");
				// resp.sendRedirect("https://www.daum.net");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (method.equals("POST")) {
			// 입력.
			String eid = req.getParameter("eid");
			String lName = req.getParameter("last_name");
			String job = req.getParameter("job");
			String hire = req.getParameter("hire_date");
			String mail = req.getParameter("email");

			EmpVO emp = new EmpVO();
			emp.setEmployeeId(Integer.parseInt(eid));
			emp.setLastName(lName);
			emp.setJobId(job);
			emp.setHireDate(hire);
			emp.setEmail(mail);

			// 서비스 로직.
			EmpService service = new EmpServiceImpl();
			int r = service.addEmp(emp);

			// 요청재지정.
			try {
				if (r > 0) {
					// resp.sendRedirect("WEB-INF/result/addResult.jsp");
					rd = req.getRequestDispatcher("empList.do");
					rd.forward(req, resp); // 페이지 재지정.
				} else {
					// resp.sendRedirect("WEB-INF/result/errorResult.jsp");
					rd = req.getRequestDispatcher("WEB-INF/result/errorResult.jsp");
					rd.forward(req, resp); // 페이지 재지정.
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("알수 요청입니다!!");
			try {
				resp.sendRedirect("https://www.daum.net");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
