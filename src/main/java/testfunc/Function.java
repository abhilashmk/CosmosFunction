package testfunc;

import com.azure.core.credential.AzureKeyCredential;
import com.azure.cosmos.*;
import com.azure.cosmos.implementation.ConnectionPolicy;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.ServiceBusTopicTrigger;


import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class Function {
    /**
     * This function listens at endpoint "/api/HttpExample". Two ways to invoke it using "curl" command in bash:
     * 1. curl -d "HTTP Body" {your host}/api/HttpExample
     * 2. curl "{your host}/api/HttpExample?name=HTTP%20Query"
     */
    @FunctionName("HttpExample")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET, HttpMethod.POST},
                authLevel = AuthorizationLevel.ANONYMOUS)
                HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {
        context.getLogger().info("Java HTTP trigger processed a request.");
            String cosmos_status="";
            try {
            context.getLogger().info("Inside method");
            /* GatewayConnectionConfig config = new GatewayConnectionConfig();
            ConnectionPolicy connectionPolicy = new ConnectionPolicy(config);*/
            context.getLogger().info("COSMOS_ENDPOINT " + "https://abhicosmos.documents.azure.com:443/");
            context.getLogger().info("COSMOS_KEY " + "");

            CosmosClient client =
                new CosmosClientBuilder()
                    .endpoint("https://abhicosmos.documents.azure.com:443/")
                    .key("")
                    .consistencyLevel(ConsistencyLevel.EVENTUAL).buildClient();
                    context.getLogger().info("client created successfully");

            cosmos_status="Success";
            
            } 
            
            catch (Exception ex) {
            context.getLogger().info("Exception in creating client : " + ex);
            ex.printStackTrace();

            cosmos_status="Failed";

            }

            return request.createResponseBuilder(HttpStatus.OK).body("Hello Cosmos test" + cosmos_status).build();
        }
    }

