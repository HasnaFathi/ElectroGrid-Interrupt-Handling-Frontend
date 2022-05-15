package com;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemsAPI
 */
@WebServlet("/ItemsAPI")
public class InterruptsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	interrupt interruptObj = new interrupt();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InterruptsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = interruptObj.insertInterrupt(request.getParameter("interruptCode"),
				 request.getParameter("date"),
				request.getParameter("Duration"),
				request.getParameter("Start_time"),
				request.getParameter("End_time"),
				request.getParameter("Region"),
				request.getParameter("Reason"),
				request.getParameter("AdminID"));
				
				response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 {String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}


	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Map paras = getParasMap(request);
		 String output = interruptObj.updateInterrupt(paras.get("hidInterruptIDSave").toString(),
		 paras.get("interruptCode").toString(),
		paras.get("date").toString(),
		paras.get("Duration").toString(),
		paras.get("Start_time").toString(),
		paras.get("End_time").toString(),
		paras.get("Region").toString(),
		paras.get("Reason").toString(),
		paras.get("AdminID").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		 String output = interruptObj.deleteInterrupt(paras.get("itemID").toString());
		response.getWriter().write(output);
	}

}
