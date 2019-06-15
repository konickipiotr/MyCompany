package pl.mycompany.servlet;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.management.NotificationFilterSupport;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import jxl.*;
import jxl.read.biff.BiffException;
import pl.mycompany.dao.EmpDao;
import pl.mycompany.model.Worker;

@WebServlet("/LoadServlet")
public class LoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String path = request.getParameter("path");
		String option = request.getParameter("option");
		try {
			File file = new File(path);
			if(!file.exists()) {
				request.setAttribute("message", "nieznaleziono pliku");
				request.getRequestDispatcher("other.jsp").forward(request, response);
				return;
			}
			
			EmpDao empDao = new EmpDao();
			if("update".equals(option)) {
				empDao.resetTable();
			}
			Workbook workbook = Workbook.getWorkbook(file);
			Sheet sheet = workbook.getSheet(0);
			sheet.getSettings().setProtected(false);
			String firstname = null;
			String lastname = null;
			String department = null;
			String position = null;
			Double salary = 0.0;
			int rows = sheet.getRows();
			for(int a=1; a<rows; a++) {
				for(int b=0; b<5; b++) {
					Cell c = sheet.getCell(b,a);

					if(c.getType() == CellType.LABEL && b == 0) {
						LabelCell firstNcell = (LabelCell) c;
						firstname = firstNcell.getString();
					}
					
					if(c.getType() == CellType.LABEL && b == 1) {
						LabelCell lastnamecell = (LabelCell) c;
						lastname = lastnamecell.getString();
					}
					
					if(c.getType() == CellType.LABEL && b == 2) {
						LabelCell departmentcell = (LabelCell) c;
						department = departmentcell.getString();
					}
					
					if(c.getType() == CellType.LABEL && b == 3) {
						LabelCell positioncell = (LabelCell) c;
						position = positioncell.getString();
					}
					if(c.getType() == CellType.NUMBER && b == 4) {
						NumberCell salarycell = (NumberCell) c;
						salary = salarycell.getValue();
					}
					if(c.getType() == CellType.EMPTY) {

					}			
				}
				Worker worker = new Worker(0,firstname,lastname,department, position, salary);
				empDao.create(worker);
			}
			workbook.close();
		
		}catch (Exception e) {
			e.printStackTrace();
		} 
		request.getRequestDispatcher("other.jsp").forward(request, response);

	}

}
