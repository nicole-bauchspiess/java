- Sujeira de Leitura (Dirty Read): Quando uma transação lê dados que foram modificados por outra transação que ainda não foi concluída.
- Leitura Não Repetível (Non-Repeatable Read): Quando uma transação lê a mesma linha duas vezes e encontra dados diferentes porque outra transação alterou os dados entre as duas leituras.
- Leitura Fantasma (Phantom Read): Quando uma transação reexecuta uma consulta, retornando um conjunto de linhas que satisfazem uma condição e encontra um conjunto de linhas diferente, porque outras transações inseriram ou excluíram linhas que satisfazem a condição.


## Lock Pessimista: 
- bloqueia os dados para que ninguém mais possa alterá-los até que a transação atual seja concluída. É como segurar um livro em uma biblioteca e dizer "ninguém mais pode ler isso até que eu termine".

```java
 @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Livro> findByAutor(String autor);
```

1. PESSIMISTIC_READ: é um lock pessimista. Com ele, podemos ler dados ao mesmo tempo, mas não é possível escrever dados. Como leitura, entenda uma busca, um SELECT executado no banco de dados, como quando utilizamos algum find.
2. PESSIMISTIC_WRITE: também é um lock pessimista. Ele impede leituras e escritas concorrentes, garantindo que apenas uma transação por vez possa acessar os dados. Aqui, não podemos nem buscar um dado (leitura), nem escrever (adicionar ou atualizar registros. Para os locks PESSIMISTIC WRITE e READ, não precisamos do atributo @Version.
3. PESSIMISTIC_FORCE_INCREMENT: último tipo de lock pessimista. Muito parecido com o anterior. Porém aqui, em qualquer movimentação, ele incrementa a versão, como no OPTIMISTIC_FORCE_INCREMENT.


## Lock Otimista: 
- permite que outras transações vejam os dados, mas antes de finalizar a transação, o sistema verifica se ninguém mais fez alterações nesse meio tempo. É como se você marcasse a página do livro e, antes de terminar de ler, verificasse se ninguém virou a página enquanto você não estava olhando.

1. OPTIMISTIC: esse tipo de lock é obtido ao usar o @Version, automaticamente, conforme utilizamos em vídeo. Ele confere se uma entidade foi ou não atualizada através da versão. Se utilizarmos o READ, teremos o mesmo resultado.
2. OPTIMISTIC_FORCE_INCREMENT: tem um mecanismo muito parecido com o anterior, mas toda vez que um objeto é acessado (não necessariamente alterado), a versão é modificada. Se utilizarmos o WRITE, teremos o mesmo resultado.