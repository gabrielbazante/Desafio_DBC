<h1 align="center">Desafio DBC - API Voting </h1>
<p align="center">Solução para votação</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white"/>
</p>

## 📑 Índice
<!--ts-->
   * [🔧 Instalação](#-instalação)
   * [🚀 EndPoints](#-endpoints)
<!--te-->

## 🔧 Instalação

1. Você precisará de uma IDE ([IntelliJ](https://jetbrains.com/idea/) ou [VsCode](https://code.visualstudio.com/) ) instalado.

2. Clone o projeto:
```bash
  git clone https://github.com/gabrielbazante/Desafio_DBC
```

3. Você precisará do Cliente de um Banco de Dados ([MySQL](https://www.jetbrains.com/datagrip/download/#section=mac)) instalado. - Neste caso, sugiro utilizar o DataGrip e dentro dele instalar a extensão do MySQL -


4. Para rodar o projeto você pode utilizar o comando:
```bash
  mvn spring-boot:run
```


## 🚀 EndPoints

<p align="center">
  <h4>🔖 Agenda</h4>
    <ul>
        <li> GET /agendas -> Retorna a lista de todas as agendas</li>
        <li> GET /agendas/agenda/{id} -> Retorna a agenda por um id específico</li>
        <li> GET /agendas/agenda/title/{title} -> Retorna a agenda por um título específico</li>
        <li> POST /agendas/agenda -> Para criar uma agenda (Necessário 2 atributos: title e description)</li>
        <li> PUT /agendas/agenda/{id} -> Para atualizar uma agenda através de um id específico (Necessário 2 atributos: title e description)</li>
        <li> DELETE /agendas/agenda/{id} -> Para deletar uma agenda através de um id específico</li>
    </ul>
</p>

<p align="center">
  <h4>🙎 Voter</h4>
    <ul>
        <li> GET /voters -> Retorna a lista de todas as Voters</li>
        <li> GET /voters/voter/{cpf} -> Retorna um eleitor com base no CPF informado.</li>
        <li> POST /voters/voter -> Cria um novo eleitor.</li>
        <li> PUT /voters/voter/{id} -> Atualiza um eleitor com base no ID informado.</li>
        <li> DELETE /voters/voter/{id} -> Exclui um eleitor com base no ID informado.</li>
    </ul>
</p>

<p align="center">
  <h4>🪪 Vote</h4>
    <ul>
        <li> GET /votes -> Retorna a lista de todas as Votes</li>
        <li> GET /votes/count/{id} -> Retorna o total de votos "sim" e "não" para uma agenda específica.</li>
        <li> POST /votes/voting -> Cria um novo voto para uma agenda.</li>
    </ul>
</p>

<p align="center">
  <h4>🗃️ Session</h4>
    <ul>
        <li> GET /sessions -> Retorna a lista de todas as Sessions</li>
        <li> POST /sessions/session -> Cria uma nova sessão para uma agenda.</li>
        <li> DELETE /sessions/session/{id} -> Desabilita uma sessão específica com base no ID fornecido.</li>
    </ul>
</p>
