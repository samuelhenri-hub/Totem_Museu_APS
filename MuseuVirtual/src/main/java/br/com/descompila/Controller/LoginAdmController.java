package br.com.descompila.Controller;

import br.com.descompila.View.AreaAdministrador;

import br.com.descompila.View.Inicio; 

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAdmController {

    private AreaAdministrador view;
    private final String SENHA_CORRETA = "1234";

    public LoginAdmController() {
        this.view = new AreaAdministrador();

        this.view.addVoltarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose(); 
                
                new Inicio().setVisible(true); 
            }
        });

        this.view.addSeguirListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = view.getSenhaDigitada();

                if (senha.length() < 4) {
                    view.exibirMensagem("Digite os 4 números da senha!");
                    return;
                }

                if (senha.equals(SENHA_CORRETA)) {
                    view.dispose(); 
                    
                    new RelatorioController().exibirRelatorio(); 
                    
                } else {
                    view.exibirMensagem("Senha incorreta!");
                    view.limparSenha();
                }
            }
        });
    }

    public void abrirTelaLogin() {
        view.setVisible(true);
    }
}