# Jogo de Tabuleiro

Projeto em Java e JavaFX, que implementa um jogo de tabuleiro com múltiplos jogadores, casas especiais e eventos que influenciam o andamento da partida.

O jogo possui uma interface gráfica para cadastro de jogadores, visualização do tabuleiro, movimentação dos participantes e acompanhamento das rodadas até a definição do vencedor.

## Tecnologias utilizadas
- **Java 21**
- **JavaFX**
- **Apache Maven** 
- **JavaFX Controls**
- **JavaFX FXML**

## Funcionalidades
- Cadastro de jogadores com nome e cor.
- Seleção automática de tipos de jogadores.
- Partidas com múltiplos participantes.
- Tabuleiro com 40 casas.
- Lançamento de dados e movimentação dos jogadores.
- Aplicação de efeitos de casas especiais.
- Controle de rodadas e turnos.
- Identificação do vencedor.
- Interface gráfica com menu, cadastro de jogadores, regras e tela da partida.
- Modo de depuração disponível na configuração do projeto.

## Tipos de jogadores
O projeto implementa três tipos de jogadores:
- **Jogador normal:** participante com comportamento padrão.
- **Jogador sortudo:** possui características específicas que influenciam os efeitos do jogo.
- **Jogador azarado:** possui características próprias que podem alterar os resultados de determinados eventos.

O tipo inicial de cada jogador é sorteado durante o cadastro.

## Casas do tabuleiro
O tabuleiro possui diferentes tipos de casas, cada uma com um efeito específico.

| Tipo de casa | Efeito |
|---|---|
| Casa neutra | Não aplica efeitos especiais. |
| Casa da Sorte | Avança três casas, conforme as regras do tipo de jogador. |
| Casa Pula Rodada | Faz o jogador perder a próxima rodada. |
| Casa Surpresa | Sorteia um novo tipo para o jogador. |
| Casa da Discórdia | Permite enviar um adversário para a casa inicial. |
| Casa Mágica | Troca a posição do jogador com quem está na última posição. |

## Regras gerais
- A partida exige pelo menos dois jogadores.
- É necessário haver pelo menos dois tipos distintos de jogadores.
- O limite de participantes é de seis jogadores.
- Cada participante possui uma cor para identificação visual no tabuleiro.
- Os efeitos das casas especiais são aplicados durante a partida.
- O jogo termina quando um participante alcança a última casa do tabuleiro.

## Como executar o jogo

```bash
git clone https://github.com/yhsww/jogo-tabuleiro.git
cd jogo-tabuleiro
```

Na pasta que contém o `pom.xml`, execute:

```bash
mvn clean javafx:run
```

O Maven compilará o projeto, resolverá as dependências e iniciará a aplicação pela classe principal `src.Main`.
Para compilar sem iniciar a interface gráfica:

```bash
mvn clean compile
```

## Organização do código

### Pacote `src.models`
Contém as classes que representam os elementos e as regras do jogo:

- `Jogo.java`: controla jogadores, rodadas, turnos e encerramento da partida.
- `Tabuleiro.java`: representa e organiza as casas do tabuleiro.
- `Jogador.java`: classe-base dos participantes.
- `JogadorNormal.java`, `JogadorSortudo.java` e `JogadorAzarado.java`: implementam os tipos de jogadores.
- `Casa.java`: classe-base das casas.
- `CasaDaSorte.java`, `CasaDaDiscordia.java`, `CasaMagica.java`, `CasaNeutra.java`, `CasaPularRodada.java` e `CasaSurpresa.java`: implementam os diferentes efeitos das casas.
- `Cor.java`: representa as cores disponíveis para os jogadores.

### Pacote `src.view`
Contém as telas e os componentes da interface gráfica:

- `TelaMenu.java`: menu principal.
- `Telacadastrojogadores.java`: cadastro dos participantes.
- `Telajogo.java`: interface da partida e representação visual do tabuleiro.
- `Telaregras.java`: apresentação das regras.
- `Navegadordetelas.java`: navegação entre telas.
- `Configuracoes.java`: configurações relacionadas ao modo de depuração.
- `CoresUI.java`: suporte à apresentação visual das cores.
- `Tela.java`: interface comum para as telas.

### Pacote `src.util`
Contém funções auxiliares utilizadas pela aplicação.


Projeto acadêmico desenvolvido em Java com JavaFX e Maven.
