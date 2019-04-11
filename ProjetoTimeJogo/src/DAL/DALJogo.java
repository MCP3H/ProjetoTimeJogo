package DAL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DALJogo {
    private Connection getConnection() {
        String url = "jdbc:derby://localhost:1527/faculdade";
        String user = "usuario";
        String password = "123";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean save(Invoice invoice) {
        try {
            Connection conn = getConnection();

            // 1o Salva a invoice
            String sql = "INSERT INTO INVOICES (COMPANY, DATE) VALUES (?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, invoice.getCompany());
            pstm.setString(2, invoice.getDate());
            pstm.executeUpdate();
            ResultSet rs = pstm.getGeneratedKeys();
            int id =0;
            if(rs.next()){
                id = rs.getInt(1);
            }
            // 2o Salva os itens (eu preciso do ID da invoice para salvar os itens)
            List<Item> items = invoice.getItems();
            sql = "INSERT INTO items (INVOICE_ID, NAME, QUANTITY, VALUE) VALUES (?,?,?,?)";

            for(Item i: items) {
                pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, invoice.getId());
                pstm.setString(2, i.getName());
                pstm.setInt(3, i.getQuantity());
                pstm.setDouble(4, i.getValue());

                pstm.executeUpdate();
            }

            conn.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }
    
    public boolean update(Invoice invoice) {
        return false;
    }
    
    public boolean delete(Invoice invoice) {
        try {
            Connection conn = getConnection();
            // Apaga os itens
            String sql = "DELETE FROM ITEMS WHERE INVOICE_ID =?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, invoice.getId());
            pstm.executeUpdate();

            // Apaga a invoice
            sql = "DELETE FROM INVOICES WHERE ID=?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, invoice.getId());
            pstm.executeUpdate();

            conn.close();
            return true;
        } catch( Exception e ) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Invoice get(int id) {
        try {
            Invoice resposta = new Invoice();
            Connection conn = getConnection();

            // Buscar Invoice
            String sql = "SELECT * FROM INVOICES WHERE ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();

            if (!rs.next()) { return null; }

            resposta.setId(id);
            resposta.setCompany(rs.getString("COMPANY"));
            resposta.setDate(rs.getString("DATE"));

            // Buscar os itens
            sql = "SELECT * FROM ITEMS WHERE INVOICE_ID=?";
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, id);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setName(rs.getString("NAME"));
                item.setQuantity(rs.getInt("QUANTITY"));
                item.setValue(rs.getDouble("VALUE"));
                resposta.getItems().add(item);
            }

            conn.close();
            return resposta;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<Invoice> getAll() {
        try {
            List<Invoice> resposta = new ArrayList<Invoice>();
            Connection conn = getConnection();
            
            String sql = "SELECT * FROM INVOICES";
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Invoice adres = new Invoice();
                adres.setId(rs.getInt("ID"));
                adres.setCompany(rs.getString("COMPANY"));
                adres.setDate(rs.getString("DATE"));
                
                sql = "SELECT * FROM ITEMS WHERE INVOICE_ID=?";
                pstm = conn.prepareStatement(sql);
                pstm.setInt(1, rs.getInt("ID"));
                rs = pstm.executeQuery();

                while (rs.next()) {
                    Item item = new Item();
                    item.setName(rs.getString("NAME"));
                    item.setQuantity(rs.getInt("QUANTITY"));
                    item.setValue(rs.getDouble("VALUE"));
                    adres.getItems().add(item);
                }
                
                resposta.add(adres);
            }
            
            conn.close();
            return resposta;
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}



