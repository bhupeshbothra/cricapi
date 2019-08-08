FROM java:8-jre
WORKDIR usr/src
ENV MONGO_DATABASENAME=muzixdb
ENV MONGO_HOST=localhost
ENV MONGO_PORT=27017
ADD ./target/usertrackservicenew-0.0.1-SNAPSHOT.jar /usr/src/usertrackservicenew-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","usertrackservicenew-0.0.1-SNAPSHOT.jar"]
