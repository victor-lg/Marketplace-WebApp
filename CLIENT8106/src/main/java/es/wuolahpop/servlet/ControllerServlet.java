package es.wuolahpop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

import es.wuolahpop.data.*;
import es.wuolahpop.images.manager.ImagenenbbddManager;



/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       
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

            WebTarget webtarget;
            WebTarget webtargetPath;
            Invocation.Builder invocationBuilder;
            Response responsews;
            User userResponse;
            
            RequestDispatcher reqDis;
            
            
            
			String typeOfOperation=request.getParameter("typeOfQuery");
			
			HttpSession session = null;
			  
			  switch(typeOfOperation){
			  
			  case "searchUser":
				  	webtarget = client.target("http://localhost:10602");
				  	String email=request.getParameter("email");
				  	String password=request.getParameter("pswd");
				  
		            // Path adicionales o parámetros
				  	webtargetPath = webtarget.path("client").path("user").queryParam("mail", email);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            
		            // Llamada por get
		            responsews = invocationBuilder.get();
		            if(responsews.getStatus()==200) {
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
			            	
			            	//para mostrar los productos de la tienda despues de iniciar sesion
				            webtarget = client.target("http://localhost:10603");
				    		webtargetPath = webtarget.path("catalogue").path("requestAll");
				            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
				            
				            responsews = invocationBuilder.get();
				            
				            Item [] itemsList = responsews.readEntity(Item[].class);
				            session = request.getSession(true);

				            session.setAttribute("itemsList", itemsList);

				            RequestDispatcher r1 = request.getRequestDispatcher("/store.jsp");
				    		r1.forward(request,response);
				    		
			            } else {
			            	
			            	reqDis = request.getRequestDispatcher("/login.html");
							reqDis.forward(request, response);
			            }
		            }
		            
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
		            // Obtención del cuerpo de la respuesta
		            
		            
		            
		            
					
			  		
			  		break;
			  		
			  	case "newUser":
			  		
			  		webtarget = client.target("http://localhost:10602");
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
		            if(responsews.getStatus()==200) {
		            	 // Obtención del cuerpo de la respuesta
			            userResponse  = responsews.readEntity(User.class);

			            System.out.println(userResponse);
			            
			            session = request.getSession(true);
						session.setAttribute("user", userResponse);
						
						session.setAttribute("email", userResponse.getMail());
						session.setAttribute("name", userResponse.getName());
						session.setAttribute("surname1", userResponse.getSurname1());
						session.setAttribute("surname2", userResponse.getSurname2());
						session.setAttribute("city", userResponse.getCity());
						session.setAttribute("password", userResponse.getPassword());
			            
						RequestDispatcher r2 = request.getRequestDispatcher("/store.jsp");
						r2.forward(request, response);
		            }
		            
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
		           
			  		
			  		break;
			  		
			  	case "modifyUser":
			  			webtarget = client.target("http://localhost:10602");
			  			webtargetPath = webtarget.path("client").path("modifyUser");
			            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
			            User modified_user = new User(request.getParameter("email"), 
			            		request.getParameter("city"), 
			            		request.getParameter("name"), 
			            		request.getParameter("pswd"), 
			            		request.getParameter("surname1"), 
			            		request.getParameter("surname2"));
			            
			            // Llamada por post
			            responsews = invocationBuilder.put(Entity.entity(modified_user,MediaType.APPLICATION_JSON));
			            if(responsews.getStatus()==200) {
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
				            
							RequestDispatcher r3 = request.getRequestDispatcher("/store.jsp");
							r3.forward(request, response);
			            }
			            
			            else {
			            	request.setAttribute("errorCode", responsews.getStatus());
			            	reqDis = request.getRequestDispatcher("/errPage.jsp");
							reqDis.forward(request, response);
			            }
			            
			            
			  		
			  		break;
			  		
			  	case "dropOutUser":
			  		webtarget = client.target("http://localhost:10602");
			  		
			  		String email_to_drop = request.getParameter("email");
		  			webtargetPath = webtarget.path("client").path("deleteUser").queryParam("mail", email_to_drop);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.delete();
		            
		            if(responsews.getStatus()==200) {
		            	userResponse  = responsews.readEntity(User.class);
		            	
		            	
		            	webtarget = client.target("http://localhost:10603");

			  			webtargetPath = webtarget.path("catalogue").path("requestByVendor").queryParam("vendor", email_to_drop);
			            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
			            
			            responsews = invocationBuilder.get();
			            
			            if(responsews.getStatus()==200) {
			            	 Item [] itemsList = responsews.readEntity(Item[].class);

			            	 for (Item myItem : itemsList) {
			            		webtarget = client.target("http://localhost:10603");
						  		webtargetPath = webtarget.path("catalogue").path("delete").queryParam("itemId", myItem.getItemId());
						        invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
						        responsews = invocationBuilder.delete();
			            	 }
			            	 
				             RequestDispatcher r4 =  request.getRequestDispatcher("/login.html");
							 r4.forward(request, response);
			            }
			            
			            else {
			            	request.setAttribute("errorCode", responsews.getStatus());
			            	reqDis = request.getRequestDispatcher("/errPage.jsp");
							reqDis.forward(request, response);
			            }
			            

		            }
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
		  		
		  		break;
			  		
			  	case "newBankTransaction":
			  		webtarget = client.target("http://localhost:10601");
		  			webtargetPath = webtarget.path("bank").path("transactions");
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            DateFormat objSDF = new SimpleDateFormat("yyyyMMdd");
					Date _fechaActual = new Date();
					
					String fecha_caducidad = request.getParameter("caducity");
					String fecha_final = fecha_caducidad.replace("-", "");
					
					String precio = request.getParameter("price");
					float precio_1 = Float.parseFloat(precio);
					
		            TransactionFromClient transaction = new TransactionFromClient(
		            		request.getParameter("buyer"), 
		            		request.getParameter("seller"), 
		            		precio_1, 
		            		fecha_final,
		            		objSDF.format(_fechaActual).toString(), 
		        			Integer.parseInt(request.getParameter("cv2")), 
		        			Long.parseLong(request.getParameter("card")), 
		        			"1");
		            
		            // Llamada por post
		            responsews = invocationBuilder.post(Entity.entity(transaction,MediaType.APPLICATION_JSON));
		           
		            
				if (responsews.getStatus()==200) {
					// Obtención del cuerpo de la respuesta
					Transaction transactionResponse = responsews.readEntity(Transaction.class);
					
					// Actualizo el estado del producto tras haber sido vendido
		            String item_sold = request.getParameter("itemIdSold");
					webtarget = client.target("http://localhost:10603");
		  			webtargetPath = webtarget.path("catalogue").path("requestById").queryParam("itemId", item_sold);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            
		            if (responsews.getStatus()==200) {
		            	Item product_sold = responsews.readEntity(Item.class);
			            
			            webtarget = client.target("http://localhost:10603");
			  			webtargetPath = webtarget.path("catalogue").path("changeState");
			            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
			            
			            product_sold.setState("Vendido");
			            
			            responsews = invocationBuilder.post(Entity.entity(product_sold,MediaType.APPLICATION_JSON));
			            
			            if (responsews.getStatus()==200) {
				            Item product_changed = responsews.readEntity(Item.class);
				            
							RequestDispatcher r5 = request.getRequestDispatcher("/store.jsp");
							r5.forward(request, response);
							
			            } else {
			            	request.setAttribute("errorCode", responsews.getStatus());
			            	reqDis = request.getRequestDispatcher("/errPage.jsp");
							reqDis.forward(request, response);
			            }
			            
		            } else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
				}
				else {
					reqDis = request.getRequestDispatcher("/errPage.html");
					reqDis.forward(request, response);
				}
				break;
		  		
			  	case "logOut":
					try {
						session = request.getSession(false);
						
						RequestDispatcher r6 = request.getRequestDispatcher("/login.html");
						r6.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					break;
				
		  		
			  		
			  }			 

			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		
		
	}

}
