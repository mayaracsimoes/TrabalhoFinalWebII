package com.exemplo.TrabalhoWeb;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exemplo.TrabalhoWeb.entities.Cliente;
import com.exemplo.TrabalhoWeb.entities.Hotel;
import com.exemplo.TrabalhoWeb.entities.Pagamento;
import com.exemplo.TrabalhoWeb.entities.Quarto;
import com.exemplo.TrabalhoWeb.entities.Servico;
import com.exemplo.TrabalhoWeb.repository.ClienteRepository;
import com.exemplo.TrabalhoWeb.repository.HotelRepository;
import com.exemplo.TrabalhoWeb.repository.PagamentoRepository;
import com.exemplo.TrabalhoWeb.repository.QuartoRepository;
import com.exemplo.TrabalhoWeb.repository.ServicoRepository;

@SpringBootApplication
public class TrabalhoWeb implements CommandLineRunner {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private QuartoRepository quartoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ServicoRepository servicoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoWeb.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Criando clientes
		Cliente cliente1 = new Cliente();
		cliente1.setNomeCliente("João Silva");
		cliente1.setEmailCliente("joao.silva@example.com");
		cliente1.setEnderecoCliente("Rua A, 123");
		cliente1.setTelefoneCliente("123456789");
		cliente1.setCpfCliente("123.456.789-00");
		cliente1.setDataNascimentoCliente(LocalDate.of(1980, 1, 1));
		clienteRepository.save(cliente1);

		Cliente cliente2 = new Cliente();
		cliente2.setNomeCliente("Maria Oliveira");
		cliente2.setEmailCliente("maria.oliveira@example.com");
		cliente2.setEnderecoCliente("Rua B, 456");
		cliente2.setTelefoneCliente("987654321");
		cliente2.setCpfCliente("987.654.321-00");
		cliente2.setDataNascimentoCliente(LocalDate.of(1980, 2, 2));
		clienteRepository.save(cliente2);

		// Criando hotéis
		Hotel hotel1 = new Hotel();
		hotel1.setNomeHotel("Hotel Central");
		hotel1.setEmailHotel("contato@hotelcentral.com");
		hotel1.setEnderecoHotel("Av. Central, 1000");
		hotel1.setLocalizacaoHotel("Centro");
		hotel1.setTelefoneHotel("111222333");
		hotel1.setWebsiteHotel("www.hotelcentral.com");
		hotelRepository.save(hotel1);

		Hotel hotel2 = new Hotel();
		hotel2.setNomeHotel("Hotel Praia");
		hotel2.setEmailHotel("contato@hotelpraia.com");
		hotel2.setEnderecoHotel("Av. Beira Mar, 500");
		hotel2.setLocalizacaoHotel("Praia");
		hotel2.setTelefoneHotel("444555666");
		hotel2.setWebsiteHotel("www.hotelpraia.com");
		hotelRepository.save(hotel2);

		// Criando quartos
		Quarto quarto1 = new Quarto();
		quarto1.setDescricaoQuarto("Quarto Simples");
		quarto1.setNumeroQuarto("101");
		quarto1.setTipoQuarto("Simples");
		quarto1.setPrecoDiariaQuarto(100.0);
		quartoRepository.save(quarto1);

		Quarto quarto2 = new Quarto();
		quarto2.setDescricaoQuarto("Quarto Duplo");
		quarto2.setNumeroQuarto("102");
		quarto2.setTipoQuarto("Duplo");
		quarto2.setPrecoDiariaQuarto(150.0);
		quartoRepository.save(quarto2);

		Quarto quarto3 = new Quarto();
		quarto3.setDescricaoQuarto("Quarto Luxo");
		quarto3.setNumeroQuarto("201");
		quarto3.setTipoQuarto("Luxo");
		quarto3.setPrecoDiariaQuarto(250.0);
		quartoRepository.save(quarto3);

		Pagamento pagamento1 = new Pagamento();
        pagamento1.setDataPagamento(LocalDate.now());
        pagamento1.setValorPagamento(100.00);
        pagamento1.setMetodoPagamento("Cartão de Crédito");
		pagamentoRepository.save(pagamento1);

		Pagamento pagamento2 = new Pagamento();
        pagamento2.setDataPagamento(LocalDate.now());
        pagamento2.setValorPagamento(50.00);
        pagamento2.setMetodoPagamento("Boleto Bancário");
		pagamentoRepository.save(pagamento2);

		Pagamento pagamento3 = new Pagamento();
        pagamento3.setDataPagamento(LocalDate.now());
        pagamento3.setValorPagamento(200.00);
        pagamento3.setMetodoPagamento("Transferência Bancária");
		pagamentoRepository.save(pagamento3);

		Servico servico1 = new Servico();
		servico1.setNomeServico("Serviço de Limpeza");
		servico1.setDescricaoServico("Serviço de limpeza diária do quarto");
		servicoRepository.save(servico1);

		Servico servico2 = new Servico();
		servico2.setNomeServico("Serviço de Alimentação");
		servico2.setDescricaoServico("Refeições incluídas durante a estadia no hotel");
		servicoRepository.save(servico2);
	}
}
