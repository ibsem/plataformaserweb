package edu.cesusc.search;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.log4j.Logger;

import edu.cesusc.search.SearchDAO;


//buscador
@Path("/pesquisa")
public class SearchREST {


	@Path("/titulo")
	@GET
	@Produces("application/json")
	public Response searchTitle(@Context HttpServletRequest request, @QueryParam("q") String termo){
			Object result = SearchDAO.getInstance().search(termo);
    		if(result != null){
    			return Response.status(Status.OK).entity(result).build();
    		}else{
    			return Response.status(Status.NO_CONTENT).entity(result).build();
    		}
    	
	}



	private Object getAtribute(String atributeName, HttpServletRequest request){
		HttpSession sessao = request.getSession();
		Object atribute = null;
		if(sessao.getAttribute(atributeName) != null){
			atribute = sessao.getAttribute(atributeName);
		}
		return atribute;
	}

	private void setAtribute(String atributeName, HttpServletRequest request){
		HttpSession sessao = request.getSession();
		sessao.setAttribute(atributeName, true);
	}

}
