package com.example.figuras;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class FiguraFactory {

    public Figura crearFigura(String nombre, double... parametros) {
        try {
            Class<?> clase = Class.forName(
                    "com.example.figuras." + nombre.trim()
            );

            if (!Figura.class.isAssignableFrom(clase)) {
                throw new IllegalArgumentException(
                        "La clase debe implementar Figura."
                );
            }

            Class<?>[] tipos = new Class<?>[parametros.length];
            Arrays.fill(tipos, double.class);

            Constructor<?> constructor = clase.getConstructor(tipos);

            Object[] argumentos = new Object[parametros.length];

            for (int i = 0; i < parametros.length; i++) {
                argumentos[i] = parametros[i];
            }

            return (Figura) constructor.newInstance(argumentos);

        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(
                    "No existe la figura: " + nombre
            );
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(
                    "La cantidad de medidas no corresponde a esa figura."
            );
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(
                    e.getCause().getMessage(), e.getCause()
            );
        } catch (ReflectiveOperationException e) {
            throw new IllegalArgumentException(
                    "No se pudo crear la figura.", e
            );
        }
    }
}
