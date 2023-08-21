package com.servlets;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.dao.SalesDAO;
import com.connection.DBConnection;

@WebServlet("/SalesChart")
public class SalesChartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SalesChartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Get the current year
        int currentYear = java.time.Year.now().getValue();
        
        // instance of the SalesDAO class
        SalesDAO salesDAO = new SalesDAO(DBConnection.getConnection());
        
        // list to store monthly sales totals
        List<Double> monthlySalesTotals = new ArrayList<Double>();
        
        // Retrieve the monthly sales totals for the current year
        for (int month = 1; month <= 12; month++) {
            double monthlyTotal = salesDAO.getMonthlySalesTotal(currentYear, month);
            monthlySalesTotals.add(monthlyTotal);
        }
        
        // Set the monthly sales totals as a request attribute
        request.setAttribute("monthlySalesTotals", monthlySalesTotals);
        
        // Forward the request to the JSP page
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
