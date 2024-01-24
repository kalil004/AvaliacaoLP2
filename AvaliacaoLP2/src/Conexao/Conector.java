/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost/alunos", "root", "fatec");
		} catch(SQLException excecao) {
			throw new RuntimeException(excecao); 
		}
	}
}
