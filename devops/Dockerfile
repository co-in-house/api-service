FROM open-liberty:full-java11-openj9

COPY --chown=1001:0 server.xml /config/
COPY --chown=1001:0 app-service.war /config/app/

EXPOSE 9080 9443