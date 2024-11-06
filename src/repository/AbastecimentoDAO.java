package repository;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AbastecimentoDAO {
    
    public void salvarAbastecimento(Date data, double quantidade, double precoPorLitro, int quilometragem) throws ClassNotFoundException {
        String sql = "INSERT INTO abastecimento (data_criacao, quantidade, preco_por_litro, quilometragem) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setDate(1, (java.sql.Date) data); // formate a data conforme necessário
            pstmt.setDouble(2, quantidade);
            pstmt.setDouble(3, precoPorLitro);
            pstmt.setInt(4, quilometragem);
            
            pstmt.executeUpdate();
            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
        }
    }
}
