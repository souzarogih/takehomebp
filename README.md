# Takehome Exercise

Este exercício é para a vaga de Software Engineer da Brasil Paralelo, e tem foco
em Backend. O objetivo é que mostre facilidade em aprender uma nova linguagem ou
habilidade nela, utilização das ferramentas, criação de testes, e organização
pessoal. Dê o seu melhor, mas não esperamos que gaste mais de 3-5 horas nesse
projeto, após se habituar com as ferramentas utilizadas.

Após ler o Briefing abaixo, escreva o código e testes para implementar a
funcionalidade solicitada em Clojure, conforme repositório. Há um código inicial
que você poderá utilizar se quiser como base em `src/takehome/core.clj`, assim
como alguns testes iniciais em `test/takehome/core_test.clj`. Há também uma
listagem de produções, que poderá copiar para seus testes, em
`resources/content-database.edn`.

Você precisará ter instalado java e as ferramentas de linha de comando de
clojure, conforme https://clojure.org/guides/getting_started. Você poderá rodar
os testes com `clj -Atest`. Utilize Git para separar os commits. Compacte o
repositório num .zip, e entregue por e-mail para konrad@brasilparalelo.com.br.

# Briefing

Na Brasil Paralelo criamos diversos tipos de mídia, como podcasts, cursos,
entrevistas, e debates. No momento, o controle de acesso a este conteúdo é feito
estritamente de acordo com a assinatura do usuário. Estamos considerando
utilizar regras mais flexíveis de acesso para permitir que estes acessem mais
tipos de conteúdo, desde que produzidos durante o período de vigência de suas
assinaturas, para que haja maior incentivo de renová-las.

As regras que testaremos são as seguintes:
- Todos os usuários terão acesso a nossas **séries**
- Usuários de tipo **patriota** terão acesso a todos os podcasts, todos os
  debates, e também a todas as entrevistas lançadas durante a validade de sua
  assinatura (12 meses)
- Usuários de tipo **premium** terão acesso a tudo que os patriotas têm, mais
  acesso a todos os cursos
- Usuários de tipo **mecenas** terão acesso a tudo que os usuários premium têm,
  mais os "relatórios mecenas" lançados durante a validade de sua assinatura (12
  meses)

## Execução deste projeto
Este projeto foi desenvolvido para ser executado na IDE, para isso será necessário executar
via REPL, para isso é necessário ter instalado a JVM.

<ol>
  <li>Instale o java</li>
  <li>Crie uma configuração de debug na IDE</li>
  <li>Clique no botão de play para iniciar a execução</li>
</ol>

## Info

O programa é executado pelo arquivo `core.clj` do projeto

- A função `(access-control)` deve ser chamada passando uma keyword como parâmetro para validar o 
acesso das mídias para este tipo de usuário.

`(services.accesscontrol/access-control :mecenas)`

- No arquivo `services.media` estão definidos os multi métodos para acessar as mídias, para usar 
estas funções, é necessário passar uma keyword referenciando o tipo de mídia desejado.

`(services.media/select-media {:media-type :series})`

- A função `get-content-database`do namespace `utils.content`deve ser utilizada quando necessário 
ler o arquivo `content-database.edn`.

## Funcionamento da aplicação

Para executar a aplicação você precisa chamar a função `(can-access?)` no arquivo `takehomebp.core` 
passando por parâmetro o nome do usuário e o tipo do usuário. 

#### Retorno de Sucesso

Um mapa com as keyword de cada conteúdo e dentro os títulos.
`{:media-podcast ({:type :podcast, :name CORINGA - Podcast Cultura Paralela #1, :released-at 2020-03-29T20:02:34.337} {:type :podcast, :name Democracia em Debate, :released-at 2020-03-29T20:02:34.340}), :media-debate ({:type :debate, :name Qual o limite do Respeito?, :released-at 2020-03-29T20:02:34.351} {:type :debate, :name O que realmente é a felicidade?, :released-at 2020-03-29T20:02:34.353}), :media-series ({:type :series, :name Congresso Brasil Paralelo, :released-at 2019-04-02T02:10:38.606} {:type :series, :name Brasil - A Última Cruzada, :released-at 2019-07-08T16:37:11.184}), :media-interview ({:type :interview, :name Congresso Brasil Paralelo - Rafael Nogueira, :released-at 2019-11-16T21:40:51.579} {:type :interview, :name Congresso Brasil Paralelo - Adriano Gianturco, :released-at 2019-11-16T21:40:51.581})}`

#### Retorno de Falha

Um mapa com um campo msg informando *Tipo de acesso não identificado* seguido do nome do usuário
`{:msg Tipo de acesso não identificado - user: Higor}`

#### Exemplo

`(println (can-access? "Higor" :patriota))`

#### Tipos dos usuários
- :patriota
- :premium
- :mecenas
- :all