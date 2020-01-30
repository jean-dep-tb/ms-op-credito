package spring.boot.webflu.ms.op.credito.app.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreditAccount {
	private String id;
	private String numero_cuenta;
	private String dni;
	private TypeCreditAccount tipoProducto;
	private String fecha_afiliacion;
	private String fecha_caducidad;
	private Double credito;
	private Double saldo;
	private Double consumo;
	private String usuario;
	private String clave;
	private String codigo_bancario;

	
	//private tipoProducto tipoCliente;
}










