package spring.boot.webflu.ms.op.credito.app.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.documents.TipoOperacionCredito;

public interface TipoOperacionCreditoService {
	
	Flux<TipoOperacionCredito> findAllTipoproducto();
	Mono<TipoOperacionCredito> findByIdTipoProducto(String id);
	Mono<TipoOperacionCredito> saveTipoProducto(TipoOperacionCredito tipoProducto);
	Mono<Void> deleteTipo(TipoOperacionCredito tipoProducto);
	
}
