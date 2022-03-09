package br.com.letscode.starwarsapi.controller;

import br.com.letscode.starwarsapi.service.RebeldeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rebeldes")
public class RebeldeController {

    @Autowired
    private RebeldeService rebeldeService;

    @GetMapping
    public ResponseEntity criarRebelde() {
        rebeldeService.criarRebelde();
        return ResponseEntity.ok().build();
    }

}
