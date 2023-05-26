package utils;

import java.text.ParseException;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

@SuppressWarnings("rawtypes")
public class DataConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.isEmpty()) {
            try {
                return DataUtils.parseData(value);
            } catch (ParseException e) {
                throw new IllegalArgumentException("Formato de data inválido", e);
            }
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Date) {
            Date data = (Date) value;
            return DataUtils.formatarData(data);
        }

        return null;
    }
}



