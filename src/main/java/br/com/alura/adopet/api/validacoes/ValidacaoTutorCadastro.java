package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastroTutorDto;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorCadastro {

    @Autowired
    private TutorRepository repository;

    public void verificarCadastro(CadastroTutorDto dto){

        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (telefoneJaCadastrado ) {
            throw new IllegalArgumentException("Telefone ja cadastrado!");
        }

        if (emailJaCadastrado){
            throw  new IllegalArgumentException("Email ja cadastrado!");
        }

    }
}
