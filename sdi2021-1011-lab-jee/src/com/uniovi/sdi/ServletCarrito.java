package com.uniovi.sdi;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletCarrito
 */
@WebServlet("/incluirEnCarrito") //Asociamos una URL al servlet
public class ServletCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//Obtenemos la sesion 
		HttpSession session = request.getSession(); 
		
		//Recogemos el carrito de la sesion (que es similar a una tabla hash que funciona 
		//como almacen para compartir informacion)
		ConcurrentHashMap<String, Integer> carrito = 
				(ConcurrentHashMap<String, Integer>) request.getSession().getAttribute("carrito"); 
		
		//No hay carrito, creamos uno 
		if (carrito == null) {
			carrito = new ConcurrentHashMap<String, Integer>(); 
			request.getSession().setAttribute("carrito", carrito);
		}
		
		//Ahora obenemos el producto deseado (se debe de haber seleccionado un producto entre 
		//los disponibles)
		String producto = request.getParameter("producto"); 
		if (producto != null) {
			insertarEnCarrito(carrito, producto);
		}
		
		//Preparamos la respuesta y mostramos el carrito en HTML 
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter(); 
		out.println("<HTML>"); 
		out.println("<HEAD><TITLE>Tienda SDI: carrito</TITLE></HEAD>"); 
		out.println("<BODY>"); 
		out.println(carritoEnHTML(carrito) + "<br>"); 
		out.println("<a href=\"tienda.html\">Volver</a></BODY></HTML>"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	//Añade un producto al carrito 
	private void insertarEnCarrito(Map<String, Integer> carrito, String claveProducto) {
		if (carrito.get(claveProducto) == null) {
			carrito.put(claveProducto, Integer.valueOf(1)); 
		}
		else {
			int numeroArticulos = (Integer) carrito.get(claveProducto).intValue(); 
			carrito.put(claveProducto, Integer.valueOf(numeroArticulos + 1)); 
		}
	}
	
	//Muestra el contenido del carrito en formato HTML 
	private String carritoEnHTML(Map<String, Integer> carrito) {
		String carritoEnHTML = ""; 
		
		for(String key : carrito.keySet()) 
			carritoEnHTML += "<p>[" + key + "], " + carrito.get(key) + " unidades</p>";
		return carritoEnHTML; 
	}

}
