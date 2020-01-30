package spring.boot.webflu.ms.op.credito.app.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.client.ProductoCreditoClient;
import spring.boot.webflu.ms.op.credito.app.dao.OperacionDao;
import spring.boot.webflu.ms.op.credito.app.documents.OperationCreditAccount;
import spring.boot.webflu.ms.op.credito.app.documents.TypeOperation;
import spring.boot.webflu.ms.op.credito.app.dto.CreditAccount;
import spring.boot.webflu.ms.op.credito.app.service.OperacionService;

@Service
public class OperacionServiceImpl implements OperacionService {

	@Autowired
	public OperacionDao productoDao;
	
	@Autowired
	public OperacionDao tipoProductoDao;

	@Autowired
	private ProductoCreditoClient productoCreditoClient;


	@Override
	public Flux<OperationCreditAccount> findAllOperacion() {
		return productoDao.findAll();

	}
	
	@Override
	public Mono<OperationCreditAccount> findByIdOperacion(String id) {
		return productoDao.findById(id);

	}
	
	@Override
	public Flux<OperationCreditAccount> findAllByIdOperacionDniCliente(String dni) {
		return productoDao.viewDniCliente(dni);

	}

	@Override
	public Mono<OperationCreditAccount> saveOperacionCredito(OperationCreditAccount producto)
	{
		return productoDao.save(producto);
	}
	@Override
	public Flux<OperationCreditAccount> saveOperacionCredito(List<OperationCreditAccount> producto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

	@Override
	public Mono<OperationCreditAccount> saveOperacionRetiro(OperationCreditAccount operacion)
	{
		
		Mono<CreditAccount> oper = productoCreditoClient
				.retiroBancario(operacion.getCuenta_origen(), operacion.getMontoPago());
				
				 return oper.flatMap(c->{
						if(c.getNumero_cuenta() == null) 
						{	
							return Mono.error(new InterruptedException("TARGETA INVALIDA"));
						}
				
						
				TypeOperation tipo=new TypeOperation();
				tipo.setIdTipo("2");	
				tipo.setDescripcion("Retiro");
				operacion.setTipoOperacion(tipo);
				return productoDao.save(operacion);
				
			
		});
	}
	@Override
	public Mono<OperationCreditAccount> saveOperacionDeposito(OperationCreditAccount operacion)
	{
		
				 Mono<CreditAccount> oper = productoCreditoClient
							.despositoBancario(operacion.getCuenta_origen(), operacion.getMontoPago());
				 
				 return oper.flatMap(c->{
						if(c.getNumero_cuenta() == null) 
						{	
							return Mono.error(new InterruptedException("TARGETA INVALIDA"));
						}
						
				TypeOperation tipo=new TypeOperation();
				
				
				tipo.setIdTipo("1");	
				tipo.setDescripcion("Deposito");
				operacion.setTipoOperacion(tipo);
				return productoDao.save(operacion);
			
		});
	}

	@Override
	public Flux<OperationCreditAccount> transaccionMovimiento(String dni, String numTarjeta) {
		
		return productoDao.consultarMovimientos(dni, numTarjeta);
	}
	
	

}
