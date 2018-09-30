package br.com.raytracing.enums;

public enum ProjectionType {
    OBLIQUO(1), ORTOGRAFICO(2);

    private final int valor;

    ProjectionType(int valor) {
        this.valor = valor;
    }

    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }
}