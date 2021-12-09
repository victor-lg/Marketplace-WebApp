package es.wuolahpop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
			
			// Configuración de las solicitudes
			ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);

            WebTarget webtarget = client.target("http://localhost:10602");
            WebTarget webtargetPath;
            Invocation.Builder invocationBuilder;
            Response responsews;
            User userResponse;
            
            RequestDispatcher reqDis;
            
			String typeOfOperation=request.getParameter("typeOfQuery");
			
			HttpSession session = null;
			  
			  switch(typeOfOperation){
			  
			  case "searchUser":
				  	String email=request.getParameter("email");
				  	String password=request.getParameter("pswd");
				  
		            // Path adicionales o parámetros
				  	webtargetPath = webtarget.path("client").path("userByMail").queryParam("mail", email);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            
		            // Llamada por get
		            responsews = invocationBuilder.get();
		            
		            // Obtención del cuerpo de la respuesta
		            userResponse  = responsews.readEntity(User.class);
		            
		            session = request.getSession(true);
					session.setAttribute("user", userResponse);
					
					session.setAttribute("email", userResponse.getMail());
					session.setAttribute("name", userResponse.getName());
					session.setAttribute("surname1", userResponse.getSurname1());
					session.setAttribute("surname2", userResponse.getSurname2());
					session.setAttribute("city", userResponse.getCity());
					session.setAttribute("password", userResponse.getPassword());
					

		            if (userResponse.getPassword().equals(password)) {
		            	reqDis = request.getRequestDispatcher("/store.jsp");
						reqDis.forward(request, response);
		            } else {
		            	reqDis = request.getRequestDispatcher("/login.html");
						reqDis.forward(request, response);
		            }
		            
					
			  		
			  		break;
			  		
			  	case "newUser":
			  		
		            // Path adicionales o parámetros
			  		webtargetPath = webtarget.path("client").path("newUser");
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            User new_user = new User(request.getParameter("email"), 
		            		request.getParameter("city"), 
		            		request.getParameter("name"), 
		            		request.getParameter("pswd"), 
		            		request.getParameter("surname1"), 
		            		request.getParameter("surname2"));
		            
		            // Llamada por post
		            responsews = invocationBuilder.post(Entity.entity(new_user,MediaType.APPLICATION_JSON));
		            
		            // Obtención del cuerpo de la respuesta
		            userResponse  = responsews.readEntity(User.class);

		            System.out.println(userResponse);
		            
					reqDis = request.getRequestDispatcher("/store.jsp");
					reqDis.forward(request, response);
			  		
			  		break;
			  		
			  	case "modifyUser":
			  			webtargetPath = webtarget.path("client").path("modifyUser");
			            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
			            User modified_user = new User(request.getParameter("email"), 
			            		request.getParameter("city"), 
			            		request.getParameter("name"), 
			            		request.getParameter("pswd"), 
			            		request.getParameter("surname1"), 
			            		request.getParameter("surname2"));
			            
			            // Llamada por post
			            responsews = invocationBuilder.post(Entity.entity(modified_user,MediaType.APPLICATION_JSON));
			            
			            // Obtención del cuerpo de la respuesta
			            userResponse  = responsews.readEntity(User.class);
			            
			            session = request.getSession(true);
						session.setAttribute("user", userResponse);
			            
			            session.setAttribute("email", userResponse.getMail());
						session.setAttribute("name", userResponse.getName());
						session.setAttribute("surname1", userResponse.getSurname1());
						session.setAttribute("surname2", userResponse.getSurname2());
						session.setAttribute("city", userResponse.getCity());
						session.setAttribute("password", userResponse.getPassword());
			            
						reqDis = request.getRequestDispatcher("/store.jsp");
						reqDis.forward(request, response);
			  		
			  		break;
			  		
			  	case "dropOutUser":
		  			webtargetPath = webtarget.path("client").path("deleteUser").queryParam("mail", request.getParameter("email"));
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.delete();
		            
		            // Obtención del cuerpo de la respuesta
		            userResponse  = responsews.readEntity(User.class);
		            
					reqDis = request.getRequestDispatcher("/login.html");
					reqDis.forward(request, response);
		  		
		  		break;
			  		
			  		
			  		
			  }			 
			 
			 
			
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
	}

}
