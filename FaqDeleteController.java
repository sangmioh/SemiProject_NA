package com.na.admin.info.controller;

import java.io.IOException;
import static com.na.template.JDBCTemplate.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.na.admin.info.model.service.FaqService;

/**
 * Servlet implementation class FaqDeleteController
 */
@WebServlet("/delete.fa")
public class FaqDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FaqDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		//2) 요청 시 전달값 뽑아서 변수에 기록 및 VO객체로 가공
		int faqNo = Integer.parseInt(request.getParameter("fno"));
		
		//3) NoticeService 로 전달값 넘기면서 결과값 받기
		int result = new FaqService().deleteFaq(faqNo);
		
		//4) 결과에 따른 응답화면 지정
		if(result>0) { //성공 
			// 공지사항 리스트 페이지로 재요청
			//  /jsp/list.no
			request.getSession().setAttribute("alertMsg", "성공적으로 FAQ가 삭제되었습니다.");
			
			response.sendRedirect(request.getContextPath() + "/faqlist.fa");			
		}
		else { //실패=> 에러페이지 포워딩
			
			request.setAttribute("errorMsg", "FAQ 삭제 실패");
			
			request.getRequestDispatcher("views/user/common/errorPage.jsp").forward(request, response);
			
		}	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
