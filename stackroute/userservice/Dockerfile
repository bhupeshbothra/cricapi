FROM java:8-jre
WORKDIR /usr/src
ENV  MYSQL_DATABASENAME=cricUserDb
ENV  MYSQL_URL=jdbc:mysql://127.0.0.1:3306/cricUserDb?createDatabaseIfnotExist=true
ENV  MYSQL_USERNAME=root
ENV  MYSQL_PASSWORD=root
ADD ./target/userservice-1.0.jar /usr/src/userservice-1.0.jar
ENTRYPOINT ["java","-jar","userservice-1.0.jar"]
