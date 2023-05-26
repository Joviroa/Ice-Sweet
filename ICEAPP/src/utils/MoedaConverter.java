package utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@SuppressWarnings("rawtypes")
public class MoedaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                // Remove o símbolo de moeda e quaisquer caracteres não numéricos
                String numericValue = value.replaceAll("[^\\d.,]", "");
                numericValue = numericValue.replace(",", ".");

                // Converte a string para BigDecimal
                return new BigDecimal(numericValue);
            } catch (NumberFormatException e) {
                // Lidar com erro de conversão
                throw new IllegalArgumentException("Erro ao converter valor para BigDecimal: " + value, e);
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof BigDecimal) {
            BigDecimal amount = (BigDecimal) value;

            // Formata o valor no formato monetário desejado
            NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            String currencyValue = format.format(amount);

            return currencyValue;
        }

        return null;
    }
}
