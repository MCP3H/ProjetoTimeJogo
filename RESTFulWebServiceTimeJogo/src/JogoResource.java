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

@Path("/jogos")
@Produces(MediaType.APPLICATION_JSON)
public class JogoResource {

    private List<Jogo> jogos;
    private int proximoId;

    public JogoResource() {
        proximoId = 1;
        jogos = new ArrayList<>();
        jogos.add(new Jogo(1, 2, 3, 4));
        jogos.add(new Jogo(2, 1, 3, 4));
        jogos.add(new Jogo(1, 3, 2, 2));
    }
    
    @GET
    public List<Jogo> read() {
        return jogos;
    }
    
    @POST
    public Jogo create(Jogo p) {
        p.setId(proximoId++);
        jogos.add(p);
        return p;
    }
    
    @PUT
    @Path("{id}")
    public Jogo update(@PathParam("id") LongParam id, Jogo p) {
        for (Jogo Jogo: jogos) {
            if (Jogo.getId() == id.get()) {
                Jogo.setTimeA(p.getTimeA());
                Jogo.setTimeB(p.getTimeB());
                Jogo.setGolsA(p.getGolsA());
                Jogo.setGolsB(p.getGolsB());
                return Jogo;
            }
        }
        return null;
    }
    
    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") LongParam id) {
        Jogo p = null;
        for (Jogo Jogo: jogos) {
            if (Jogo.getId() == id.get()) {
                p = Jogo;
                break;
            }
        }
        if (p != null) {
            jogos.remove(p);
        } else {
            throw new WebApplicationException("Jogo com id=" + id.get() + " não encontrado!", 404);
        }
        return Response.ok().build();
    }
}

