FROM openjdk:13-alpine
WORKDIR /
COPY entrypoint.sh entrypoint.sh
ADD build/libs/weltcrawlerdemo.jar weltcrawlerdemo.jar
ENTRYPOINT ["./entrypoint.sh"]