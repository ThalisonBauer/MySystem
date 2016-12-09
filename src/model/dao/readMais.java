/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.bean.Produto;

public class readMais {
        public List<Produto>read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
            List<Produto> produtos = new ArrayList<>();
                try {
                stmt = con.prepareStatement("SELECT  COUNT(produto.idproduto),produto.nomeproduto,produto.precoproduto FROM produto INNER JOIN orca_produt ON orca_produt.id_produto = produto.idproduto GROUP BY produto.nomeProduto ORDER BY COUNT(produto.idproduto) DESC");
                rs=stmt.executeQuery();
                while(rs.next()){
                    Produto produto = new Produto();
                    
                    
                    produto.setQtd(rs.getInt(1));
                    produto.setNomeProduto(rs.getString(2));
                    produto.setPrecoProduto(rs.getFloat(3));
                    
                    produtos.add(produto);
                    }
                    
                
            } catch (SQLException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
                return produtos;
        }
}
