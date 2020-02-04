package spring.boot.webflu.ms.op.credito.app.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import spring.boot.webflu.ms.op.credito.app.documents.TipoOperacionCredito;

public interface TipoOperacionCreditoDao extends ReactiveMongoRepository<TipoOperacionCredito, String> {

}
