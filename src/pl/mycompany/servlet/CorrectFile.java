package pl.mycompany.servlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CorrectFile")
public class CorrectFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String dir = request.getParameter("dir");
		String filename = request.getParameter("filename");
		String path = dir+filename;
		path=path.trim();
		
		File file = new File(path);
		Boolean correctType = correct(filename);
		if(correctType == false) {
			request.setAttribute("message", "Incorrect type.  Import: (*.xls)");
			request.setAttribute("dir", null);
			request.setAttribute("filename", null);
		}else {
			if(file.exists()) {
				request.setAttribute("dir", dir);
				request.setAttribute("filename", filename);
				request.setAttribute("message", null);
			}else {
				request.setAttribute("dir", null);
				request.setAttribute("filename", null);
				request.setAttribute("message", "File doesn't exist");
			}
		}
		request.getRequestDispatcher("other.jsp").forward(request, response);
		
	}
	
	private Boolean correct(String filename) {
		if(filename.substring(filename.length()-4).equals(".xls"))
			return true;
		else
			return false;
	}

}
