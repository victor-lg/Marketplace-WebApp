package es.wuolahpop.images.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import es.wuolahpop.data.Imagenenbbdd;
import es.wuolahpop.data.Item;
import es.wuolahpop.data.ItemFromCatalogue;
import es.wuolahpop.data.User;
import es.wuolahpop.images.controller.*;
import es.wuolahpop.images.manager.ImagenenbbddManager;

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

        session = null;
		
		String accion = request.getParameter("typeOfQuery");
		
		if(accion==null){
			accion = "getAllProducts";
		}
		
		
		
		switch(accion)
		{
			/*case "getAllProducts":
				try {
					webtarget = client.target("http://localhost:10603");
		  			webtargetPath = webtarget.path("catalogue").path("requestAll");
		            invocationBuilder = webtargetPath.request(MediaType.APPLICATION_JSON);
		            
		            responsews = invocationBuilder.get();
		            
		            Item [] itemsList = responsews.readEntity(Item[].class);
		            session = request.getSession(true);

		            session.setAttribute("itemsList", itemsList);

		            RequestDispatcher r1 = request.getRequestDispatcher("/store.jsp");
					r1.forward(request,response);
				} catch (Exception e) {
					System.out.println(e);
				}
				*/
				
			
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
	            
	            // Obtención del cuerpo de la respuesta
	            itemResponse  = responsews.readEntity(Item.class);
	            
	            System.out.println(itemResponse);
			    
				RequestDispatcher r2 = request.getRequestDispatcher("/store.jsp");
				r2.forward(request,response);
				
				break;
			
				
			case "consultaBBDD":
				//ImagenenbbddManager ibm4 = new ImagenenbbddManager("UP");
				//List<Imagenenbbdd> lista4=ibm4.findBySimilarTitle(request.getParameter("palabra"));
				//request.setAttribute("lista", lista4);
				RequestDispatcher r4 = request.getRequestDispatcher("catalogo.jsp");
				r4.forward(request,response);
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
