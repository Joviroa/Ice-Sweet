function somenteNumeros(e) {
        var charCode = e.charCode ? e.charCode : e.keyCode;
        // charCode 8 = backspace   
        // charCode 9 = tab
        if (charCode != 8 && charCode != 9) {
            // charCode 48 equivale a 0   
            // charCode 57 equivale a 9
            if (charCode < 48 || charCode > 57) {
                return false;
            }
        }
    }

function somenteLetras(event) {
  const tecla = event.which || event.keyCode;
  const teclaPermitida = [8, 9, 13, 16, 17, 18, 20, 27, 37, 38, 39, 40, 46];

  if ((tecla < 48 || tecla > 57) && (tecla < 96 || tecla > 105) && teclaPermitida.indexOf(tecla) === -1) {
    event.preventDefault();
  }
}