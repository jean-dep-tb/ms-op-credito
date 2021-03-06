package spring.boot.webflu.ms.op.credito.app.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.dao.TipoOperacionDao;
import spring.boot.webflu.ms.op.credito.app.documents.TypeOperation;
import spring.boot.webflu.ms.op.credito.app.service.TipoOperacionService;

@Service
public class TipoOperacionServiceImpl implements TipoOperacionService{

	
	@Autowired
	public TipoOperacionDao  tipoProductoDao;
	
	@Override
	public Flux<TypeOperation> findAllTipoproducto()
	{
		return tipoProductoDao.findAll();
	
	}
	@Override
	public Mono<TypeOperation> findByIdTipoProducto(String id)
	{
		return tipoProductoDao.findById(id);
	
	}
	
	@Override
	public Mono<TypeOperation> saveTipoProducto(TypeOperation tipoCliente)
	{
		return tipoProductoDao.save(tipoCliente);
	}
	
	@Override
	public Mono<Void> deleteTipo(TypeOperation tipoProducto) {
		return tipoProductoDao.delete(tipoProducto);
	}
	
}
