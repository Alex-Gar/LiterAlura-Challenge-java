package utils;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatosImpl implements IConvierteDatos {
    private ObjectMapper objectMapper = new ObjectMapper();

    // @Override
    // public <T> T convierteDatos(String json, Class<T> clase) {
    // try {
    // String jsonResults = objectMapper.readTree(json).get("results").toString();
    // System.out.println(jsonResults);

    // return objectMapper.readValue(jsonResults, clase);
    // } catch (JsonProcessingException e) {
    // throw new RuntimeException(e);
    // }
    // }

    @Override
    public <T> List<T> convierteDatos(String json, Class<T> clase) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsNode = rootNode.get("results"); 

            return objectMapper.readValue(resultsNode.toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clase));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar el JSON", e);
        }
    }

}
