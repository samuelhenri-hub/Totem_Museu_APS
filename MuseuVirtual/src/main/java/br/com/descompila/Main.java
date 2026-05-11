package br.com.descompila;

import br.com.descompila.View.Inicio;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("Erro ao carregar o tema: " + e.getMessage());
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Inicio telaInicial = new Inicio();
                telaInicial.setVisible(true);
            }
        });
    }
}