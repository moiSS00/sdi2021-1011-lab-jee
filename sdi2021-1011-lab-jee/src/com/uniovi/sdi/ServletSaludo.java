package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletSaludo
 */
@WebServlet("/ServletSaludo")
public class ServletSaludo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int contador = 0; //Contador auxiliar para contar el numero de hilos  
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletSaludo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Establecemos el tipo de la respuesta (HTML en este caso)
		response.setContentType("text/html"); 
		
		//Obtenemos la salida de la respuesta, para construir HTML 
		PrintWriter out = response.getWriter(); 
		
		//Imprimimos HTML
		out.println("<HTML>");
		out.println("<HEAD><TITLE>Hola Mundo!</TITLE></HEAD>");
		out.println("<BODY>");
		
		//Obtenemos el valor introducido por el usuario en el formulario (que usa GET)
		//y lo imprimimos
		String nombre = (String) request.getParameter("nombre");
		if (nombre != null) {
			out.println("Hola " + nombre + "<br>");
		}
		
		//Mostramos informacion de los hilos en ejecucion 
		try {
			Thread.sleep(15000);
		} catch(InterruptedException e) {}
		
		out.println("ID del hilo:" + Thread.currentThread().getId() + "<br>");
		contador++; 
		out.println("Visitas:" + contador + "<br>");
		
		out.println("</BODY></HTML>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Hace lo mismo que al atender una peticion GET 
		doGet(request, response);
	}

}
