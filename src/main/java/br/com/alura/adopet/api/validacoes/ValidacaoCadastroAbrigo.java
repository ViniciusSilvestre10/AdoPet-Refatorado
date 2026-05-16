package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastraAbrigoDto;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoCadastroAbrigo {

    @Autowired
    private AbrigoRepository repository;

    public void cadastroAbrigo(CadastraAbrigoDto dto){
        boolean nomeJaCadastrado = repository.existsByNome(dto.nome());
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (nomeJaCadastrado ) {
            throw new IllegalArgumentException("O nome cadastrado ja existe");

        }
        if (telefoneJaCadastrado){
            throw new IllegalArgumentException("O telefone cadastrado ja existe");

        }
        if (emailJaCadastrado){
            throw new IllegalArgumentException("O Email cadastrado ja existe");
        }
    }
}
