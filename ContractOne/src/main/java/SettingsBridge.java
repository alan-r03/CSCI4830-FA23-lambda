

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datamodel.Contractor;
import datamodel.Customer;
import util.UtilDB;
import util.Info;

/**
 * Servlet implementation class SettingsBridge
 */
@WebServlet("/SettingsBridge")
public class SettingsBridge extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SettingsBridge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		Customer customer = UtilDB.getCustomer(email);
		Contractor contractor = UtilDB.getContractor(email);
		String forward = "Login.jsp";
		if(customer != null)
		{
			forward = "Customer-Home-Settings.jsp";
		}
		else if(contractor != null)
		{
			forward = "Contractor-Home-Settings.jsp";
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(forward);
		rd.forward(request, response);
		response.sendRedirect(forward);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
