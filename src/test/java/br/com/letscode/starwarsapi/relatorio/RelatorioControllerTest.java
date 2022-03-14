package br.com.letscode.starwarsapi.relatorio;

import br.com.letscode.starwarsapi.TestUtils;
import br.com.letscode.starwarsapi.model.Rebelde;
import br.com.letscode.starwarsapi.repository.RebeldesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RelatorioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RebeldesRepository rebeldesRepository;

    @Test
    @DisplayName("Deve gerar relatório vazio corretamente quando não houver rebeldes cadastrados")
    void deveGerarRelatorioVazioCorretamente() throws Exception {
        mockMvc.perform(get("/v1/relatorio"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.porcentagemTraidores", equalTo(0.0)))
                .andExpect(jsonPath("$.porcentagemRebeldes", equalTo(0.0)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaArmas", equalTo(0.0)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaMunicoes", equalTo(0.0)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaAguas", equalTo(0.0)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaComidas", equalTo(0.0)))
                .andExpect(jsonPath("$.pontosPerdidosTraidores", equalTo(0)));
    }

    @Test
    @DisplayName("Deve gerar relatório preenchido corretamente")
    void deveGerarRelatorioPreenchidoCorretamente() throws Exception {
        final Rebelde rebelde1 = TestUtils.criarRebelde1();
        final Rebelde rebelde2 = TestUtils.criarRebelde2();
        final Rebelde rebelde3 = TestUtils.criarRebelde3();
        final Rebelde rebelde4 = TestUtils.criarRebeldeTraidor();

        rebeldesRepository.saveAll(Arrays.asList(rebelde1, rebelde2, rebelde3, rebelde4));

        mockMvc.perform(get("/v1/relatorio"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$.porcentagemTraidores", equalTo(0.25)))
                .andExpect(jsonPath("$.porcentagemRebeldes", equalTo(0.75)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaArmas", equalTo(4.0)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaMunicoes", equalTo(5.0)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaAguas", equalTo(6.0)))
                .andExpect(jsonPath("$.quantidadeMediaRecursos.mediaComidas", equalTo(7.0)))
                .andExpect(jsonPath("$.pontosPerdidosTraidores", equalTo(80)));
    }

    @AfterEach
    void resetarBancoDeDados() {
        rebeldesRepository.deleteAll();
    }

}
