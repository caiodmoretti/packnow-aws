package br.edu.iff.PackNow.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
public class Encomenda implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="funcionario_id" )
	private Funcionario funcionarioEntrada;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="funcionario_saida_id" )
	private Funcionario funcionarioSaida;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="endereco_id")
	private Endereco enderecoEntrega;
	
	@ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name="morador_id", nullable = true)
	private Morador moradorRetirada;

	private String dataEntrada;
	
	private String dataSaida;
	
	@NotBlank(message = "O nome do entregador não pode ser nulo ou ficar em branco.")
	@Size(min = 1, max = 80, message = "O nome deve ter entre 1 e 80 caracteres")
	@Column(length = 80)
	private String nomeEntregador;
	
	@NotBlank(message = "O telefone do entregador não pode ser nulo ou ficar em branco.")
	@Size(min = 11, max = 11, message = "O telefone deve ter 11 caracteres.")
	@Column(length = 11)
	private String telefoneEntregador;
	
	public Encomenda(Funcionario funcionario, Endereco enderecoEntrega, String nomeEntregador, String telefoneEntregador) {
		this.funcionarioEntrada = funcionario;
		this.enderecoEntrega = enderecoEntrega;
		this.nomeEntregador = nomeEntregador;
		this.telefoneEntregador = telefoneEntregador;
		this.moradorRetirada = null;
		this.dataEntrada = obterHoraEmFormatoString();
	}
	public Encomenda() {};
	/**
	 * @param the funcionarioEntrada to set
	 */
	public String obterHoraEmFormatoString() {
        LocalDateTime currentDate = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = currentDate.format(formatter);
        return formattedDate;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		if(funcionario == null) {
			throw new IllegalArgumentException("Funcionário não pode ser nulo.");
		}
		this.funcionarioEntrada = funcionario;
	}
	/**
	 * @param the enderecoEntrega to set
	 */
	public void setEndereco(Endereco endereco) {
		if(endereco == null) {
			throw new IllegalArgumentException("Endereço não pode ser nulo.");
		}
		this.enderecoEntrega = endereco;
	}
	/**
	 * @param the nomeEntregador to set
	 */
	public void setNomeEntregador(String nomeEntregador) {
		if(nomeEntregador.trim().isEmpty() || nomeEntregador == null) {
			throw new IllegalArgumentException("Nome de entregador inválido.");
		}
		this.nomeEntregador = nomeEntregador;		
	}
	/**
	 * @param the telefoneEntregador to set
	 */
	public  void setTelefoneEntregador(String telefoneEntregador) {
		if(telefoneEntregador.trim().isEmpty() || telefoneEntregador == null) {
			throw new IllegalArgumentException("Telefone de entregador inválido.");
		}
		this.telefoneEntregador = telefoneEntregador;		
	}
	/**
	 * @param the dataSaida to set
	 */
	public void registrarRetirada(Morador morador) {
		this.dataSaida = obterHoraEmFormatoString();
		this.moradorRetirada = morador;
	}
	/**
	 * @return the funcionarioEntrada
	 */
	public Funcionario getFuncionarioEntrada() {
		return this.funcionarioEntrada;
	}
	public void setMoradorRetirada(Morador moradorRetirada) {
		this.moradorRetirada = moradorRetirada;
	}
	/**
	 * @return the moradorRetirada
	 */
	public Morador getMoradorRetirada() {
		return this.moradorRetirada;
	}
	/**
	 * @return the enderecoRetirada
	 */
	public Endereco getEnderecoEntrega() {
		return this.enderecoEntrega;
	}
	/**
	 * @return the dataEntrada
	 */
	public String getDataEntrada() {

		return this.dataEntrada;
	}
	/**
	 * @return the dataSaida
	 */
	public String getDataSaida() {
		return this.dataSaida;
	}
	/**
	 * @return the nomeEntregador
	 */
	public String getNomeEntregador() {
		return this.nomeEntregador;
	}
	/**
	 * @return the telefoneEntregador
	 */
	public String getTelefoneEntregador() {
		return this.telefoneEntregador;
	}
	public Long getId() {
		return this.id;
	}
	public void setDataEntrada(String dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public void setDataSaida(String dataSaida) {
		this.dataSaida = dataSaida;
	}
	public Funcionario getFuncionarioSaida() {
		return funcionarioSaida;
	}
	public void setFuncionarioSaida(Funcionario funcionarioSaida) {
		this.funcionarioSaida = funcionarioSaida;
	}

	
}
