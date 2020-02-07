package spring.boot.webflu.ms.op.credito.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.boot.webflu.ms.op.credito.app.client.ProductoCreditoClient;
import spring.boot.webflu.ms.op.credito.app.dao.OperacionCreditoDao;
import spring.boot.webflu.ms.op.credito.app.documents.OperacionCuentaCredito;
import spring.boot.webflu.ms.op.credito.app.documents.TipoOperacionCredito;
import spring.boot.webflu.ms.op.credito.app.dto.CuentaCredito;
import spring.boot.webflu.ms.op.credito.app.service.OperacionCreditoService;

@Service
public class OperacionCreditoServiceImpl implements OperacionCreditoService {

	@Autowired
	public OperacionCreditoDao productoDao;
	
	@Autowired
	public OperacionCreditoDao tipoProductoDao;

	@Autowired
	private ProductoCreditoClient productoCreditoClient;


	@Override
	public Flux<OperacionCuentaCredito> findAllOperacion() {
		return productoDao.findAll();

	}
	
	@Override
	public Mono<OperacionCuentaCredito> findByIdOperacion(String id) {
		return productoDao.findById(id);

	}
	
	@Override
	public Flux<OperacionCuentaCredito> findAllByIdOperacionDniCliente(String dni) {
		return productoDao.findByDni(dni);

	}

	@Override
	public Mono<OperacionCuentaCredito> saveOperacionCredito(OperacionCuentaCredito producto)
	{
		return productoDao.save(producto);
	}
	@Override
	public Flux<OperacionCuentaCredito> saveOperacionCredito(List<OperacionCuentaCredito> producto) {
		return null;
	}
	
	

	@Override
	public Mono<OperacionCuentaCredito> saveOperacionRetiro(OperacionCuentaCredito operacion)
	{
		//VERIFICAR SI LA CUENTA PERTENECE AL CLIENTE
		
		//RETIRAR DE UNA CUENTA DE CREDITO
		Mono<CuentaCredito> oper = productoCreditoClient
				.retiroBancarioCredito(operacion.getCuenta_origen(), operacion.getMontoPago(),operacion.getCodigoBanco());
				
				 return oper.flatMap(c->{
						if(c.getNumeroCuenta() == null) 
						{	
							return Mono.error(new InterruptedException("CUENTA INVALIDA"));
						}
				
				//REGISTRO DE LA TRANSACCION
				TipoOperacionCredito tipo=new TipoOperacionCredito();
				tipo.setIdTipo("2");	
				tipo.setDescripcion("Retiro");
				operacion.setTipoOperacion(tipo);
				return productoDao.save(operacion);
				
			
		});
	}
	
	@Override
	public Mono<OperacionCuentaCredito> saveOperacionDeposito(OperacionCuentaCredito operacion)
	{
		
		System.out.println("codigo banco : " + operacion.getCodigoBanco());
		System.out.println("cuenta : " +  operacion.getCuenta_origen());
		
		 Mono<CuentaCredito> oper = productoCreditoClient
					.despositoBancarioCredito(operacion.getCuenta_origen(), operacion.getMontoPago(),operacion.getCodigoBanco());
		 
		 return oper.flatMap(c->{
				if(c.getNumeroCuenta() == null) 
				{	
					return Mono.error(new InterruptedException("CUENTA INVALIDA"));
				}
				
		TipoOperacionCredito tipo=new TipoOperacionCredito();
		
		
		tipo.setIdTipo("1");	
		tipo.setDescripcion("Deposito");
		operacion.setTipoOperacion(tipo);
		return productoDao.save(operacion);
			
		});
	}

	@Override
	public Flux<OperacionCuentaCredito> transaccionMovimiento(String dni, String numTarjeta) {
		
		return productoDao.consultarMovimientos(dni, numTarjeta);
	}
	
	

}
