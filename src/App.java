package ControleCombustivel.src;
import java.sql.*;
import java.util.Scanner;

public class App {

    private static final String DB_URL = "jdbc:sqlite:combustivel.db";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 3) {
            System.out.println("1. Inserir abastecimento");
            System.out.println("2. Listar abastecimentos");
            System.out.println("3. Sair");
            System.out.print("Escolha uma opção: ");
            option = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha

            switch (option) {
                case 1:
                    inserirAbastecimento(scanner);
                    break;
                case 2:
                    listarAbastecimentos();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static void inserirAbastecimento(Scanner scanner) {
        System.out.print("Data do Abastecimento (YYYY-MM-DD): ");
        String data = scanner.nextLine();
        System.out.print("Quantidade de Combustível (litros): ");
        double quantidade = scanner.nextDouble();
        System.out.print("Preço por Litro: ");
        double precoPorLitro = scanner.nextDouble();
        System.out.print("Valor Total: ");
        double valorTotal = scanner.nextDouble();
        System.out.print("Quilometragem: ");
        int quilometragem = scanner.nextInt();

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO Abastecimentos (data_abastecimento, quantidade_combustivel, preco_por_litro, valor_total, quilometragem) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, data);
            pstmt.setDouble(2, quantidade);
            pstmt.setDouble(3, precoPorLitro);
            pstmt.setDouble(4, valorTotal);
            pstmt.setInt(5, quilometragem);
            pstmt.executeUpdate();
            System.out.println("Abastecimento inserido com sucesso!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void listarAbastecimentos() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT * FROM Abastecimentos";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Data: " + rs.getString("data_abastecimento"));
                System.out.println("Quantidade: " + rs.getDouble("quantidade_combustivel"));
                System.out.println("Preço por Litro: " + rs.getDouble("preco_por_litro"));
                System.out.println("Valor Total: " + rs.getDouble("valor_total"));
                System.out.println("Quilometragem: " + rs.getInt("quilometragem"));
                System.out.println("--------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
