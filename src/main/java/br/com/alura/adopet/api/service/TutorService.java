package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizarTutorDto;
import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoTutorCadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    @Autowired
    private ValidacaoTutorCadastro validacaoTutorCadastro;

    public  void cadastarTutor(CadastroTutorDto dto){
        validacaoTutorCadastro.verificarCadastro(dto);
        Tutor tutor = new Tutor(dto.nome(), dto.telefone(), dto.email());
        repository.save(tutor);
    }

    public void atualizarCadastroTutor(AtualizarTutorDto dto){
        try {
            Tutor tutor = repository.getReferenceById(dto.id());
            tutor.setEmail(dto.email());
            tutor.setNome(dto.nome());
            tutor.setTelefone(dto.telefone());

            repository.save(tutor);
        }catch (jakarta.persistence.EntityNotFoundException e){
            throw new IllegalArgumentException("Erro: O ID do tutor informado não existe no banco!");
        }

    }

}
