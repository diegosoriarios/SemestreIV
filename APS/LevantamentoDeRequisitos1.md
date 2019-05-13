**Documento de**  **Definição**  **de Requisitos**



**Projeto** : Sistema de Controle Acadêmico

**Responsáveis** : Diego Soria Rios

**1. Introdução**

Este documento apresenta os requisitos de usuário do sistema de controle acadêmico e está organizado da seguinte forma: a Seção 2 contém uma descrição do propósito do sistema; a Seção 3 apresenta uma descrição do minimundo apresentando o problema; e a Seção 4 apresenta as listas de requisitos de usuário levantados junto ao cliente.

**2. Descrição do Propósito do Sistema**

Esse sistema tem como objetivo controlar as inscrições de alunos em cursos e cadeiras, permitir o controle das notas, controlar a distribuição das turmas, salas dos professores, etc.

**3. Descrição do Minimundo**

| **Descrição do Minimundo** |
| --- |
| O sistema de controle acadêmico deve controlar o cadastro de alunos guardando o número de matrícula, nome, cpf, email, telefone e senha.Também é necessário controlar professores com código, nome, cpf, email, telefone e senha.Os cursos precisam conter o codigo, nome, turno, número de semestres e cadeirasAs cadeiras precisam conter o codigo, professor, alunos, horario e pré requisitos. |



**4. Requisitos de Usuário**

Tomando por base o contexto do sistema, foram identificados os seguintes requisitos de usuário:

**Requisitos Funcionais**

| **Identificador** | **Descrição** | **Prioridade** | **Depende de** |
| --- | --- | --- | --- |
| RF1 | Cadastro de aluno | Alta |   |
| RF2 | Cadastro de professor | Alta |   |
| RF3 | Cadastro de cadeiras | Alta | RF1, RF2 |
| RF4 | Realizar chamadas | Média | RF1, RF2, RF3 |
| RF5 | Controle de notas | Média | RF1, RF2, RF3 |
| RF6 | Envio de documentos | Baixa | RF1, RF2, RF3 |
| RF7 | Exclusão de cadastro de aluno | Média | RF1 |
| RF8 | Exclusão de cadastro de professor | Média | RF2 |
| RF9 | Realizar a média dos alunos por cadeira | Média | RF1, RF2, RF3, RF5 |
| RF10 | Gerar um relatório com a média das notas e a média de frequência dos alunos por cadeira | Baixa | RF1, RF2, RF3, RF5, RF9 |



**Regras de Negócio**

| **Identificador** | **Descrição** | **Prioridade** | **Depende de** |
| --- | --- | --- | --- |
| RN1
 | Número máximo de 50 alunos por turma | Média |   |
| RN2 | A média da instituição é de 6.0 | Alta |   |
| RN3 | O aluno precisa ter no mínimo 50% de frequência para ser aprovado | Média |   |
| RN4 | Um aluno não pode estar matriculado em duas cadeiras no mesmo horário | Alta |   |
| RN5 | O envio de arquivos deve ser de no máximo 10 Mb. | Baixa |   |



**Requisitos Não Funcionais**

| **Identificador** | **Descrição** | **Categoria** | **Escopo** | **Prioridade** | **Depende de** |
| --- | --- | --- | --- | --- | --- |
| RNF1 | O sistema deve ser executado em navegadores Chrome 69 superior, Firefox 63 ou superior | Restrições de software |   | Alta |   |
| RNF2 | Pelo menos 2gb de RAM, se utilizar o Google Chrome recomenda-se 4gb | Restrições de Hardware |   | Alta |   |
| RNF3 | Alunos tem acesso apenas ao e-mail dos professores e professores tem acesso à matrícula e e-mail dos alunos. | Restrições de integridade e segurança |   | Alta |   |
| RNF4 | O sistema deve emitir o relatório em pdf da média e frequência dos alunos em no máximo 5 segundos. | Critério de Desempenho |   | Baixa |   |
| RNF5 | Layout ser responsivo para visualização em telas de diferentes tamanhos | Restrições de usabilidade |   | Baixa |   |