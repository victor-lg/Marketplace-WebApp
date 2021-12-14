package es.wuolahpop.images.controller;

import java.io.IOException;
import java.io.InputStream;

import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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

import es.wuolahpop.data.Item;


/**
 * Servlet implementation class MainController
 */
@WebServlet("/controlador")
@MultipartConfig
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
       

	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);

        WebTarget webtarget;
        WebTarget webtargetPath;
        Invocation.Builder invocationBuilder;
        Response responsews;
        Item itemResponse;
        RequestDispatcher reqDis;

        session = null;
		
		String accion = request.getParameter("typeOfQuery");
		
		/*if(accion==null){
			accion = "getAllProducts";
		}*/
		
		
		
		switch(accion)
		{
			case "getAllProducts":
				try {
					webtarget = client.target("http://localhost:10603");
		  			webtargetPath = webtarget.path("catalogue").path("requestAll");
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            
		            if(responsews.getStatus()==200) {
		            	Item [] itemsList = responsews.readEntity(Item[].class);
			            session = request.getSession(true);

			            session.setAttribute("itemsList", itemsList);

			            RequestDispatcher r1 = request.getRequestDispatcher("/store.jsp");
						r1.forward(request,response);
		            }
		            
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
		            
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
				
			case "showUserProducts":
				try {
					
					String userEmail = request.getParameter("userEmail");
					webtarget = client.target("http://localhost:10603");
		  			webtargetPath = webtarget.path("catalogue").path("requestByVendor").queryParam("vendor", userEmail);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            if(responsews.getStatus()==200) {
		            	Item [] userItemsList = responsews.readEntity(Item[].class);
			            //session = request.getSession(true);

			            request.setAttribute("userItemsList", userItemsList);

			            RequestDispatcher r2 = request.getRequestDispatcher("/account.jsp");
						r2.forward(request,response);
		            }
		            
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
				
			case "searchByCategory":
				try {
					webtarget = client.target("http://localhost:10603");
					String categorySelected = request.getParameter("product-category-selection");
		  			webtargetPath = webtarget.path("catalogue").path("requestByCategory").queryParam("category", categorySelected);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            if(responsews.getStatus()==200) {
		            	Item [] itemsList = responsews.readEntity(Item[].class);
			            session = request.getSession(true);

			            session.setAttribute("itemsList", itemsList);

			            RequestDispatcher r1 = request.getRequestDispatcher("/store.jsp");
						r1.forward(request,response);
		            }
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
				
			case "searchByVendor":
				try {
					webtarget = client.target("http://localhost:10603");
					String vendorSelected = request.getParameter("vendorSelected");
		  			webtargetPath = webtarget.path("catalogue").path("requestByVendor").queryParam("vendor", vendorSelected);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            if(responsews.getStatus()==200) {
		            	 Item [] itemsList = responsews.readEntity(Item[].class);
				         session = request.getSession(true);
				         session.setAttribute("itemsList", itemsList);
				         RequestDispatcher r1 = request.getRequestDispatcher("/store.jsp");
				         r1.forward(request,response);
		            }
		            
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		           
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
				
			case "searchByTitle":
				try {
					webtarget = client.target("http://localhost:10603");
					String titleSelected = request.getParameter("titleSelected");
		  			webtargetPath = webtarget.path("catalogue").path("requestByTitle").queryParam("title", titleSelected);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            if(responsews.getStatus()==200) {
		            	Item [] itemsList = responsews.readEntity(Item[].class);
			            session = request.getSession(true);
			            session.setAttribute("itemsList", itemsList);
			            RequestDispatcher r1 = request.getRequestDispatcher("/store.jsp");
						r1.forward(request,response);
		            }
		            
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
		            
				} catch (Exception e) {
					System.out.println(e);
				}
				
				break;
			
			case "newProduct":
				webtarget = client.target("http://localhost:10603");
	  			webtargetPath = webtarget.path("catalogue").path("insertion");
	            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);

				Part filePart = request.getPart("image-file");
				
				InputStream fileContent = filePart.getInputStream();
			    byte[] imageBytes = new byte[(int) filePart.getSize()];
			    fileContent.read(imageBytes, 0, imageBytes.length);
			    fileContent.close();
			    String image = Base64.getEncoder().encodeToString(imageBytes);
			    
			    Item new_item = new  Item(
						request.getParameter("product-category-selection"),
						request.getParameter("product-description"),
						image, 
						Float.parseFloat(request.getParameter("price")), 
						request.getParameter("product-name"),  
						request.getParameter("email"));
			    
			    System.out.println(new_item);
			    
			 // Llamada por post
	            responsews = invocationBuilder.post(Entity.entity(new_item,MediaType.APPLICATION_JSON));
	            if(responsews.getStatus()==200) {
	            	itemResponse  = responsews.readEntity(Item.class);
		            System.out.println(itemResponse);				    
					RequestDispatcher r3 = request.getRequestDispatcher("/store.jsp");
					r3.forward(request,response);
	            }
	            
	            else {
	            	request.setAttribute("errorCode", responsews.getStatus());
	            	reqDis = request.getRequestDispatcher("/errPage.jsp");
					reqDis.forward(request, response);
	            }
	            
	            
				
				break;
			
				
			case "buyProduct":
				String itemId = request.getParameter("productID");     
				try {
					webtarget = client.target("http://localhost:10603");
		  			webtargetPath = webtarget.path("catalogue").path("requestById").queryParam("itemId", itemId);
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            if(responsews.getStatus()==200) {
		            	Item product = responsews.readEntity(Item.class);

			            request.setAttribute("productToBuy", product);

			            RequestDispatcher r4 = request.getRequestDispatcher("/checkout.jsp");
						r4.forward(request,response);
		            }
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
		            
		            
				} catch (Exception e) {
					System.out.println(e);
				}
				
				
				break;
				
			case "selectProductToModify":
				String itemToModifyID = request.getParameter("itemToModifyID"); 
				
				webtarget = client.target("http://localhost:10603");
	  			webtargetPath = webtarget.path("catalogue").path("requestById").queryParam("itemId", itemToModifyID);
	            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
	            
	            responsews = invocationBuilder.get();
	            if(responsews.getStatus()==200) {
	            	Item product = responsews.readEntity(Item.class);

		            request.setAttribute("itemToModify", product);
		            
					RequestDispatcher r5 = request.getRequestDispatcher("/modify-product.jsp");
					r5.forward(request,response);
	            }
	            else {
	            	request.setAttribute("errorCode", responsews.getStatus());
	            	reqDis = request.getRequestDispatcher("/errPage.jsp");
					reqDis.forward(request, response);
	            }
	            
	            
				
				break;
				
			case "updateProduct":
				//elimino el producto con ese ID
				int itemID = Integer.parseInt (request.getParameter("itemId")); 
				
				webtarget = client.target("http://localhost:10603");
	  			webtargetPath = webtarget.path("catalogue").path("delete").queryParam("itemId", itemID);
	            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
	            
	            responsews = invocationBuilder.delete();
	            if(responsews.getStatus()==200) {
	            	itemResponse  = responsews.readEntity(Item.class);
		            
		            
		            //creo un producto nuevo
		            webtarget = client.target("http://localhost:10603");
		  			webtargetPath = webtarget.path("catalogue").path("insertion");
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);


					Part filePart_updated = request.getPart("image-file");
					
					InputStream fileContent_updated  = filePart_updated .getInputStream();
				    byte[] imageBytes_updated  = new byte[(int) filePart_updated .getSize()];
				    fileContent_updated.read(imageBytes_updated , 0, imageBytes_updated .length);
				    fileContent_updated.close();
				    String image_updated  = Base64.getEncoder().encodeToString(imageBytes_updated );
				    
				    Item item_updated = new  Item(
							request.getParameter("product-category-selection"),
							request.getParameter("product-description"),
							image_updated, 
							Float.parseFloat(request.getParameter("price")), 
							request.getParameter("product-name"),  
							request.getParameter("email"));
				    
				    System.out.println(item_updated);
				    
				 // Llamada por post
		            responsews = invocationBuilder.post(Entity.entity(item_updated,MediaType.APPLICATION_JSON));
		            if(responsews.getStatus()==200) {
		            	itemResponse  = responsews.readEntity(Item.class);
			            System.out.println(itemResponse);
					    
						RequestDispatcher r6 = request.getRequestDispatcher("/account.jsp");
						r6.forward(request,response);
		            }
		            else {
		            	request.setAttribute("errorCode", responsews.getStatus());
		            	reqDis = request.getRequestDispatcher("/errPage.jsp");
						reqDis.forward(request, response);
		            }
		            
		            // Obtención del cuerpo de la respuesta
		            
	            }
	            else {
	            	request.setAttribute("errorCode", responsews.getStatus());
	            	reqDis = request.getRequestDispatcher("/errPage.jsp");
					reqDis.forward(request, response);
	            }
	            
	            
				
				break;

		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
