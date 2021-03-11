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

import java.time.LocalDate;
import java.time.LocalDateTime; 
 
public abstract class Usuario{

	private Long id;
	
	private LocalDateTime dataCriacao;
	
	private String primeiroNome;
	
	private String ultimoNome;
	
	private String nomeCompleto;
	
	private LocalDate dataNascimento;
	
	private String email;
	
	public final Long getId() {
		return id;
	}
	
	public final void setId(Long id) {
		this.id = id;
	}
	
	public final LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	
	public final void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public final String getPrimeiroNome() {
		return primeiroNome;
	}
	
	public final void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	
	public final String getUltimoNome() {
		return ultimoNome;
	}
	
	public final void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	
	public final String getNomeCompleto() {
		return nomeCompleto;
	}
	
	public final void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	
	public final LocalDate getDataNascimento() {
		return dataNascimento;
	}
	
	public final void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public final String getEmail() {
		return email;
	}
	
	public final void setEmail(String email) {
		this.email = email;
	}

}
 
