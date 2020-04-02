FROM java:8

RUN mkdir -p /home/work

COPY ./target /home/work

ENTRYPOINT ["java","-Xms256m","-Xmx256m","-Xmn256m","-Dapollo.configService=http://10.1.16.89:8080", "-jar", "/home/work/dubbo-back-1.0-SNAPSHOT.jar"]