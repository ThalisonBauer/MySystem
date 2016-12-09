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
import model.bean.Produto;
public class ProdutoDAO {
        public void createProduto(Produto  p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO produto(nomeProduto,precoProduto)VALUES(?,?)");
            stmt.setString(1,p.getNomeProduto());
            stmt.setFloat(2,p.getPrecoProduto());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Produto Salvo!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Salvar!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
        public List<Produto> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM produto ORDER BY idproduto");
            rs =  stmt.executeQuery();
            
                while(rs.next()){
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("idproduto"));
                    produto.setNomeProduto(rs.getString("nomeProduto"));
                    produto.setPrecoProduto(rs.getFloat("precoProduto"));
                    produtos.add(produto);
                }
                    } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtos;
    }
        public List<Produto> read(Produto p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> produtoss = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT idproduto,nomeProduto,precoProduto FROM produto  WHERE idproduto = ?");
            stmt.setInt(1,p.getId());
            rs =  stmt.executeQuery();
            
                    rs.next();
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("idproduto"));
                    produto.setNomeProduto(rs.getString("nomeProduto"));
                    produto.setPrecoProduto(rs.getFloat("precoProduto"));
                    produtoss.add(produto);
                
                    } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return produtoss;
        }
        public void update(Produto  p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("UPDATE produto SET nomeProduto =? ,precoProduto =? WHERE idproduto =?");
            stmt.setString(1,p.getNomeProduto());
            stmt.setFloat(2,p.getPrecoProduto());
            stmt.setInt(3,p.getId());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Atualzado com SUCESSO!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao ATUALIZAR!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
    

