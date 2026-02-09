# BankMG API - Solução para Cálculo de Patrimônio

Este repositório contém minha implementação para o desafio técnico da BankMG. Foquei em criar uma solução extensível e que tratasse corretamente as complexidades de estruturas societárias.

### Como a solução foi estruturada

Para modelar o domínio, utilizei uma interface `Participante` que é implementada por `PessoaFisica` e `PessoaJuridica`. Essa abordagem me permitiu usar polimorfismo no serviço de cálculo, tratando qualquer nó da árvore societária de forma uniforme.

- **PessoaJuridica**: Pode ter tanto bens próprios quanto uma lista de sócios (que podem ser outras PJs ou PFs).
- **PessoaFisica**: Representa os nós folha que possuem bens, mas não possuem sócios.

### O desafio do cálculo (Ciclos e Duplicidade)

O ponto central do desafio era garantir que o cálculo de patrimônio não entrasse em loop infinito (caso de participações cruzadas) e não contasse o mesmo bem duas vezes (caso o bem esteja listado em mais de um participante).

Implementei o `CalculadoraPatrimonioService` utilizando o algoritmo de **Busca em Largura (BFS)** com uma fila (`Deque`). Para garantir a consistência, utilizei dois controles de estado:
1. Um conjunto de documentos já visitados para evitar loops em ciclos societários.
2. Um conjunto de IDs de bens já contabilizados para evitar a duplicidade de valores no total.

### Validação e Integridade

Criei um utilitário `DocumentoValidator` para garantir que apenas CPFs e CNPJs válidos entrem no sistema. Essa validação acontece diretamente no construtor das classes de domínio, seguindo o princípio de que um objeto não deve existir em um estado inválido.

### Testes realizados

Foquei os testes unitários nos cenários mais críticos:
- Cálculo simples de patrimônio.
- Navegação em múltiplos níveis de sócios.
- Garantia de que o mesmo bem não é somado duas vezes.
- Proteção contra recursão infinita em estruturas circulares.

### Stack técnica
- Java 17
- Spring Boot
- JUnit 5
- Maven

Qualquer dúvida sobre a implementação, estou à disposição para conversar!