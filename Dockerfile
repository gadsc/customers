FROM gradle:jdk14 as builder

USER root

ENV APP_DIR /app
WORKDIR $APP_DIR

COPY build.gradle.kts $APP_DIR/
COPY settings.gradle.kts $APP_DIR/

RUN gradle dependencies

RUN wget "http://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic-java.zip" && \
    unzip newrelic-java.zip

RUN mkdir -p /tmp/cacerts/
RUN curl "https://s3.amazonaws.com/rds-downloads/rds-ca-2019-root.pem" -o /tmp/cacerts/rds-ca-2019-root.pem
RUN openssl x509 -outform der -in /tmp/cacerts/rds-ca-2019-root.pem -out /tmp/cacerts/rds-ca-2019-root.der

COPY . $APP_DIR

RUN gradle build -x test -x integrationTest

USER guest

# -----------------------------------------------------------------------------

FROM openjdk:11-jdk

#ARG KEYSTORE_PASSWORD

WORKDIR /app

COPY docker/init.sh .
COPY --from=builder /app/build/libs/customers-*.jar /app/
COPY --from=builder /app/newrelic/newrelic.jar /app/
COPY --from=builder /app/newrelic/newrelic.yml /app/
COPY --from=builder /tmp/cacerts/rds-ca-2019-root.der /app/

#RUN keytool -storepasswd -new $KEYSTORE_PASSWORD -storepass changeit -keystore $JAVA_HOME/lib/security/cacerts
RUN keytool -keystore $JAVA_HOME/lib/security/cacerts -import -file rds-ca-2019-root.der -alias aws-rds-ca-cert -storepass changeit -noprompt

EXPOSE 8080

ENTRYPOINT ["sh", "init.sh"]
