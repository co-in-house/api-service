FROM open-liberty:full-java11-openj9

COPY --chown=1001:0 server.xml /config/
COPY --chown=1001:0 app-service.war /config/app/
COPY --chown=1001:0 db2jcc4.jar /liberty/usr/shared/resources/
COPY --chown=1001:0 DigiCertGlobalRootCA.crt /liberty/usr/shared/resources/

EXPOSE 9080 9443