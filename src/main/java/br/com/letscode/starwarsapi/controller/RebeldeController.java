package br.com.letscode.starwarsapi.controller;

import br.com.letscode.starwarsapi.dto.CadastrarRebeldeDTO;
import br.com.letscode.starwarsapi.model.Rebelde;
import br.com.letscode.starwarsapi.service.RebeldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {

    @Autowired
    private RebeldeService rebeldeService;

    @PostMapping
    public ResponseEntity criarRebelde(CadastrarRebeldeDTO cadastrarRebeldeDTO) {
        rebeldeService.criarRebelde(cadastrarRebeldeDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/lista")
    public List<Rebelde> getListaTodosRebeldes() {
        return rebeldeService.listarRebeldes();
    }

    @PatchMapping("/{id}")
    public ResponseEntity editarLocalizacao(@PathVariable("id") String id) {
        rebeldeService.editarLocalizacao(id);
        return ResponseEntity.ok().build();
    }


}
