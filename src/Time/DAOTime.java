package Time;

import Server.ConexaoJavaDb;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAOTime {

    private PreparedStatement stmC;
    private PreparedStatement stmRALL;
    private PreparedStatement stmR;
    private PreparedStatement stmU;
    private PreparedStatement stmD;
    
    
    @SuppressWarnings("CallToPrintStackTrace")
    public DAOTime(ConexaoJavaDb conexao) {
        try {
            Connection conn = conexao.getConexao();
            this.stmC = conn.prepareStatement("INSERT INTO TIMES (NOME, ANO, CIDADE, ESTADO) VALUES (?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            this.stmRALL = conn.prepareStatement("SELECT * FROM TIMES");
            this.stmR = conn.prepareStatement("SELECT * FROM TIMES WHERE ID=?");
            this.stmU = conn.prepareStatement("UPDATE TIMES SET NOME = ?, ANO = ?, CIDADE = ?, ESTADO = ? WHERE ID = ?");
            this.stmD = conn.prepareStatement("DELETE FROM TIMES WHERE ID=?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Time> readAll() {
        try {
            ResultSet rs = this.stmRALL.executeQuery();
            List<Time> times = new ArrayList<>();
            while (rs.next()) {
                Time t = new Time();
                t.setId(rs.getInt("ID"));
                t.setNome(rs.getString("NOME"));
                t.setAno(rs.getInt("ANO"));
                t.setCidade(rs.getString("CIDADE"));
                t.setEstado(rs.getString("ESTADO"));
                times.add(t);
            }
            return times;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Time read(int id) {
        try {
            Time t = new Time();
            this.stmR.setInt(1, id);
            ResultSet rs = this.stmR.executeQuery();
            while (rs.next()) {
                t.setId(rs.getInt("ID"));
                t.setNome(rs.getString("NOME"));
                t.setAno(rs.getInt("ANO"));
                t.setCidade(rs.getString("CIDADE"));
                t.setEstado(rs.getString("ESTADO"));
            }
            return t;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean delete(int id) {
        try {
            this.stmD.setInt(1, id);
            int i = this.stmD.executeUpdate();
            if (i>0){
                return true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }
 
    public Time create(Time time) {
        try {
            this.stmC.setString(1, time.getNome());
            this.stmC.setInt(2, time.getAno());
            this.stmC.setString(3, time.getCidade());
            this.stmC.setString(4, time.getEstado());
            this.stmC.executeUpdate();
            
            ResultSet rs = this.stmC.getGeneratedKeys();
            rs.next();
            int id = rs.getInt(1);
            time.setId(id);
            
            return time;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Time time) {
        try {
            this.stmU.setString(1, time.getNome());
            this.stmU.setInt(2, time.getAno());
            this.stmU.setString(3, time.getCidade());
            this.stmU.setString(4, time.getEstado());
            this.stmU.setInt(5, time.getId());
            int i = this.stmU.executeUpdate();
            if (i>0){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
