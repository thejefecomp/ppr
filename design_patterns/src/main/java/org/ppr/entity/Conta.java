/*
BSD 3-Clause License

Copyright (c) 2018,
Jeferson Souza (thejefecomp) - jefecomp.official@gmail.com
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of the copyright holder nor the names of its
  contributors may be used to endorse or promote products derived from
  this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */
 
package org.ppr.entity;

import java.util.List;
import java.util.Map;

import org.ppr.entity.common.TipoConta;

public class Conta{
	
	private Long id;
	
	private Long numeroConta;
	
	private Agencia agencia;
	
	private Map<Long,Cartao> cartoes;
	
	private Usuario gerenteConta;
	
	private Cartao cartaoPrincipal;
	
	private Cliente titularPrincipal;
	
	private Map<Long,Cliente> titulares;
	
	private Map<Long,List<Permissao>> permissoes;
	
	private Map<Permissao,List<Cliente>> autorizacoes;
	
	private Long saldo;
	
	private TipoConta tipoConta;
	
	private Long limiteConta;
	
	private Long limiteTotalCartoes;
	
	private Map<TipoConta,List<Conta>> investimentos;

	public final Long getId() {
		return id;
	}

	public final void setId(Long id) {
		this.id = id;
	}

	public final Long getNumeroConta() {
		return numeroConta;
	}

	public final void setNumeroConta(Long numeroConta) {
		this.numeroConta = numeroConta;
	}

	public final Agencia getAgencia() {
		return agencia;
	}

	public final void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public final Map<Long, Cartao> getCartoes() {
		return cartoes;
	}

	public final void setCartoes(Map<Long, Cartao> cartoes) {
		this.cartoes = cartoes;
	}

	public final Usuario getGerenteConta() {
		return gerenteConta;
	}

	public final void setGerenteConta(Usuario gerenteConta) {
		this.gerenteConta = gerenteConta;
	}

	public final Cartao getCartaoPrincipal() {
		return cartaoPrincipal;
	}

	public final void setCartaoPrincipal(Cartao cartaoPrincipal) {
		this.cartaoPrincipal = cartaoPrincipal;
	}

	public final Cliente getTitularPrincipal() {
		return titularPrincipal;
	}

	public final void setTitularPrincipal(Cliente titularPrincipal) {
		this.titularPrincipal = titularPrincipal;
	}

	public final Map<Long, Cliente> getTitulares() {
		return titulares;
	}

	public final void setTitulares(Map<Long, Cliente> titulares) {
		this.titulares = titulares;
	}

	public final Map<Long, List<Permissao>> getPermissoes() {
		return permissoes;
	}

	public final void setPermissoes(Map<Long, List<Permissao>> permissoes) {
		this.permissoes = permissoes;
	}

	public final Map<Permissao, List<Cliente>> getAutorizacoes() {
		return autorizacoes;
	}

	public final void setAutorizacoes(Map<Permissao, List<Cliente>> autorizacoes) {
		this.autorizacoes = autorizacoes;
	}

	public final Long getSaldo() {
		return saldo;
	}

	public final void setSaldo(Long saldo) {
		this.saldo = saldo;
	}

	public final TipoConta getTipoConta() {
		return tipoConta;
	}

	public final void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	public final Long getLimiteConta() {
		return limiteConta;
	}

	public final void setLimiteConta(Long limiteConta) {
		this.limiteConta = limiteConta;
	}

	public final Long getLimiteTotalCartoes() {
		return limiteTotalCartoes;
	}

	public final void setLimiteTotalCartoes(Long limiteTotalCartoes) {
		this.limiteTotalCartoes = limiteTotalCartoes;
	}

	public final Map<TipoConta, List<Conta>> getInvestimentos() {
		return investimentos;
	}

	public final void setInvestimentos(Map<TipoConta, List<Conta>> investimentos) {
		this.investimentos = investimentos;
	}
}