package gdf.exception;

public class StockInsuficienteException extends RuntimeException {

    private final String nombreProducto;
    private final int stockDisponible;
    private final int cantidadSolicitada;

    public StockInsuficienteException(String message, String nombreProducto, int stockDisponible, int cantidadSolicitada) {
        super(message);
        this.nombreProducto = nombreProducto;
        this.stockDisponible = stockDisponible;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public int getCantidadSolicitada() {
        return cantidadSolicitada;
    }
}