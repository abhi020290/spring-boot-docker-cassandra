# spring-boot-cassandra


Download Apache Cassandra Cassandra
Set up CASANDRA_HOME
Install python and set up Environment variable Path

Docker commands:

    docker-compose build
    docker-compose up

Cassandra Queries:

    CREATE KEYSPACE reference_keyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 1};
    
    DESCRIBE keyspaces;
    
    USE reference_keyspace;
    
    CREATE TABLE reference_keyspace.payment_details ( payment_id text PRIMARY KEY ,transaction_id text,auth_status text );
    
Again restart the Spring boot application image so that it will able to connect with docker cassandra keyspace