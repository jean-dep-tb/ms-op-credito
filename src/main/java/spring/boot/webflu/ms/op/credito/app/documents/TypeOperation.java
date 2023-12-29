package spring.boot.webflu.ms.op.credito.app.documents;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection ="tipoOperacion")

public class TypeOperation {
	
	@NotEmpty
	private String idTipo;
	@NotEmpty
	private String descripcion;
	
	public TypeOperation() {
		
	}

	public TypeOperation(String idTipo,String descripcion) {
		this.idTipo = idTipo;
		this.descripcion = descripcion;
	}
		
}
