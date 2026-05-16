package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastarPetAbrigoDto;
import br.com.alura.adopet.api.dto.CadastraAbrigoDto;
import br.com.alura.adopet.api.dto.ListaAbrigoDto;
import br.com.alura.adopet.api.exception.AbrigoNaoEncontradoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoCadastroAbrigo;
import br.com.alura.adopet.api.validacoes.ValidacaoTutorCadastro;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private ValidacaoCadastroAbrigo cadastroAbrigo;

    public List<ListaAbrigoDto> listaAbrigo() {
        List<Abrigo> abrigos = repository.findAll();
        List<ListaAbrigoDto> listaAbrigoDto = abrigos.stream().map(ListaAbrigoDto::new).toList();
        return listaAbrigoDto;
    }

    public void cadastarAbrigo(CadastraAbrigoDto dto) {
        cadastroAbrigo.cadastroAbrigo(dto);
        Abrigo abrigo = new Abrigo(dto.nome(), dto.telefone(), dto.email());
        repository.save(abrigo);
    }

    public ResponseEntity<List<Pet>> listarPetsAbrigo(String idOuNome) {
        Optional<Abrigo> abrigoOptional;
        if (idOuNome.matches("\\d+")) {
            Long id = Long.parseLong(idOuNome);
            abrigoOptional = repository.findById(id);
        } else {
            abrigoOptional = Optional.ofNullable(repository.findByNome(idOuNome));
        }

        return abrigoOptional.map(abrigo -> ResponseEntity.ok(abrigo.getPets()))
                .orElse(ResponseEntity.notFound().build());
    }


    public void cadastraPetsAbrigo(String idOuNome, CadastarPetAbrigoDto dto) {
        Pet pet = new Pet(dto.tipo(), dto.nome(), dto.raca(), dto.idade(), dto.cor(), dto.peso());
        Optional<Abrigo> abrigoOptional;
        if (idOuNome.matches("\\d+")) {
            Long id = Long.parseLong(idOuNome);
            abrigoOptional = repository.findById(id);
        } else {
            abrigoOptional = Optional.ofNullable(repository.findByNome(idOuNome));
        }
        Abrigo abrigo = abrigoOptional.orElseThrow(() ->
                new AbrigoNaoEncontradoException("Não foi possível cadastrar o pet: Abrigo não encontrado com o termo: " + idOuNome));
        pet.setAbrigo(abrigo);
        pet.setAdotado(false);
        abrigo.getPets().add(pet);
        repository.save(abrigo);
    }


}
