package utils;

import java.util.List;

public interface IConvierteDatos {
    <T> List<T> convierteDatos(String json, Class<T> clase);
}
