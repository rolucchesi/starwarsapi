package br.com.letscode.starwarsapi.rebelde;

import br.com.letscode.starwarsapi.TestUtils;
import br.com.letscode.starwarsapi.model.Rebelde;
import br.com.letscode.starwarsapi.repository.RebeldesRepository;
import br.com.letscode.starwarsapi.service.RebeldeService;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RebeldeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RebeldesRepository rebeldesRepository;

    @Autowired
    private RebeldeService rebeldeService;

    public static String getJsonAsString(final String path) throws IOException {
        final URL resource = RebeldeControllerTest.class.getResource("/jsons/" + path);
        return IOUtils.toString(resource, "UTF-8");
    }

    @Test
    @DisplayName("Deve criar um novo rebelde")
    void deveCriarUmNovoRebelde() throws Exception {
        final String feedPayload = getJsonAsString("cadastrarRebelde.json");

        mockMvc.perform(post("/v1/rebeldes/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedPayload))
                .andExpect(status().is(201))
                .andExpect(jsonPath("$.nome", equalTo("Joao")))
                .andExpect(jsonPath("$.idade", equalTo(35)))
                .andExpect(jsonPath("$.genero", equalTo("Masculino")))
                .andExpect(jsonPath("$.localizacao.latitude", equalTo("123")))
                .andExpect(jsonPath("$.localizacao.longitude", equalTo("456")))
                .andExpect(jsonPath("$.localizacao.nome", equalTo("galaxia")))
                .andExpect(jsonPath("$.inventario.itens[0].nome", equalTo("Arma")))
                .andExpect(jsonPath("$.inventario.itens[0].quantidade", equalTo(5)))
                .andExpect(jsonPath("$.inventario.itens[1].nome", equalTo("Munição")))
                .andExpect(jsonPath("$.inventario.itens[1].quantidade", equalTo(2)));
    }

    @Test
    @DisplayName("Não deve criar um novo rebelde com nome vazio")
    void naoDeveCriarUmRebeldeSemNome() throws Exception {
        final String feedPayload = getJsonAsString("cadastrarRebeldeSemNome.json");

        mockMvc.perform(post("/v1/rebeldes/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedPayload))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$", equalTo("Erro: Não é possível criar um rebelde sem nome")));
    }

    @Test
    @DisplayName("Não deve criar um novo rebelde com inventário vazio")
    void naoDeveCriarUmRebeldeComInventarioVazio() throws Exception {
        final String feedPayload = getJsonAsString("cadastrarRebeldeSemInventario.json");

        mockMvc.perform(post("/v1/rebeldes/cadastrar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedPayload))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$", equalTo("Erro: Não é possível criar um rebelde com inventário vazio")));
    }

    @Test
    @DisplayName("Deve listar todos os rebeldes")
    void deveListarTodosOsRebeldes() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        final Rebelde rebelde2 = TestUtils.criarRebelde2();

        rebeldesRepository.saveAll(Arrays.asList(rebelde1, rebelde2));

        mockMvc.perform(get("/v1/rebeldes"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nome", equalTo("João")))
                .andExpect(jsonPath("$[1].nome", equalTo("Maria")))
                .andReturn();
    }

    @Test
    @DisplayName("Deve atualizar localização do rebelde")
    void deveAtualizarLocalizacaoDoRebelde() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        rebeldesRepository.save(rebelde1);

        final String feedPayload = getJsonAsString("localizacao.json");

        mockMvc.perform(put("/v1/rebeldes/localizacao/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(feedPayload))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.localizacao.latitude", equalTo("1234")))
                .andExpect(jsonPath("$.localizacao.longitude", equalTo("5678")))
                .andExpect(jsonPath("$.localizacao.nome", equalTo("Lua")));
    }

    @Test
    @DisplayName("Deve conseguir acusar rebelde de traidor")
    void deveConseguirAcusarRebeldeDeTraidor() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        rebeldesRepository.save(rebelde1);

        mockMvc.perform(patch("/v1/rebeldes/acusar/1"))
                .andExpect(status().is(200));

        Rebelde rebeldeAtualizado = rebeldesRepository.findById("1").get();

        assertThat(rebeldeAtualizado.getContagemTraidor()).isEqualTo(3);
        assertThat(rebeldeAtualizado.getTraidor()).isEqualTo(Boolean.TRUE);
    }

    @Test
    @DisplayName("Deve realizar negociação de itens")
    void deveRealizarNegociacaoDeItens() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        final Rebelde rebelde2 = TestUtils.criarRebelde2();

        rebeldesRepository.saveAll(Arrays.asList(rebelde1, rebelde2));

        final String feedPayload = getJsonAsString("negociarItem.json");

        mockMvc.perform(post("/v1/rebeldes/negociar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedPayload))
                .andExpect(status().is(200));

        final List<Rebelde> rebeldes = rebeldeService.listarRebeldes();

        assertThat(rebeldes.get(0).getInventario().getQuantidadeItemEspecifico("Munição")).isEqualTo(3);
        assertThat(rebeldes.get(1).getInventario().getQuantidadeItemEspecifico("Munição")).isEqualTo(6);
        assertThat(rebeldes.get(0).getInventario().getQuantidadeItemEspecifico("Comida")).isEqualTo(7);
        assertThat(rebeldes.get(1).getInventario().getQuantidadeItemEspecifico("Comida")).isEqualTo(2);
    }

    @Test
    @DisplayName("Não deve realizar negociação de itens com quantidade total de pontos diferentes")
    void naoDeveRealizarNegociacaoComQuantidadeTotalDePontosDiferente() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        final Rebelde rebelde2 = TestUtils.criarRebelde2();

        rebeldesRepository.saveAll(Arrays.asList(rebelde1, rebelde2));

        final String feedPayload = getJsonAsString("negociarItemQuantidadeErrada.json");

        mockMvc.perform(post("/v1/rebeldes/negociar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedPayload))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$", equalTo("Erro: A quantidade de pontos para a troca dos itens não é igual.")));
    }

    @Test
    @DisplayName("Não deve realizar negociação de itens se o rebelde não possuir itens desejados")
    void naoDeveRealizarNegociacaoSeORebeldeNaoPossuiItensDesejados() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        final Rebelde rebelde2 = TestUtils.criarRebelde2();

        rebeldesRepository.saveAll(Arrays.asList(rebelde1, rebelde2));

        final String feedPayload = getJsonAsString("negociarItemNaoPossuiItem.json");

        mockMvc.perform(post("/v1/rebeldes/negociar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(feedPayload))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$", equalTo("Erro: As informações de itens para troca foram inválidas.")));
    }

    @Test
    @DisplayName("Não deve realizar negociação de itens com traidores")
    void naoDeveRealizarNegociacaoDeItensComTraidores() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        final Rebelde rebelde2 = TestUtils.criarRebeldeTraidor();

        rebeldesRepository.saveAll(Arrays.asList(rebelde1, rebelde2));

        final String feedPayload = getJsonAsString("negociarItemTraidor.json");

        mockMvc.perform(post("/v1/rebeldes/negociar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(feedPayload))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$", equalTo("Erro: Não é possível fazer negociação com rebeldes traidores")));
    }

    @Test
    @DisplayName("Não deve realizar negociação de itens com rebelde inexistente")
    void naoDeveRealizarNegociacaoDeItensComRebeldeInexistente() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        final Rebelde rebelde2 = TestUtils.criarRebelde3();

        rebeldesRepository.saveAll(Arrays.asList(rebelde1, rebelde2));

        final String feedPayload = getJsonAsString("negociarItem.json");

        mockMvc.perform(post("/v1/rebeldes/negociar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(feedPayload))
                .andExpect(status().is(400))
                .andExpect(jsonPath("$", equalTo("Erro: Não existe rebelde com este id: 2")));
    }

    @AfterEach
    void resetarBancoDeDados() {
        rebeldesRepository.deleteAll();
    }

}
