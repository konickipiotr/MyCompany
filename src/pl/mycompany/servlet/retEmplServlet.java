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

@WebServlet("/retEmplServlet")
public class retEmplServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(request.getParameter("id"));
		Worker worker = null;
		EmpDao empdao = new EmpDao();

		try {
			worker = empdao.readWorker(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("worker", worker);
		request.getRequestDispatcher("edit.jsp").forward(request, response);
	}
}
