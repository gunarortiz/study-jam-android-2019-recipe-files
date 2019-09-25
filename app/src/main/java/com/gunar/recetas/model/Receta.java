package com.gunar.recetas.model;

import java.io.Serializable;

public class Receta implements Serializable {
    private String titulo, ingredientes, preparacion;
    private int grafico;

    public Receta() {

    }

    public Receta(String titulo, String ingredientes, String preparacion, int grafico) {
        this.titulo = titulo;
        this.ingredientes = ingredientes;
        this.preparacion = preparacion;
        this.grafico = grafico;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String prepaaracion) {
        this.preparacion = prepaaracion;
    }

    public int getGrafico() {
        return grafico;
    }

    public void setGrafico(int grafico) {
        this.grafico = grafico;
    }
}
