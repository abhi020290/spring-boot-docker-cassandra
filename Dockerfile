#Base Image
FROM openjdk:8-jdk-alpine

#Port to expose
EXPOSE 8081

#Env varibale for Working Directory
ENV CC_APP_HOME /usr/src/app

#Locate and Create JAR path
ARG CASSANDRA_CONNECTIVITY_JAR=target/spring-boot-docker-cassandra-*.jar

#Copy jar to container app directory
COPY $CASSANDRA_CONNECTIVITY_JAR $CC_APP_HOME/spring-boot-docker-cassandra.jar

#Set Working Directory
WORKDIR $CC_APP_HOME

#Start Command
ENTRYPOINT ["java", "-jar","spring-boot-docker-cassandra.jar"]