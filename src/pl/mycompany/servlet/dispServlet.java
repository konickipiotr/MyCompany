package pl.mycompany.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.mycompany.dao.EmpDao;
import pl.mycompany.model.Worker;

@WebServlet("/dispServlet")
public class dispServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("disp");
		String checkbox[] = request.getParameterValues("disp");
		String searchby = request.getParameter("search");
		String searchValue = request.getParameter("searchValue");
		EmpDao empDao = new EmpDao();
			try {
				List<Worker> workerList = null;
				if("all".equals(searchby)){
					workerList = empDao.dispAll();
				}else {
					workerList = empDao.read(searchby, searchValue);
				}
				request.setAttribute("worekList", workerList);			
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			Boolean depart[] = new Boolean[] {false,false,false,false,false,false};
			
			if(checkbox != null) {
				for(String s: checkbox) {
					if("all".equals(s)) {
						for(int i=0; i<depart.length; i++) {
							depart[i] = true;
						}
						break;
					}else {
						if("fname".equals(s))
							depart[1] = true;
						if("lname".equals(s))
							depart[2] = true;
						if("dep".equals(s))
							depart[3] = true;
						if("pos".equals(s))
							depart[4] = true;
						if("salary".equals(s))
							depart[5] = true;
					}
				}
				
				
			}
			request.setAttribute("checkDep", depart);		
			request.getRequestDispatcher("display.jsp").forward(request, response);
		
		
	}

}
