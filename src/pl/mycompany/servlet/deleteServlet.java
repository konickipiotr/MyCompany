package pl.mycompany.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.mycompany.dao.EmpDao;

@WebServlet("/deleteServlet")
public class deleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		
		EmpDao empDao = new EmpDao();
		try {
			empDao.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("message", "The employee has been removed");
		request.getRequestDispatcher("display.jsp").forward(request, response);
	}
}
