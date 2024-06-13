# API Gateway

- Repository: [GitHub](https://github.com/polito-WAII-2024/lab5-g13)

# Environment

- `ISSUER_URI`: issuer uri of the oauth2 server. Default: `http://keycloak:8080/realms/crm`
- `CLIENT_ID`: client id of the keycloak realm
- `CLIENT_SECRET`: client secret of the keycloak realm
- `REDIRECT_URI`: redirect uri to the client. Defautl: `http://localhost:8080/login/oauth2/code/gateway-client`
- `CRM_URI`: uri of the CRM serverice. Default: `http://localhost:8081`
- `CRM_BASE`: base path for redirection. Default: `crm`
- `DOCUMENT_STORE_URI`: uri of the Document Store service. Default: `http://localhost:8082`
- `DOCUMENT_STORE_BASE`: base path for redirection. Default: `document_store`
- `COMMUNICATION_MANGER_URI`: uri of the Communication Manager: Default: `http://localhost:8083`
- `COMMUNICATION_MANAGER_BASE`: base path for redirection: Default: `communication_manager`
- `OPENAPI_BASE_URL`: base url of this service that will appear in the OpenAPI documentation.
  Default `http://localhost:${PORT}`
- `PORT`: server port. Default: `8080`
- `LOGOUT_REDIRECT_URL`: redirect url when performing a logout
