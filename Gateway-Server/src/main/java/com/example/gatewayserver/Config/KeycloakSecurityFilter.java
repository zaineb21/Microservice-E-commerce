package com.example.gatewayserver.Config;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class KeycloakSecurityFilter extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return (exchange, chain) -> {
            KeycloakAuthenticationToken token = exchange.getAttribute(KeycloakAuthenticationToken.class.getName());

            if (token != null) {
                // Vérifiez si l'utilisateur a le rôle "admin"
                if (userHasAdminRole(token)) {
                    // L'utilisateur a le rôle "admin" et est autorisé à accéder à la ressource.
                    return chain.filter(exchange);
                }
            }

            // Si l'utilisateur n'a pas le rôle "admin" ou n'a pas de token Keycloak, renvoyez une réponse non autorisée.
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return Mono.empty(); // Retourne un Mono vide pour terminer le traitement.
        };
    }

    private boolean userHasAdminRole(KeycloakAuthenticationToken token) {
        // Ici, vous pouvez implémenter la logique pour vérifier si l'utilisateur a le rôle "admin".
        // Par exemple, vous pouvez vérifier les rôles dans le token.
        // Si l'utilisateur a le rôle "admin", retournez true ; sinon, retournez false.
        // Vous pouvez personnaliser cette logique en fonction de votre configuration Keycloak.

        // Exemple simple : Vérifiez si le token contient le rôle "admin".
        return token.getAccount().getRoles().contains("admin");
    }
}
