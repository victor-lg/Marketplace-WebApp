package es.wuolahpop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			
			// Configuración de las solicitudes
			ClientConfig config = new ClientConfig();
			Client client = ClientBuilder.newClient(config);
			
			// URL donde está desplegado el microservicio
			WebTarget webtarget = client.target("http://localhost:10604");
			// Path adicionales o parámetros
			WebTarget webtargetPath = webtarget.path("finance").path("transactions");
			// Tipo de dato que se solicita JSON
			Invocation.Builder invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
			// Llamada por get
			Response responsews = invocationBuilder.get();
			// Obtención del cuerpo de la respuesta
			Transaction[] listTransactions  = responsews.readEntity(Transaction[].class);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
	}

}
