import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import repository.*;

public class Main {

    public static void main(String[] args) {
        // Criação da janela principal
        JFrame frame = new JFrame("Formulário de Abastecimento");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10)); // Layout de grade para organizar os campos

        // Criação dos labels e campos de entrada
        JLabel lblData = new JLabel("Data do Abastecimento:");
        JTextField txtData = new JTextField();

        JLabel lblValorTotal = new JLabel("Valor Total:");
        JTextField txtValorTotal = new JTextField();

        JLabel lblPrecoCombustivel = new JLabel("Preço do Combustível:");
        JTextField txtPrecoCombustivel = new JTextField();

        JLabel lblQuilometragem = new JLabel("Quilometragem:");
        JTextField txtQuilometragem = new JTextField();

        // Criação do botão de salvar
        JButton btnSalvar = new JButton("Salvar");

        // Adicionando os componentes à janela
        frame.add(lblData);
        frame.add(txtData);

        frame.add(lblValorTotal);
        frame.add(txtValorTotal);

        frame.add(lblPrecoCombustivel);
        frame.add(txtPrecoCombustivel);

        frame.add(lblQuilometragem);
        frame.add(txtQuilometragem);

        frame.add(new JLabel()); // Espaço vazio para alinhamento
        frame.add(btnSalvar);

        // Ação do botão "Salvar"
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String dataStr = txtData.getText();
                    String valorTotalStr = txtValorTotal.getText();
                    String precoCombustivelStr = txtPrecoCombustivel.getText();
                    String quilometragemStr = txtQuilometragem.getText();

                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); // ajuste o formato conforme
                                                                                      // necessário
                    Date data = dateFormat.parse(dataStr);

                    // Converter String para BigDecimal
                    Double valorTotal = new Double(valorTotalStr);
                    Double precoCombustivel = new Double(precoCombustivelStr);

                    // Converter String para int
                    int quilometragem = Integer.parseInt(quilometragemStr);

                    new AbastecimentoDAO().salvarAbastecimento(data, valorTotal / precoCombustivel, precoCombustivel,
                            quilometragem);

                    // Exemplo de ação: exibir os dados inseridos em um popup
                    JOptionPane.showMessageDialog(frame,
                            "Dados salvos:\n" +
                                    "Data: " + data + "\n" +
                                    "Valor Total: " + valorTotal + "\n" +
                                    "Preço do Combustível: " + precoCombustivel + "\n" +
                                    "Quilometragem: " + quilometragem);

                } catch (Exception ex) {

                }
            }
        });

        // Exibir a janela
        frame.setVisible(true);
    }
}
