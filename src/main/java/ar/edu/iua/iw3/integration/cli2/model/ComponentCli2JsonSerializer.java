package ar.edu.iua.iw3.integration.cli2.model;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashSet;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

// ...existing code...
        }
        Date expirationDate;
        try {
            expirationDate = JsonUtiles.getDate(node, "expirationDate,expiration_date".split(","), null);
            if (expirationDate == null) {
                throw new IllegalArgumentException("El campo 'expirationDate' es obligatorio y no puede estar vacío.");
            }

            r.setExpirationDate(expirationDate);
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "El campo 'expirationDate' tiene un formato inválido. Se esperaba ISO-8601.");
        }

        String[] componentsStr = JsonUtiles.getArrayComponent(node,
                "components,parts".split(","), null);

        if (componentsStr != null && componentsStr.length > 0) {
            if (r.getComponents() == null) {
                r.setComponents(new HashSet<>());
            }

            for (String comp : componentsStr) {
                comp = comp.trim();
                try {
                    r.getComponents().add(componentBusiness.load(comp));
                } catch (NotFoundException e) {
                    throw new IllegalArgumentException(
                            "El componente '" + comp + "' no existe. Solo se pueden asociar componentes existentes.");
                } catch (BusinessException e) {
                    throw new IllegalArgumentException(
                            "Error al cargar el componente '" + comp + "'.", e);
                }
            }
        }

        return r;
    }

}
