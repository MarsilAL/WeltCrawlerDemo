FROM openjdk:13-alpine
EXPOSE 5432
WORKDIR /
COPY entrypoint.sh entrypoint.sh
ADD build/libs/weltcrawlerdemo.jar weltcrawlerdemo.jar
ENTRYPOINT ["java -jar weltcrawlerdemo.jar"]