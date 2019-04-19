/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 31806872
 */
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import io.dropwizard.jersey.*;
import io.dropwizard.jersey.params.*;
import java.util.*;

@Path("/times")
@Produces(MediaType.APPLICATION_JSON)
public class TimeResource {

    private List<Time> times;
    private int proximoId;

    public TimeResource() {
        proximoId = 1;
        times = new ArrayList<>();
        times.add(new Time("Pouneirraz", 2001, "São Paulo", "SP"));
        times.add(new Time("Corrintyaz", 1999, "Rio de Janeiro", "RJ"));
        times.add(new Time("SanPaul", 1987, "Acre", "AC"));
    }
    
    @GET
    public List<Time> read() {
        return times;
    }
    
    @POST
    public Time create(Time p) {
        p.setId(proximoId++);
        times.add(p);
        return p;
    }
    
    @PUT
    @Path("{id}")
    public Time update(@PathParam("id") LongParam id, Time p) {
        for (Time Time: times) {
            if (Time.getId() == id.get()) {
                Time.setNome(p.getNome());
                Time.setAno(p.getAno());
                Time.setCidade(p.getCidade());
                Time.setEstado(p.getEstado());
                return Time;
            }
        }
        return null;
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id) {
        Time p = null;
        for (Time Time: times) {
            if (Time.getId() == id.get()) {
                p = Time;
                break;
            }
        }
        if (p != null) {
            times.remove(p);
        } else {
            throw new WebApplicationException("Time com id=" + id.get() + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
}
