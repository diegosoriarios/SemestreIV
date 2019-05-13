**Documento de**  **Definição**  **de Requisitos**



**Projeto** : Sistema de assinatura de cursos

**Responsáveis** : Diego Soria Rios

**1. Introdução**

Este documento apresenta os requisitos de usuário do sistema de assinatura de Cursos e está organizado da seguinte forma: a Seção 2 contém uma descrição do propósito do sistema; a Seção 3 apresenta uma descrição do minimundo apresentando o problema; e a Seção 4 apresenta as listas de requisitos de usuário levantados junto ao cliente.

**2. Descrição do Propósito do Sistema**

Esse sistema tem como objetivo realizar assinatura de cursos online, permitindo aos alunos realizarem provas ao final de cada curso e avaliar seu desempenho, assim como é possível que professores se cadastrem e vendam seus próprios cursos.

**3. Descrição do Minimundo**

| **Descrição do Minimundo** |
| --- |
| O sistema de assinatura deve guardar os dados dos clientes, como código, nome, data de nascimento, telefone, email, login, senha, cartão de crédito, histórico de cursos, média de notas e plano de assinatura.Também é necessário ter um controle dos professores, guardando o código, nome, telefone, email, login, senha e cursos.Os cursos precisam ter um código, nome, professor responsável. |



**4. Requisitos de Usuário**

Tomando por base o contexto do sistema, foram identificados os seguintes requisitos de usuário:

**Requisitos Funcionais**

| **Identificador** | **Descrição** | **Prioridade** | **Depende de** |
| --- | --- | --- | --- |
| RF1 | Cadastro de aluno | Alta |   |
| RF2 | Cadastro de professor | Alta |   |
| RF3 | Cadastro de cursos | Alta | RF2 |
| RF4 | Realização de provas | Média | RF1, RF2, RF3 |
| RF5 | Controle de notas | Média | RF1, RF3 |
| RF6 | Envio de documentos | Baixa | RF2, RF3 |
| RF7 | Exclusão de cadastro de aluno | Baixa | RF1 |
| RF8 | Exclusão de cadastro de professor | Baixa | RF2 |
| RF9 | Exclusão de cadastro de cursos | Baixa | RF3 |
| RF10 | Alteração de plano de assinatura | Média | RF1 |



**Regras de Negocio**

| **Identificador** | **Descrição** | **Prioridade** | **Depende de** |
| --- | --- | --- | --- |
| RN1 | Alunos não tem limite de cursos | Média |   |
| RN2 | Para ser aprovado na prova precisa tirar uma nota maior que 70% | Média |   |
| RN3 | Um professor pode também ser aluno em outro curso. | Média |   |
| RN4 | Para realizar a prova o aluno precisa ter realizado 90% do curso | Alta |   |
| RN5 | O aluno pode realizar a prova no máximo 3 vezes. | Baixa |   |



**Requisitos Não Funcionais**

| **Identificador** | **Descrição** | **Categoria** | **Escopo** | **Prioridade** | **Depende de** |
| --- | --- | --- | --- | --- | --- |
| RNF1 | O sistema deve ser executado em navegadores Chrome 69 superior, Firefox 63 ou superior e possuir o flash player instalado. | Restrição de Software |   | Alta |   |
| RNF2 | Pelo menos 2gb de RAM, se utilizar o Google Chrome recomenda-se 4gb | Restrição de Hardware |   | Alta |   |
| RNF3 | Layout ser responsivo para visualização em telas de diferentes tamanhos | Restrição de usabilidade |   | Baixa |   |
| RNF4 | Sistema deve emitir o certificado do aluno após a prova em até 5 segundos. | Critério de desempenho |   | Baixa |   |
| RNF5 | Usuários não conseguem ver histórico de assinatura de outr os usuários | Restrições de integridade e segurança |   | Alta |   |