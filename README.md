# comex-jpa-maven-alura
Projeto da quarta semana da trilha Boost do Back-End

### Bom dia!
- O seed dos dados está sendo realizado pelo arquivo import.sql na pasta resources.
- Para que a aplicação funcione é necessário:
  - Clonar esse repositório usando o path HTTPS (git clone pathCopiadoDoGitHub).
  - Caso não use o git, você também pode fazer o download do zip do projeto.    
  - Mudar informações no persistence.xml encontradas na pasta META-INF.
  - As informações a serem modificadas são quanto ao nome do banco de dados, username e password do seu banco de dados.
  - Pode ser necessário, incialmente, criar um banco de dados em seu SGBD para que o seed funcione.
  - Pode ocorrer problemas por contado do seed dos dados em cada módulo.
  - Portanto, pode ser necessário deletar o database: DROP DATABASE comex;

    
 ### Informações para modificar:
  name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/nomeDoBanco?createDatabaseIfNotExist=true"
  name="javax.persistence.jdbc.user" value="SeuUserNameDoBancoDeDados"
  name="javax.persistence.jdbc.password" value="SuaSenhaDoBancoDeDados"
