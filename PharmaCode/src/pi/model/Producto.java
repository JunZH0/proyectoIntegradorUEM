package pi.model;

public class Producto {
	private int idProducto;
	private String nombreProd;
	private String descrProd;
	private String tipo;
	private double precioProd;
	private int stockProd;
	
	
	public Producto(int idProducto, String nombreProd, String descrProd, String tipo, double precioProd,
			int stockProd) {
		this.idProducto = idProducto;
		this.nombreProd = nombreProd;
		this.descrProd = descrProd;
		this.tipo = tipo;
		this.precioProd = precioProd;
		this.stockProd = stockProd;
	}


	public int getIdProducto() {
		return idProducto;
	}


	public String getNombreProd() {
		return nombreProd;
	}


	public String getDescrProd() {
		return descrProd;
	}


	public String getTipo() {
		return tipo;
	}


	public double getPrecioProd() {
		return precioProd;
	}


	public int getStockProd() {
		return stockProd;
	}
	
	
	
	
	
}
