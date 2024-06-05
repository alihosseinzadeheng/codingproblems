FROM openjdk:21-jre

#RUN  sed -i 's/SECLEVEL\=2/SECLEVEL\=1/g' /etc/ssl/openssl.cnf

RUN mkdir /opt/app
WORKDIR /opt/app

COPY --from=build target/codingproblems-*.jar codingproblems.jar
