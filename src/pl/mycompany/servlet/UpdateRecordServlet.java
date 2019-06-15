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


@WebServlet("/UpdateRecordServlet")
public class UpdateRecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String department = request.getParameter("department");
		String position = request.getParameter("position");
		double salary = Double.parseDouble(request.getParameter("salary"));
		
		Worker worker = new Worker(id, firstName, lastName, department, position, salary);
		EmpDao empDao = new EmpDao();
		
		try {
			empDao.update(worker);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("message", "Worker update");
		request.getRequestDispatcher("display.jsp").forward(request, response);
	}

}
