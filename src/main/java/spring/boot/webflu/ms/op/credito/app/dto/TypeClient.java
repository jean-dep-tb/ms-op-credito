package spring.boot.webflu.ms.op.credito.app.dto;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeClient {


	private String idTipo;
	private String descripcion;
}
