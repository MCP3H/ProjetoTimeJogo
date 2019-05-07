import Jogo.DAOJogo;
import Jogo.JogoResource;
import Time.DAOTime;
import Time.TimeResource;
import ch.qos.logback.core.CoreConstants;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RestApp extends Application<Configuration> {

    public static void main(String[] args) throws Exception {
        new RestApp().run(new String[]{"server"});
    }

    @Override
    public void run(Configuration configuration, Environment environment) {
        DAOTime daotime = new DAOTime();
        DAOJogo daojogo = new DAOJogo();
        environment.jersey().register(new TimeResource(daotime));
        environment.jersey().register(new JogoResource(daojogo));
    }
}





