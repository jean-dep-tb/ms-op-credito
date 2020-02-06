package spring.boot.webflu.ms.op.credito.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CuentaCredito {
	private String id;
	private String numeroCuenta;
	private String dni;
	private TipoCuentaCredito tipoProducto;
	private String fecha_afiliacion;
	private String fecha_caducidad;
	private Double credito;
	private Double saldo;
	private Double consumo;
	private String usuario;
	private String clave;
	private String codigoBanco;//codigo_bancario

	
	//private tipoProducto tipoCliente;
}










