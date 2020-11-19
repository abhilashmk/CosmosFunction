ARG JAVA_VERSION=8
# # This image additionally contains function core tools – useful when using custom extensions
# #FROM mcr.microsoft.com/azure-functions/java:3.0-java$JAVA_VERSION-core-tools AS installer-env
# FROM mcr.microsoft.com/azure-functions/java:3.0-java$JAVA_VERSION-build AS installer-env

# COPY . /src/java-function-app
# RUN cd /src/java-function-app && \
#     mkdir -p /home/site/wwwroot && \
#     #mvn clean package && \
#     #cd ./target/azure-functions/ && \
#     cd ./build/azure-functions/azure-functions-sample/ && \
#     cd $(ls -d */|head -n 1) && \
#     cp -a . /home/site/wwwroot

# This image is ssh enabled
FROM mcr.microsoft.com/azure-functions/java:3.0-java$JAVA_VERSION-appservice
# This image isn't ssh enabled
#FROM mcr.microsoft.com/azure-functions/java:3.0-java$JAVA_VERSION

ENV AzureWebJobsScriptRoot=/home/site/wwwroot \
    AzureFunctionsJobHost__Logging__Console__IsEnabled=true \
    FUNCTIONS_WORKER_JAVA_LOAD_APP_LIBS=true

COPY . /tmp
RUN cd /tmp && \
    cd ./build/azure-functions/azure-functions-sample/ && \
    #cd $(ls -d */|head -n 1) && \
    mkdir -p /home/site/wwwroot && \
    cp -a . /home/site/wwwroot

# COPY --from=installer-env ["/home/site/wwwroot", "/home/site/wwwroot"]