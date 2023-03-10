package com.yedam.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.emp.command.EmpControl;
import com.yedam.emp.command.EmpDetailControl;
import com.yedam.emp.command.EmpForm;
import com.yedam.emp.command.EmpList;
import com.yedam.emp.command.EmpModControl;
import com.yedam.emp.command.EmpModFormControl;
import com.yedam.emp.command.EmpRemoveControl;
import com.yedam.emp.command.LoginControl;
import com.yedam.emp.command.ServiceControl;
import com.yedam.member.command.LoginFormControl;
import com.yedam.member.command.LogoutControl;
import com.yedam.member.command.MemberListControl;
import com.yedam.member.command.SignOnControl;
import com.yedam.member.command.SignOnFormControl;

@WebServlet("*.do")
public class FrontController extends HttpServlet {

	// url패턴과 실행할 프로그램과 매핑.
	Map<String, Command> map;

	public FrontController() {
		map = new HashMap<>();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		//첫페이지 지정
		map.put("/main.do", new MainControl());
		
		map.put("/service.do", new ServiceControl());
		map.put("/login.do", new LoginControl());
		map.put("/errorPage.do", new ErrorPage());
				
		// get : 목록출력(json)요청, post : 입력처리. 
		map.put("/employee.do", new EmpControl());
		
		//xxxForm.do : 페이지 오픈.
		map.put("/empForm.do", new EmpForm());
		map.put("/empList.do", new EmpList()); //목록페이지
		map.put("/empDetail.do", new EmpDetailControl()); //상세페이지.
		map.put("/empModForm.do", new EmpModFormControl()); //수정화면페이지.
		map.put("/empModify.do", new EmpModControl()); //수정페이지
		map.put("/empRemove.do", new EmpRemoveControl()); //삭제
		
		//회원관리메뉴
		map.put("/loginForm.do", new LoginFormControl());
		map.put("/login.do", new LoginControl());
		map.put("/logout.do", new LogoutControl());
		map.put("/signOnForm.do", new SignOnFormControl()); //회원가입화면.
		map.put("/signon.do", new SignOnControl());
		map.put("/memberList.do", new MemberListControl());
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8"); //요청에 한글.
		resp.setCharacterEncoding("utf-8"); //응답 한글.
		resp.setContentType("text/html;charset=utf-8");
		// url패턴을 확인 => 요청페이지 어떤지 ?
		String uri = req.getRequestURI(); // http://localhost:8081/HelloApp/service.do
		String context = req.getContextPath(); // /HelloApp
		String page = uri.substring(context.length());

//		System.out.println("uri: " + uri);
//		System.out.println("context: " + context);
		System.out.println(page);

		Command command = map.get(page);
		command.exec(req, resp);

	}

	@Override
	public void destroy() {

	}
}