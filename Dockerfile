FROM openjdk:17-jdk-alpine as build
WORKDIR /workspace/app

COPY projectBatch/mvnw .
COPY projectBatch/.mvn .mvn
COPY projectBatch/pom.xml .
COPY projectBatch/src src

RUN chmod +x mvnw
RUN ./mvnw install -DskipTests 
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM openjdk:17-jdk-alpine
ARG DEPENDENCY=/workspace/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","org.projectbatch.projectBatch.ProjectBatchApplication"]