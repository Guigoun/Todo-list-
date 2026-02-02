# 📝 Todo List Project (Liferay MVC)

Sistema de gerenciamento de tarefas desenvolvido como parte de um estudo aprofundado sobre migração de arquitetura Java (Console para Web Modular).

O projeto utiliza a estrutura do **Liferay DXP 7.4** com **MVC Portlet**, demonstrando a aplicação prática de padrões de projeto e desenvolvimento modular.

## 🚀 Sobre o Projeto

Este projeto nasceu da refatoração de um sistema *Legacy* (Console Application) para uma arquitetura Web moderna baseada em Portlets.

**Objetivos Alcançados:**
* Migração de regras de negócio (Services) de Java puro para Liferay OSGi.
* Implementação do padrão **MVC (Model-View-Controller)** no contexto de Portlets.
* Uso de **JSP** e **JSTL** para renderização dinâmica do front-end.
* Configuração de ambiente com **Gradle** e **Liferay Workspace**.

## 🛠️ Tecnologias Utilizadas

* **Java 11 / JDK**
* **Liferay DXP 7.4 (GA/Update 112+)**
* **Liferay MVC Portlet**
* **JSP & JSTL** (Front-end)
* **Gradle** (Gerenciador de Dependências)
* **OSGi** (Modularidade)

## 📂 Estrutura do Projeto

O projeto segue a arquitetura padrão do Liferay Workspace:

* `modules/todo-list-web`: Módulo principal contendo o Portlet.
    * `model`: Classes de domínio (Tarefa, Usuario).
    * `service`: Regras de negócio e persistência em memória.
    * `portlet`: Controller (Camada de recepção das requisições).
    * `resources/META-INF/resources`: Views (arquivos .jsp, css).

## 📦 Como Rodar

1.  Certifique-se de ter o **Liferay Portal** configurado (Tomcat Bundle).
2.  No terminal ou IDE, navegue até a raiz do projeto.
3.  Execute o deploy do módulo:
    ```bash
    ./gradlew deploy
    ```
4.  Inicie o servidor Liferay.
5.  Adicione o Widget **"TodoList"** (Categoria: Sample) em qualquer página do portal.

---
Desenvolvido por **Guilherme** | Desenvolvedor Back End