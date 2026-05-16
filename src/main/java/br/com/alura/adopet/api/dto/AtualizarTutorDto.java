package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizarTutorDto(

        @NotNull(message = "O ID é obrigatório para atualização")
        Long id,

        @NotBlank String nome,

        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}", message = "Formato de telefone inválido")
        String telefone,

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email) {
}
