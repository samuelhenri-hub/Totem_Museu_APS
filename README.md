# 🪐 Museu Virtual: Robôs em Marte

Um sistema desktop interativo desenvolvido em **Java (Swing)** que simula um totem de museu virtual focado na exploração de Marte por máquinas. O projeto foi construído com forte ênfase em **Orientação a Objetos (POO)** e no padrão de arquitetura **MVC (Model-View-Controller)**.

## 🚀 Funcionalidades Principais

* 🖼️ **Catálogo Dinâmico de Obras:** Exposição de 10 quadros/obras temáticas. Utiliza injeção de dependência via Controller para renderizar diferentes obras em uma **única View genérica**, otimizando o reuso de código e memória.
* 📝 **Pesquisa Interativa (Quiz):** Sistema de perguntas de Verdadeiro/Falso sobre as obras do museu, finalizado com uma coleta de feedback (Bom, Regular, Ruim) sobre a experiência do usuário.
* 🔒 **Área do Administrador:** Módulo protegido por senha (via teclado virtual numérico) restrito à gestão do museu.
* 📊 **Dashboard de Relatórios:** Geração de estatísticas em tempo real baseadas no uso do totem, incluindo:
    * Total de participações.
    * Média global de acertos.
    * Porcentagem de acerto individualizada por questão.
    * **Alertas Inteligentes de Aprendizado:** O sistema avisa automaticamente se alguma questão específica obteve taxa de acerto inferior a 50%.
* 💾 **Armazenamento em Memória (Volátil):** O sistema utiliza Listas Estáticas (`ArrayList` via classe genérica de memória) para guardar e processar os dados das sessões em tempo de execução, garantindo altíssima performance sem a necessidade de um banco de dados externo ou arquivos TXT.

## 🏗️ Arquitetura e Padrões (MVC)

O projeto quebra o mito de que o MVC precisa ser simétrico (1:1:1), demonstrando maturidade arquitetural ao separar claramente as responsabilidades:

* **Models (Os Dados):** `Obra`, `Questao`, `SessaoPsquisa`, `EstatisticasExpo`, `MemoriaDados`. Classes especialistas e independentes que encapsulam as regras de negócio e a lógica matemática.
* **Controllers (Os Maestros):** `PesquisaController`, `ObrasController`, `LoginAdmController`, `RelatorioController`. Gerenciam o fluxo de navegação e conectam os modelos às interfaces.
* **Views (A Interface):** Telas construídas com Java Swing estritamente focadas na exibição (Front-end), sem regras de negócio embutidas.

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java (JDK)
* **Interface Gráfica:** Java Swing / AWT
* **Paradigma:** Programação Orientada a Objetos (POO)
* **Design Pattern:** MVC
* **Ferramentas:** IDE (NetBeans), Git e GitHub.

## ⚙️ Como Executar o Projeto

1. Certifique-se de ter o **Java Development Kit (JDK)** instalado em sua máquina.
2. Clone este repositório:
   ```bash
   git clone [https://github.com/SEU-USUARIO/NOME-DO-REPOSITORIO.git](https://github.com/SEU-USUARIO/NOME-DO-REPOSITORIO.git)
