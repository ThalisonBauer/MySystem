package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.bean.Orcamento;
import model.bean.Produto;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.Cliente;
import view.Main;

public class OrcamentoDAO {
    public void createOrcamento(Orcamento  o){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement("INSERT INTO orcamento(tituloOrcamento,idCliente,dateOrcamento,totalOrcamento)VALUES(?,?,?,?)");
            stmt.setString(1,o.getTituloOrcamento());
            stmt.setInt(2, o.getIdCliente());
            stmt.setString(3, o.getDateOrcamento());
            stmt.setFloat(4, o.getTotalOrcamento());
            
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Orçamento Salvo!");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Erro ao Salvar Orçamento!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
public void prod_orca(int  o, int p){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        Orcamento or = new Orcamento();
        Produto pro = new Produto();
        try {
            stmt = con.prepareStatement("INSERT INTO orca_produt(id_produto,id_orcamento)VALUES(?,?)");
            stmt.setInt(1, p);
            stmt.setInt(2, o);
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," ERRO AO SALVAR ID PRODUTO E ORCAMENTO!"+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    }        
public int idOrcamento (){
    int i =0 ;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       
        try {
                    stmt = con.prepareStatement("select max(idorcamento) from orcamento");
                    rs = stmt.executeQuery();
                    while(rs.next())
                    {
                        i = rs.getInt(1);
                    }
                    } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return i;
    }
public List<Orcamento> read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Orcamento> orcamentos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT cliente.nomeCliente, orcamento.tituloOrcamento orcamento,idOrcamento FROM cliente INNER JOIN orcamento ON orcamento.idCliente = cliente.idcliente ORDER BY idorcamento DESC");
            rs=stmt.executeQuery();
                while(rs.next()){
                 Orcamento orcamento = new Orcamento();
                 orcamento.setNomeCliente(rs.getString(1));
                 orcamento.setTituloOrcamento(rs.getString(2));
                 orcamento.setIdOrcamento(rs.getInt(3));
                 orcamentos.add(orcamento);
                }
           } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }return orcamentos;
}
}
