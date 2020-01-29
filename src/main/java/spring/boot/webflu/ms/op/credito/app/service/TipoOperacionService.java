package spring.boot.webflu.ms.op.credito.app.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.TypeOperation;

public interface TipoOperacionService {
	
	Flux<TypeOperation> findAllTipoproducto();
	Mono<TypeOperation> findByIdTipoProducto(String id);
	Mono<TypeOperation> saveTipoProducto(TypeOperation tipoProducto);
	Mono<Void> deleteTipo(TypeOperation tipoProducto);
	
}
