## 1 - Por que o UDP existe? Não teria sido suficiente deixar que os processos dos usuários enviassem pacotes IP brutos?
- O UDP torna o trafego na rede mais rápido e sem necessidade de reenvio caso algum pacote se perca, caso o envio fosse apenas de pacotes IP brutos, os pacotes seriam entregues ao IP correto, mas não saberia qual a porta correta, já os pacotes UDP contêm uma porta de destino para ser entregue ao processo correto

## 2 - Tanto o UDP quanto o TCP empregam números de portas para identificar a entidade de destino ao entregarem uma mensagem. Forneça duas razões pelas quais esses protocolos criaram uma nova ID abstrata (números de portas), em vez de usarem IDs de processos, que já existiam quando esses protocolos foram projetados.
- Como cada ID de processo varia de acordo com o Sistema Operacional, cada protocolo precisaria usar um Sistema Operacional específico, mas como os protocolos não dependem de nenhhum Sistema Operacional, ele cria uma ID abstrata que pode ser usada em qualquer sistema.

## 3 - A fragmentação e a remontagem de datagramas são tratadas pelo IP e são invisíveis para o TCP. Isso quer dizer que o TCP não tem de se preocupar com a chegada de dados na ordem errada?
- O TCP precisa tratar os dados para ser entregues na ordem correta

## 4 - Uma aplicação precisa transmitir 1 Gbyte para outra aplicação na rede. Para que a transmissão ocorra, os bytes a serem transmitidos precisam ser divididos em pacotes
### X) na camada de transporte, pelo TCP ou UDP.
### b) na camada de transporte, quando a transmissão utilizar TCP.
### c) na camada de rede, de acordo com o número do pacote especificado pelo TCP.
### d) na camada de rede, de acordo com o número do pacote especificado pelo TCP ou UDP.
### e) pela própria aplicação, causando ou não reparticionamento dos pacotes pela camada de transporte.
### f) pela própria aplicação, causando ou não reparticionamento dos pacotes pela camada de rede.

## 5 - O controle de erros é um mecanismo na camada de transporte que permite garantir
### a) que todas as mensagens chegarão ao processo destino exatamente como transmitidos pelo processo origem.
### X) que todas as mensagens chegarão à estação destino exatamente com transmitidos pela estação origem.
### c) que todas as mensagens chegarão em ordem ao destino.
### d) que erros nos enlaces de comunicação serão corrigidos tão logo possível.

## 6 - Na camada de transporte, a multiplexação é o mecanismo que define
### a) como o protocolo de transporte identifica a aplicação destino ou origem de cada pacote recebido/enviado.
### b) como as portas serão definidas pela cada processo comunicante.
### c) como duas ou mais conversas simultâneas entre duas estações serão identificadas e distinguidas.
### x) como duas ou mais conversas simultâneas entre duas ou mais estações serão identificadas e distinguidas.
### e) como duas ou mais conversas simultâneas entre processos serão identificadas e distinguidas.


## 7 - Sobre o UDP, assinate a alternativa FALSA.
### a) não há correção de erros.
### b) não há estabelecimento de conexão.
### X) erros são sempre detectados, mas nem sempre corrigidos.
### d) os pacotes não são numerados (sequenciados).
### e) o cabeçalho do pacote é menor do que no protocolo TCP

## 8 - Assinale o campo inexistente no pacote UDP:
### X) IP de origem e destino
### b) porta de origem e destino
### c) tamanho do pacote
### d) checksum
### e) dados da camada de aplicação

## 9 - O uso de reconhecimentos (ACKs) como técnica de detecção de erros, permite à camada de transporte identificar se
### a) pacotes transmitidos foram recebidos pelo processo destinatário.
### X) pacotes transmitidos chegaram sem erros no processo destinatário.
### c) pacotes recebidos foram, de fato, transmitidos pelo processo origem.
### d) não houve congestionamento do caminho percorrido pelo pacote.

## 10 - Se a verificação do checksum for desabilitada no TCP então
### a) não haverá garantia para os processos (camada de aplicação) que as mensagen chegaram em ordem.
### b) não haverá garantia para os processos (camada de aplicação) que os bytes recebidos correspondem àqueles transmitidos.
### X) não haverá garantia para os processos (camada de aplicação) que os pacotes perdidos foram retransmitidos.
### d) não haverá garantia para os processos (camada de transporte) que os bytes recebidos correspondem àqueles transmitidos.
### e) não haverá garantia para os processos (camada de transporte) que as mensagens chegaram em ordem.
### f) não haverá garantia para os processos (camada de transporte) que os pacotes perdidos foram retransmitidos.