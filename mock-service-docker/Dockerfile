FROM adoptopenjdk/openjdk11:latest AS java11

RUN mkdir mock_service
WORKDIR /root/mock_service/

ADD target/mock-service-docker-${project.version}.jar /root/mock_service/
ADD data/mock-data.mv.db /root/mock_service/

EXPOSE 11000

ENTRYPOINT ["java", "-jar", "mock-service-docker-${project.version}.jar"]
