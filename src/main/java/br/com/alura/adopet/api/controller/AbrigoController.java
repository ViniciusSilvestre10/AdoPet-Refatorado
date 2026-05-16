package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.CadastarPetAbrigoDto;
import br.com.alura.adopet.api.dto.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.ListaAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {


    @Autowired
    private AbrigoService abrigoService;

    @GetMapping
    public ResponseEntity<List<ListaAbrigoDto>> listar() {
            return ResponseEntity.ok(abrigoService.listaAbrigo());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid CadastraAbrigoDto dto) {
        abrigoService.cadastarAbrigo(dto);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{idOuNome}/pets")
    public ResponseEntity<List<Pet>> listarPets(@PathVariable String idOuNome) {
        return abrigoService.listarPetsAbrigo(idOuNome);

    }

    @PostMapping("/{idOuNome}/pets")
    @Transactional
    public ResponseEntity<String> cadastrarPet(@PathVariable String idOuNome, @RequestBody @Valid CadastarPetAbrigoDto dto) {
        abrigoService.cadastraPetsAbrigo(idOuNome,dto);
        return ResponseEntity.ok("Pet cadastrado com sucesso");
    }


}


