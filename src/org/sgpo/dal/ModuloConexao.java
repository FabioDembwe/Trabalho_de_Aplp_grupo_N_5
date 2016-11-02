/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.sgpo.dal;

import java.sql.*;

/**
 *
 * @author HP
 */
public class ModuloConexao {

    // Estabelecer a conexao com o banco
    public static Connection conector() {
        java.sql.Connection conexao = null;
        // Chama o Driver da importacao
        String driver = "com.mysql.jdbc.Driver";
        //Armazenando informacoes referente ao banco

        String url = "jdbc:mysql://localhost:3306/SGPO1";
        String user = "root";
        String password = "";
        //Estabelecendo a conexao com o banco
         
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            
            System.out.println(e);
            return null;
        }
    }
}
