FROM amazoncorretto:17-alpine-jdk
# debug
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8081
COPY build/libs/*.war spring-java-checkBracketSber.war
ENTRYPOINT ["java","-jar","/spring-java-checkBracketSber.war"]