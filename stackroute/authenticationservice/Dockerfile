FROM java:8-jre
WORKDIR /usr/src
ENV  MYSQL_DATABASENAME=userDb
ENV  MYSQL_URL=jdbc:mysql://127.0.0.1:3306/userDb?createDatabaseIfnotExist=true
ENV  MYSQL_USERNAME=root
ENV  MYSQL_PASSWORD=root
ADD ./target/authenticationservice-0.0.1-SNAPSHOT.jar /usr/src/authenticationservice-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","authenticationservice-0.0.1-SNAPSHOT.jar"]
