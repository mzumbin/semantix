
#### Para rodar os testes`sbt test`
#### Para rodar a aplicação `sbt run nome do arquivo`
1. Qual​ ​o​ ​objetivo​ ​do​ ​comando​ ​​cache​ ​​em​ ​Spark?

    Cache  é uma optimizacao para o reuso de Spark de computaçoes em estagios subb sequentes.  
2. O​ ​mesmo​ ​código​ ​implementado​ ​em​ ​Spark​ ​é​ ​normalmente​ ​mais​ ​rápido​ ​que​ ​a​ ​implementação​ ​equivalente​ ​em MapReduce.​ ​Por​ ​quê?
    
    1. Uma das principais limitações do MapReduce é que ele mantém o conjunto de dados completo no HDFS após
       executando cada trabalho. Isso é muito caro, porque incorre em três vezes (para replicação) o tamanho do conjunto de dados
       em E / S de disco e uma quantidade semelhante de E / S de rede. O Spark tem um DAG de um pipeline de operações,
       podendo reusar a saída de uma operação para ser alimentada em outra operação, o Spark passa os dados diretamente sem gravar no armazenamento persistente.
       
    2. E quanto aos trabalhos do Spark que se resumem a um único trabalho do MapReduce?
       Em muitos casos, eles também são executados mais rapidamente no Spark do que no MapReduce. A principal vantagem
       Spark tem aqui é que ele pode iniciar tarefas muito mais rápido.
       O MapReduce inicia uma nova JVM para cada tarefa, o que pode levar segundos 
       . O Spark mantém um executor executando JVM em cada nó,
        então, iniciar uma tarefa é simplesmente uma questão de fazer um RPC e passar um Runnable para um pool de threads,
        que leva em um único dígito de milissegundos.
    
3. Qual​ ​é​ ​a​ ​função​ ​do​ ​​SparkContext​?       
   
   Um contexto Spark é essencialmente um cliente do ambiente de execução do Spark e atua como o mestre do seu aplicativo Spark
    
4. Explique​ ​com​ ​suas​ ​palavras​ ​​ ​o​ ​que​ ​é​ ​​Resilient​ ​Distributed​ ​Datasets​​ ​(RDD)
   
      Os conjuntos de dados distribuídos resilientes (RDD) são uma estrutura de dados fundamental do Spark. É uma coleção distribuída imutável de objetos com operações inspiridas pela coleçoes do Scala . 
      Cada conjunto de dados no RDD é dividido em partições lógicas, que podem ser calculadas em nós diferentes do cluster. Os RDDs podem conter qualquer tipo de objetos Python, Java ou Scala, 
      incluindo classes definidas pelo usuário.  
   
5. GroupByKey​ ​​é​ ​menos​ ​eficiente​ ​que​ ​​reduceByKey​ ​​em​ ​grandes​ ​dataset.​ ​Por​ ​quê?
    ​​
    
    reduceByKey​ pode começar a agrupar e reudir numa mesma maquina ou worker resultando em menor troca de dados entre os estágios
 6. Explique o código Scala?
    é o hello word do spark, Count Words  
     
    
