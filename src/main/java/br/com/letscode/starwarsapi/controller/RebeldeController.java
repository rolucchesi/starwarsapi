package br.com.letscode.starwarsapi.controller;

import br.com.letscode.starwarsapi.dto.CadastrarRebeldeDTO;
import br.com.letscode.starwarsapi.dto.LocalizacaoRequestDto;
import br.com.letscode.starwarsapi.dto.NegociarItensRequestDto;
import br.com.letscode.starwarsapi.model.Rebelde;
import br.com.letscode.starwarsapi.service.RebeldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/rebeldes")
public class RebeldeController {

    @Autowired
    private RebeldeService rebeldeService;

    @PostMapping("/cadastrar")
    public ResponseEntity criarRebelde(@RequestBody final CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        try {
            final Rebelde rebelde = rebeldeService.criarRebelde(cadastrarRebeldeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(rebelde);
        } catch (final RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @GetMapping
    public List<Rebelde> getListaTodosRebeldes() {
        return rebeldeService.listarRebeldes();
    }

    @PutMapping("/localizacao/{id}")
    public ResponseEntity editarLocalizacao(@PathVariable("id") final String id,
                                            @RequestBody final LocalizacaoRequestDto localizacaoRequestDto) {
        try {
            final Rebelde rebelde = rebeldeService.editarLocalizacao(id, localizacaoRequestDto);
            return ResponseEntity.ok(rebelde);
        } catch (final RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @PatchMapping("/acusar/{id}")
    public ResponseEntity<String> acusarTraidor(@PathVariable("id") final String id) {
        try {
            rebeldeService.acusarTraidor(id);
            return ResponseEntity.ok("O rebelde foi acusado de traição com sucesso.");
        } catch (final RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @PostMapping("/negociar")
    public ResponseEntity<String> negociarItens(@RequestBody final NegociarItensRequestDto negociarItensRequestDto) {
        try {
            rebeldeService.negociarItens(negociarItensRequestDto);
            return ResponseEntity.ok().body("A troca foi realizada com sucesso!");
        } catch (final RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

}
