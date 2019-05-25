package Time;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/times")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {

    private DAOTime daotime;

    public TimeResource(DAOTime daotime) {
        this.daotime = daotime;
    }
//Examples:
    
    @GET
    public List<Time> readAll() {
        return daotime.readAll();
    }
//fetch('http://localhost:8080/times')
//.then(response => response.json())
//.then(json => console.log(json));

    @GET
    @Path("/{id}")
    public Time read(@PathParam("id") IntParam id) {
        return this.daotime.read(id.get());
    }
//fetch('http://localhost:8080/times/1')
//.then(response => response.json())
//.then(json => console.log(json));

    @POST
    public Time create(Time t) {
        return this.daotime.create(t);
    }
//fetch('http://localhost:8080/times', {
//method: 'POST',
//body: JSON.stringify({
//nome: 'Vasco',
//ano: 1898,
//cidade: 'São Paulo',
//estado: 'SP'
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
        if (this.daotime.delete(id.get())) {
            return Response.ok().build();
        } else {
            throw new WebApplicationException("Time com id = " + id.get() + " não encontrado!", 404);
        }
    }
//fetch('http://localhost:8080/times/4', {
//method: 'DELETE'
//});

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") IntParam id, Time t) {
        t.setId(id.get());
        if (this.daotime.update(t)) {
            return Response.ok().build();
        } else {
            throw new WebApplicationException("Time com id = " + id.get() + " não encontrado!", 404);
        }
    }
//fetch('http://localhost:8080/times/4', {
//method: 'PUT',
//body: JSON.stringify({
//nome: 'Corinthians',
//ano: 1910,
//cidade: 'São Paulo',
//estado: 'SP'
//}),
//headers: {
//"Content-type": "application/json; charset=UTF-8"
//}
//})
//.then(response => response.json())
//.then(json => console.log(json));
    
}
