package br.com.descompila.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener; 

public class AreaAdministrador extends JFrame {

    private String senhaDigitada = "";
    
    
    private JButton btnVoltar;
    private JButton btnSeguir;
    private JPasswordField campoSenha;

    public AreaAdministrador() {
        setTitle("Área de Administrador");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 620);
        setLocationRelativeTo(null);
        setResizable(false);

        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 20, 10));

        JPanel cabecalho = new JPanel(new BorderLayout());
        cabecalho.setBackground(new Color(60, 30, 10));
        cabecalho.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        
        btnVoltar = new JButton("← VOLTAR");
        estilizarBotao(btnVoltar);
        cabecalho.add(btnVoltar, BorderLayout.WEST);

        JLabel titulo = new JLabel("ÁREA DE ADMINISTRADOR", SwingConstants.CENTER);
        titulo.setFont(new Font("Century Gothic", Font.BOLD, 18));
        titulo.setForeground(new Color(255, 200, 100));
        cabecalho.add(titulo, BorderLayout.CENTER);

        add(cabecalho, BorderLayout.NORTH);

        JPanel painel = new JPanel(new BorderLayout(0, 20));
        painel.setBackground(new Color(40, 25, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(25, 40, 25, 40));
        add(painel, BorderLayout.CENTER);

        JLabel instrucao = new JLabel("Digite a senha de 4 números", SwingConstants.CENTER);
        instrucao.setFont(new Font("Century Gothic", Font.BOLD, 20));
        instrucao.setForeground(Color.WHITE);

        campoSenha = new JPasswordField();
        campoSenha.setFont(new Font("Century Gothic", Font.BOLD, 28));
        campoSenha.setHorizontalAlignment(JTextField.CENTER);
        campoSenha.setEditable(false);
        campoSenha.setEchoChar('●');
        campoSenha.setBackground(new Color(60, 40, 15));
        campoSenha.setForeground(Color.WHITE);
        campoSenha.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 100, 50), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        JPanel topo = new JPanel(new GridLayout(2, 1, 0, 15));
        topo.setBackground(new Color(40, 25, 10));
        topo.add(instrucao);
        topo.add(campoSenha);
        painel.add(topo, BorderLayout.NORTH);

        JPanel teclado = new JPanel(new GridLayout(4, 3, 10, 10));
        teclado.setBackground(new Color(50, 30, 10));
        teclado.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        
        for (int i = 1; i <= 9; i++) {
            String numero = String.valueOf(i);
            JButton btn = criarBotao(numero);
            btn.addActionListener(e -> {
                if (senhaDigitada.length() < 4) {
                    senhaDigitada += numero;
                    campoSenha.setText(senhaDigitada);
                }
            });
            teclado.add(btn);
        }

        JButton btnApagar = criarBotao("APAGAR");
        btnApagar.addActionListener(e -> {
            if (!senhaDigitada.isEmpty()) {
                senhaDigitada = senhaDigitada.substring(0, senhaDigitada.length() - 1);
                campoSenha.setText(senhaDigitada);
            }
        });
        teclado.add(btnApagar);

        JButton btnZero = criarBotao("0");
        btnZero.addActionListener(e -> {
            if (senhaDigitada.length() < 4) {
                senhaDigitada += "0";
                campoSenha.setText(senhaDigitada);
            }
        });
        teclado.add(btnZero);

        
        btnSeguir = criarBotao("SEGUIR");
        teclado.add(btnSeguir);

        painel.add(teclado, BorderLayout.CENTER);
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Century Gothic", Font.BOLD, 16));
        btn.setBackground(new Color(180, 80, 30));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        return btn;
    }

    private void estilizarBotao(JButton btn) {
        btn.setFont(new Font("Century Gothic", Font.BOLD, 12));
        btn.setBackground(new Color(180, 80, 30));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
    }

    public void addVoltarListener(ActionListener listener) {
        btnVoltar.addActionListener(listener);
    }

    public void addSeguirListener(ActionListener listener) {
        btnSeguir.addActionListener(listener);
    }

    public String getSenhaDigitada() {
        return senhaDigitada;
    }

    public void limparSenha() {
        senhaDigitada = "";
        campoSenha.setText("");
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}