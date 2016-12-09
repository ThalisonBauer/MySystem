package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Cliente;

public class ClienteDAO {
    public void create(Cliente  c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO cliente(nomeCliente,emailCliente,foneCliente)VALUES(?,?,?)");
            stmt.setString(1,c.getNomeCliente());
            stmt.setString(2, c.getEmailCliente());
            stmt.setString(3, c.getFoneCliente());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Cliente Salvo!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Salvar!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public List<Cliente> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Cliente> clientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM cliente ORDER BY nomeCliente");
            rs =  stmt.executeQuery();
            
                while(rs.next()){
                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("idcliente"));
                    cliente.setNomeCliente(rs.getString("nomeCliente"));
                    cliente.setEmailCliente(rs.getString("emailCliente"));
                    cliente.setFoneCliente(rs.getString("foneCliente"));
                    clientes.add(cliente);
                }
                    } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return clientes;
    }
    public void update(Cliente  c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE cliente SET nomeCliente =? ,emailCliente =? ,foneCliente = ? WHERE idcliente =?");
            stmt.setString(1,c.getNomeCliente());
            stmt.setString(2, c.getEmailCliente());
            stmt.setString(3, c.getFoneCliente());
            stmt.setInt(4,c.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Atualzado com SUCESSO!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao ATUALIZAR!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    public void delete(Cliente  c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE idcliente =?");
            stmt.setInt(1,c.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Excluido com SUCESSO!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Excluir!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
 