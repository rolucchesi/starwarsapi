package br.com.letscode.starwarsapi.controller;

import br.com.letscode.starwarsapi.dto.CadastrarRebeldeDTO;
import br.com.letscode.starwarsapi.dto.NegociarItensRequestDto;
import br.com.letscode.starwarsapi.model.Rebelde;
import br.com.letscode.starwarsapi.service.RebeldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rebeldes")
public class RebeldeController {

    @Autowired
    private RebeldeService rebeldeService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Rebelde> criarRebelde(@RequestBody CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        Rebelde rebelde = rebeldeService.criarRebelde(cadastrarRebeldeDTO);
        return ResponseEntity.ok(rebelde);
    }

    @GetMapping
    public List<Rebelde> getListaTodosRebeldes() {
        return rebeldeService.listarRebeldes();
    }

    @PutMapping("/localizacao/{id}")
    public ResponseEntity editarLocalizacao(@PathVariable("id") String id) {
        rebeldeService.editarLocalizacao(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/negociar")
    public ResponseEntity<String> negociarItens(@RequestBody NegociarItensRequestDto negociarItensRequestDto) {
        try {
            rebeldeService.negociarItens(negociarItensRequestDto);
            return ResponseEntity.ok().body("A troca foi realizada com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

}
