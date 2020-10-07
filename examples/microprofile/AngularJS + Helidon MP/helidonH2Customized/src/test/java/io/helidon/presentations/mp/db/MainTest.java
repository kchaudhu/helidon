
package io.helidon.presentations.mp.db;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.spi.CDI;
import javax.json.JsonArray;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.helidon.microprofile.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class MainTest {

    private static Server server;
    private static String serverUrl;
    private static Client client;

    @BeforeAll
    public static void startTheServer() {
        client = ClientBuilder.newClient();
        server = Server.create().start();
        serverUrl = "http://localhost:" + server.port();
    }

    @AfterAll
    static void destroyClass() {
        CDI<Object> current = CDI.current();
        ((SeContainer) current).close();
    }


    @Test
    void testPokemon() {
        assertThat(getPokemonCount(), is(6));

        Employee pokemon = client.target(serverUrl)
                .path("employees/1")
                .request()
                .get(Employee.class);
        assertThat(pokemon.getName(), is("Kallol"));

        pokemon = client.target(serverUrl)
                .path("employees/name/Kallol")
                .request()
                .get(Employee.class);
        assertThat(pokemon.getRole(), is("Enterprise Architect"));

        Response response = client.target(serverUrl)
                .path("employees/1")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));

        Employee test = new Employee();
        test.setRole("Test Role");
        test.setId(100);
        test.setName("Test");
        response = client.target(serverUrl)
                .path("employees")
                .request()
                .post(Entity.entity(test, MediaType.APPLICATION_JSON));
        assertThat(response.getStatus(), is(204));
        assertThat(getPokemonCount(), is(7));

        response = client.target(serverUrl)
                .path("employees/100")
                .request()
                .delete();
        assertThat(response.getStatus(), is(204));
        assertThat(getPokemonCount(), is(6));
    }

    @Test
    void testHealthMetrics() {
        Response response = client.target(serverUrl)
                .path("health")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
        response = client.target(serverUrl)
                .path("metrics")
                .request()
                .get();
        assertThat(response.getStatus(), is(200));
    }

    private int getPokemonCount() {
        JsonArray pokemons = client.target(serverUrl)
                .path("employees")
                .request()
                .get(JsonArray.class);
        return pokemons.size();
    }
}
