package Jogo;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/jogos")
@Produces(MediaType.APPLICATION_JSON)
public class JogoResource {

    private DAOJogo daojogo;

    public JogoResource(DAOJogo daojogo) {
        this.daojogo = daojogo;
    }
//Examples:
    
    @GET
    public List<Jogo> readAll() {
        return daojogo.readAll();
    }
//fetch('http://localhost:8080/jogos')
//.then(response => response.json())
//.then(json => console.log(json));

    @GET
    @Path("/{id}")
    public Jogo read(@PathParam("id") IntParam id) {
        return this.daojogo.read(id.get());
    }
//fetch('http://localhost:8080/jogos/2')
//.then(response => response.json())
//.then(json => console.log(json));
    
    @POST
    public Jogo create(Jogo j) {
        return this.daojogo.create(j);
    }
//fetch('http://localhost:8080/jogos', {
//method: 'POST',
//body: JSON.stringify({
//timeA: 1,
//timeB: 4,
//golsA: 3,
//golsB: 0
//}),
//headers: {
//"Content-type": "application/json; charset=UTF-8"
//}
//})
//.then(response => response.json())
//.then(json => console.log(json));    
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") IntParam id) {
        if (this.daojogo.delete(id.get())) {
            return Response.ok().build();
        } else {
            throw new WebApplicationException("Jogo com id = " + id.get() + " não encontrado!", 404);
        }
    }
//fetch('http://localhost:8080/jogos/10', {
//method: 'DELETE'
//});

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") IntParam id, Jogo j) {
        j.setId(id.get());
        if (this.daojogo.update(j)) {
            return Response.ok().build();
        } else {
            throw new WebApplicationException("Jogo com id = " + id.get() + " não encontrado!", 404);
        }
    }
//fetch('http://localhost:8080/jogos/9', {
//method: 'PUT',
//body: JSON.stringify({
//timeA: 2,
//timeB: 1,
//golsA: 1,
//golsB: 1
//}),
//headers: {
//"Content-type": "application/json; charset=UTF-8"
//}
//})
//.then(response => response.json())
//.then(json => console.log(json)); 
    
}


