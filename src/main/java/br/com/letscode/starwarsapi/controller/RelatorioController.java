package br.com.letscode.starwarsapi.controller;

import br.com.letscode.starwarsapi.dto.RelatorioResponseDto;
import br.com.letscode.starwarsapi.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/relatorio")
public class RelatorioController {

    @Autowired
    RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<RelatorioResponseDto> gerarRelatorio() {
        RelatorioResponseDto relatorioResponseDto = relatorioService.gerarRelatorio();
        return ResponseEntity.ok(relatorioResponseDto);
    }

}
