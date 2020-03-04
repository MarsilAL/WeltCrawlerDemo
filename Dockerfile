FROM openjdk:13-alpine
EXPOSE 5432
WORKDIR /
ADD build/libs/weltcrawlerdemo.jar weltcrawlerdemo.jar
ENTRYPOINT ["java -jar weltcrawlerdemo.jar"]