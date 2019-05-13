### Nome: Diego Soria Rios
### Discilplina: Redes de computadores
### Professor: Carlos Alexandre Silva Santos
### Data: 05/02/2019

# Redes Definidas Por Software

Rede definida por software ou SDN (Software Defined Networking) é um modelo teórico de implementação de rede onde o controle (decisões) e o plano de dados (encaminhamento de pacotes) é separado. SDN abre possibilidade de desenvolver aplicações que controlam o encaminhamento de uma rede fisica de forma mais simples de maneiras impensadas no passado. O SDN evita ter que configurar o hardware de maneira manual, utilizando uma virtualização. Os sistemas centralizados de SDN automatizam a configuração e o provisionamento de toda a malha de rede, sem serem limitados pelas restrições do hardware.

## PLANO DE DADOS
O plano de dados é responsável pelo encaminhamento de pacotes. Ele pode ser implementado atraves de um hardware comum atraves de hardware em roteadores e computadores. O encaminhamento de pacotes consiste em executar algumas operações como alterar cabeçalhos dos pacotes, descartá-los e encaminhar para alguma porta específica do equipamento.

## PLANO DE CONTROLE
O plano de controle consiste em tomar as decisões de como as operações do plano de dados serão executadas. Como uma entidade separada do plano de dados, o plano de controle, toma decisões como roteamento, firewall, priorização de pacotes. O plano de controle tem uma natureza de sistemas de tempo real.

## CONTROLADOR
No plano do controle existe um controlador, esse controlador é o responsável pela definição da rota dos pacotes. O controlador tem uma visão global da topologia de rede o que pode ser levado em conta para tomar a decisão para o melhor caminho a enviar o pacote.
A definição da rota ocorre da seguinte maneira: o controlador fornece um ambiente de programação onde podem ser desenvolvidas aplicações que decidem o próximo salto de um fluxo. Essa interface de programação provê um meio de mais alto nível para definir as funções dos elementos de rede, o que permite que mais desenvolvedores possam atuar nessa área.

Como o nome já diz o plano de controle é o plano que controla, quem decide e quem toma a decisão enquanto o plano de dados apenas encaminha e direciona pacotes decididos pelo plano de controle.

Com o SDN novos experimentos podem ser feitos na rede, novos algoritmos e aplicações podem ser criados de forma menos custosa, tanto tecnicamente quanto financeiramente.

## PROTOCOLOS
Existem alguns protocolos e iniciativas em se implementar SDN. No entanto, o mais adotado pela academia e pelo mercado é o protocolo OpenFlow, porém existem alguns outros como o BGP, NETCONF, XMPP, OVSDB e o MPLS-TP. 
Em 2008 o protocolo OpenFlow foi publicado. Ele permitiu que pesquisadores pudessem criar experimentos com novos protocolos em redes convencionais.

O OpenFlow foi criado como um padrão aberto, o que permite que todos os fabricantes de equipamentos de redes possam habilitar seus produtos a esse padrão. O protocolo consiste em uma interface de programação para o comutador. Assim, um programador pode, através de um programa, controlar a forma como um comutador executa seu encaminhamento de pacotes. De uma maneira bem clara, o protocolo OpenFlow separa o plano de dados do plano de controle, fazendo com que soluções SDN possam ser criadas e experimentadas.

## APLICAÇÕES

Como cada fabricante de switches e roteadores possuem suas próprias configurações, adicionar novos aplicativos e protocolos pode ser um processo lento e inviavel, tornando assim as redes mais vulneráveis a ataques de segurança. Como as redes de software resolvem esse problema, atravez de uma rápida configuração e a possibilidade de criar features e protocolos, o SDN se torna uma ótima opção para segurança contra ciberataques.

As principais ferramentas em cloud oferecem a capacidade de armazenar e trabalhar com cargas de trabalho e limites maiores, as organizações tem a opção de estender arquiteturas que originalmente são muito limitadas. O SDN permite que o trafego de rede que passa entre switches possa ser segmentado e permite que as cargas de trabalho possam ser protegidas e monitoradas.

Um dos maiores benefícios da SDN é sua capacidade de se estender por todo o data center. Esse tipo de agilidade integra locais distribuídos, nuvem e toda a organização. O SDN permite que o tráfego de rede crítico passe entre vários locais, independentemente do tipo de arquitetura de rede . Ao abstrair os controles críticos de rede, você permite uma movimentação mais fácil de dados entre o data center e os locais na nuvem. Como a SDN é uma forma de virtualização de rede você pode usar APIs poderosas para não apenas integrar-se a um provedor de nuvem; Você também pode controlar serviços de rede específicos.

### Referencias

- https://www.gta.ufrj.br/grad/13_1/sdn/
- https://www.hpe.com/br/pt/what-is/sdn.html
- https://www.cisco.com/c/pt_br/solutions/software-defined-networking/overview.html
- https://blog.pantuza.com/artigos/redes-definidas-por-software-software-defined-networking
- https://cio.com.br/a-importancia-das-redes-sdn-contra-ciberataques/
- https://www.datacenterknowledge.com/archives/2016/03/31/top-five-apps-and-services-that-can-benefit-from-sdn