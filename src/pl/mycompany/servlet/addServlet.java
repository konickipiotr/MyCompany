package pl.mycompany.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.mycompany.dao.EmpDao;
import pl.mycompany.model.Worker;

@WebServlet("/addServlet")
public class addServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		double salary = Double.parseDouble(request.getParameter("salary"));
		System.out.println(firstName);
		if(firstName == "" || lastName == "" || department == ""  ||position == "") {
			request.setAttribute("message", "incorrect");
			request.getRequestDispatcher("addEmpl.jsp").forward(request, response);
			return;
		}
		try {
			EmpDao empDao = new EmpDao();
			Worker worker = new Worker(0,firstName,lastName,department, position, salary);
			empDao.create(worker);
			request.setAttribute("message", "correct");
			request.getRequestDispatcher("addEmpl.jsp").forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("message", "incorrect");
			request.getRequestDispatcher("addEmpl.jsp").forward(request, response);
		}
	}

}
