package br.com.descompila.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TelaRelatorio extends JFrame {

    private JTextField txtTotal;
    private JTextField txtMedia;
    private JTextField[] txtQuestoes = new JTextField[4];
    private JTextField txtBoa;
    private JTextField txtRegular;
    private JTextField txtRuim;
    private JButton btnVoltar;
    private JButton btnAvancar;

    public TelaRelatorio() {
        setTitle("Relatório");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(620, 720);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painelFundo = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradiente = new GradientPaint(
                        0, 0, new Color(160, 90, 35),
                        0, getHeight(), new Color(30, 12, 2)
                );
                g2d.setPaint(gradiente);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        setContentPane(painelFundo);

        JPanel painelTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelTopo.setOpaque(false);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(10, 15, 0, 0));

        btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Georgia", Font.BOLD, 15));
        btnVoltar.setBackground(new Color(30, 12, 2));
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFocusPainted(false);
        btnVoltar.setPreferredSize(new Dimension(100, 38));
        painelTopo.add(btnVoltar);
        
        painelFundo.add(painelTopo, BorderLayout.NORTH);

        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setOpaque(false);
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(10, 80, 25, 80));

        JScrollPane scroll = new JScrollPane(painelConteudo);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelFundo.add(scroll, BorderLayout.CENTER);

        JLabel lblTitulo = new JLabel("RELATÓRIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Georgia", Font.BOLD, 30));
        lblTitulo.setOpaque(true);
        lblTitulo.setBackground(Color.WHITE);
        lblTitulo.setBorder(BorderFactory.createLineBorder(new Color(180, 100, 50), 2));
        lblTitulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 52));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(lblTitulo);
        painelConteudo.add(Box.createVerticalStrut(20));

        painelConteudo.add(criarLabel("Total de Participações:"));
        painelConteudo.add(Box.createVerticalStrut(6));
        txtTotal = criarCampo();
        painelConteudo.add(txtTotal);
        painelConteudo.add(Box.createVerticalStrut(14));

        painelConteudo.add(criarLabel("Média de Acertos Gerais:"));
        painelConteudo.add(Box.createVerticalStrut(6));
        txtMedia = criarCampo();
        painelConteudo.add(txtMedia);
        painelConteudo.add(Box.createVerticalStrut(14));

        String[] nomes = {"Questão 1:", "Questão 2:", "Questão 3:", "Questão 4:"};
        for (int i = 0; i < 4; i++) {
            painelConteudo.add(criarLabel(nomes[i]));
            painelConteudo.add(Box.createVerticalStrut(6));
            txtQuestoes[i] = criarCampo();
            painelConteudo.add(txtQuestoes[i]);
            painelConteudo.add(Box.createVerticalStrut(14));
        }

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(200, 130, 70));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 10));
        sep.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(sep);
        painelConteudo.add(Box.createVerticalStrut(16));

        JLabel lblAval = new JLabel("Avaliação da Exposição", SwingConstants.CENTER);
        lblAval.setFont(new Font("Georgia", Font.BOLD, 20));
        lblAval.setOpaque(true);
        lblAval.setBackground(Color.WHITE);
        lblAval.setBorder(BorderFactory.createLineBorder(new Color(180, 100, 50), 2));
        lblAval.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        lblAval.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(lblAval);
        painelConteudo.add(Box.createVerticalStrut(14));

        JPanel painelAval = new JPanel(new GridLayout(2, 3, 15, 8));
        painelAval.setOpaque(false);
        painelAval.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
        painelAval.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (String s : new String[]{"Boa:", "Regular:", "Ruim:"}) {
            JLabel l = new JLabel(s, SwingConstants.CENTER);
            l.setFont(new Font("Georgia", Font.BOLD, 16));
            l.setForeground(Color.WHITE);
            painelAval.add(l);
        }

        txtBoa = criarCampoAval(new Color(200, 230, 200));
        txtRegular = criarCampoAval(new Color(255, 240, 180));
        txtRuim = criarCampoAval(new Color(255, 200, 200));

        painelAval.add(txtBoa);
        painelAval.add(txtRegular);
        painelAval.add(txtRuim);

        painelConteudo.add(painelAval);
        painelConteudo.add(Box.createVerticalStrut(28));

        btnAvancar = new JButton("AVANÇAR");
        btnAvancar.setFont(new Font("Georgia", Font.BOLD, 18));
        btnAvancar.setBackground(new Color(160, 90, 35));
        btnAvancar.setForeground(Color.WHITE);
        btnAvancar.setFocusPainted(false);
        btnAvancar.setMaximumSize(new Dimension(240, 55));
        btnAvancar.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelConteudo.add(btnAvancar);
    }

    // MÉTODOS PARA O CONTROLLER INJETAR DADOS
    public void atualizarDados(String total, String media, String[] questoes, String boa, String regular, String ruim) {
        txtTotal.setText(total);
        txtMedia.setText(media);
        for (int i = 0; i < 4; i++) {
            txtQuestoes[i].setText(questoes[i]);
        }
        txtBoa.setText(boa);
        txtRegular.setText(regular);
        txtRuim.setText(ruim);
    }

    public void addVoltarListener(ActionListener listener) { btnVoltar.addActionListener(listener); }
    public void addAvancarListener(ActionListener listener) { btnAvancar.addActionListener(listener); }

    // Métodos auxiliares de UI
    private JLabel criarLabel(String texto) {
        JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
        lbl.setFont(new Font("Georgia", Font.BOLD, 17));
        lbl.setForeground(Color.WHITE);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        lbl.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        return lbl;
    }

    private JTextField criarCampo() {
        JTextField campo = new JTextField();
        campo.setEditable(false);
        campo.setBackground(Color.WHITE);
        campo.setFont(new Font("Georgia", Font.PLAIN, 16));
        campo.setMaximumSize(new Dimension(420, 38));
        campo.setAlignmentX(Component.CENTER_ALIGNMENT);
        campo.setBorder(BorderFactory.createLineBorder(new Color(180, 100, 50), 2));
        return campo;
    }

    private JTextField criarCampoAval(Color cor) {
        JTextField campo = new JTextField();
        campo.setEditable(false);
        campo.setBackground(cor);
        campo.setFont(new Font("Georgia", Font.PLAIN, 15));
        campo.setHorizontalAlignment(JTextField.CENTER);
        campo.setBorder(BorderFactory.createLineBorder(new Color(180, 100, 50), 2));
        return campo;
    }
}