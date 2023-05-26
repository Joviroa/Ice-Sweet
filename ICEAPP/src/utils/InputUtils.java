package utils;

public class InputUtils {
	
	public static boolean validarString(String str) {
		  if (str == null || str.isEmpty()) {
		    return false; // A string é nula ou vazia
		  }
		  for (char c : str.toCharArray()) {
		    if (Character.isDigit(c)) {
		      return false; // A string contém um caractere numérico
		    }
		  }
		  return true; // A string não é vazia, não é nula e não contém números
		}
	
	public static boolean validarCPF(String cpf) {
        // Remover caracteres não numéricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verificar se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verificar se todos os dígitos são iguais (CPF inválido)
        boolean todosDigitosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
            return false;
        }
        
        // Calcular o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * (cpf.charAt(i) - '0');
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        
        // Verificar o primeiro dígito verificador
        if (cpf.charAt(9) - '0' != digito1) {
            return false;
        }
        
        // Calcular o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (11 - i) * (cpf.charAt(i) - '0');
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        
        // Verificar o segundo dígito verificador
        if (cpf.charAt(10) - '0' != digito2) {
            return false;
        }
        
        return true; // CPF válido
    }
	
	public static boolean validarTelefone(String telefone) {
        // Remover caracteres não numéricos do telefone
        telefone = telefone.replaceAll("[^0-9]", "");
        
        // Verificar se o telefone tem 10 dígitos numéricos
        if (telefone.length() != 11) {
            return false;
        }
        
        // Verificar se todos os dígitos são iguais (telefone inválido)
        boolean todosDigitosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (telefone.charAt(i) != telefone.charAt(0)) {
                todosDigitosIguais = false;
                break;
            }
        }
        if (todosDigitosIguais) {
            return false;
        }
        
        return true; // Telefone válido
    }
}
