package utils;

public class InputUtils {
	
	public static boolean validarString(String str) {
		  if (str == null || str.isEmpty()) {
		    return false; // A string � nula ou vazia
		  }
		  for (char c : str.toCharArray()) {
		    if (Character.isDigit(c)) {
		      return false; // A string cont�m um caractere num�rico
		    }
		  }
		  return true; // A string n�o � vazia, n�o � nula e n�o cont�m n�meros
		}
	
	public static boolean validarCPF(String cpf) {
        // Remover caracteres n�o num�ricos do CPF
        cpf = cpf.replaceAll("[^0-9]", "");
        
        // Verificar se o CPF tem 11 d�gitos
        if (cpf.length() != 11) {
            return false;
        }
        
        // Verificar se todos os d�gitos s�o iguais (CPF inv�lido)
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
        
        // Calcular o primeiro d�gito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (10 - i) * (cpf.charAt(i) - '0');
        }
        int digito1 = 11 - (soma % 11);
        if (digito1 > 9) {
            digito1 = 0;
        }
        
        // Verificar o primeiro d�gito verificador
        if (cpf.charAt(9) - '0' != digito1) {
            return false;
        }
        
        // Calcular o segundo d�gito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (11 - i) * (cpf.charAt(i) - '0');
        }
        int digito2 = 11 - (soma % 11);
        if (digito2 > 9) {
            digito2 = 0;
        }
        
        // Verificar o segundo d�gito verificador
        if (cpf.charAt(10) - '0' != digito2) {
            return false;
        }
        
        return true; // CPF v�lido
    }
	
	public static boolean validarTelefone(String telefone) {
        // Remover caracteres n�o num�ricos do telefone
        telefone = telefone.replaceAll("[^0-9]", "");
        
        // Verificar se o telefone tem 10 d�gitos num�ricos
        if (telefone.length() != 11) {
            return false;
        }
        
        // Verificar se todos os d�gitos s�o iguais (telefone inv�lido)
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
        
        return true; // Telefone v�lido
    }
}
