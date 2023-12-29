package spring.boot.webflu.ms.op.credito.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.dao.TipoOperacionCreditoDao;
import spring.boot.webflu.ms.op.credito.app.documents.TipoOperacionCredito;
import spring.boot.webflu.ms.op.credito.app.service.TipoOperacionCreditoService;

@Service
public class TipoOperacionCreditoServiceImpl implements TipoOperacionCreditoService{

	
	@Autowired
	public TipoOperacionCreditoDao  tipoProductoDao;
	
	@Override
	public Flux<TipoOperacionCredito> findAllTipoproducto()
	{
		return tipoProductoDao.findAll();
	
	}
	@Override
	public Mono<TipoOperacionCredito> findByIdTipoProducto(String id)
	{
		return tipoProductoDao.findById(id);
	
	}
	
	@Override
	public Mono<TipoOperacionCredito> saveTipoProducto(TipoOperacionCredito tipoCliente)
	{
		return tipoProductoDao.save(tipoCliente);
	}
	
	@Override
	public Mono<Void> deleteTipo(TipoOperacionCredito tipoProducto) {
		return tipoProductoDao.delete(tipoProducto);
	}
	
}
