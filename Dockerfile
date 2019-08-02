FROM adoptopenjdk/openjdk11:alpine

RUN apk update && apk add fontconfig ghostscript-fonts && rm -rf /var/cache/apk/*

VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.flowableboot.FlowableBootApplication"]
