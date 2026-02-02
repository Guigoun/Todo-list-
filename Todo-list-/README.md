📋 Todo-list Portlet - Liferay DXP
Este projeto consiste em um portlet de gerenciamento de tarefas desenvolvido para a plataforma Liferay DXP 7.4. A aplicação permite a organização de atividades através de uma interface moderna de cards, suporte a subtarefas e uma camada rigorosa de segurança e testes unitários.

🛠️ Tecnologias e Decisões Técnicas
Java 17: Versão utilizada para o desenvolvimento do backend, garantindo compatibilidade com as versões mais recentes do Liferay.

Liferay MVC Portlet: Arquitetura utilizada para o gerenciamento de ações e renderização de views.

Gradle: Gerenciador de dependências e automação de builds.

JUnit 4: Biblioteca utilizada para a implementação da suíte de Testes Unitários.

JSP & Lexicon/Liferay UI: Utilizados para criar uma interface responsiva, com barras de progresso e feedbacks visuais.

🚀 Funcionalidades Implementadas
Gerenciamento de Tarefas (CRUD): Criação, edição, visualização e exclusão de tarefas.

Gestão de Subtarefas: Capacidade de adicionar múltiplos itens a uma tarefa principal.

Monitoramento de Progresso: Barras de progresso automáticas calculadas com base no status das subtarefas.

Controle de Status: Alternância entre estados "Pendente" e "Concluído" para tarefas e itens.

🛡️ Validação e Segurança
A aplicação foi protegida contra vulnerabilidades comuns seguindo as etapas de Validação e Segurança do desafio:

Proteção Anti-XSS: Implementada via HtmlUtil.escape no backend e <c:out> no frontend para neutralizar scripts maliciosos.

Validação de Servidor: Uso de Validator para impedir a persistência de campos vazios ou compostos apenas por espaços.

Validação de Cliente: Uso do atributo required nos formulários HTML5 para feedback imediato ao usuário.

⚙️ Configuração, Compilação e Execução
Para rodar este portlet em seu ambiente Liferay local, siga os passos abaixo:

1. Pré-requisitos
   Java 17 instalado e configurado nas variáveis de ambiente.

Liferay DXP 7.4 (Bundle ou rodando via Docker).

2. Compilação
   Abra o terminal na raiz do projeto (life-projeto) e execute o comando Gradle:

PowerShell
# Limpa builds anteriores e compila o projeto
./gradlew clean build
3. Execução dos Testes Unitários
   Para validar as funcionalidades principais antes do deploy:

PowerShell
# Executa os testes unitários do módulo web
./gradlew :modules:todo-list-web:test
4. Deploy no Liferay
   Com o servidor Liferay rodando, execute:

PowerShell
# Realiza o deploy do módulo no servidor local
./gradlew deploy
O portlet estará disponível na categoria "Sample" (ou na categoria definida nas propriedades do componente) após a mensagem "STARTED" no log do servidor.

🧪 Suíte de Testes (QA)
O projeto conta com uma pasta dedicada de testes (src/test/java) que valida:

Adição correta de tarefas.

Remoção de itens.

Lógica de alternância de conclusão (status).