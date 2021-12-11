package es.wuolahpop.images.controller;

import java.io.IOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.wuolahpop.data.Imagenenbbdd;
import es.wuolahpop.images.manager.*;


/**
 * Servlet implementation class VisualizarImagen
 */
@WebServlet("/visualizarImagen")
public class VisualizarImagen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@PersistenceUnit
	EntityManagerFactory emf;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VisualizarImagen() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ImagenenbbddManager manager = new ImagenenbbddManager(emf);
		Imagenenbbdd imagen =manager.findById(Integer.parseInt(request.getParameter("id")));

		response.setContentType("image/png");
		response.getOutputStream().write(imagen.getImagen());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
