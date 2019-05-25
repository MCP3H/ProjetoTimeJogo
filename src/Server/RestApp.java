package Server;

import Jogo.DAOJogo;
import Jogo.JogoResource;
import Time.DAOTime;
import Time.TimeResource;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;


public class RestApp extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new RestApp().run(new String[]{"server"});
    }
    
    @Override
    public void initialize(final Bootstrap<Configuration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/html", "/", "index.html"));
    }
    
    @Override
    public void run(Configuration configuration, Environment environment) {
        try {
            ConexaoJavaDb conexao = new ConexaoJavaDb ("app", "app", "jdbc:derby://localhost", "1527", "bancotimejogo");
             
            DAOTime daotime = new DAOTime(conexao);
            DAOJogo daojogo = new DAOJogo(conexao);
            
            environment.jersey().register(new TimeResource(daotime));
            environment.jersey().register(new JogoResource(daojogo));
            environment.jersey().setUrlPattern("/api/*");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
