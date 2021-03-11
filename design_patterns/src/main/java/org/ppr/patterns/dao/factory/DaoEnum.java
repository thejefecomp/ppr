/*
BSD 3-Clause License

Copyright (c) 2019,
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

package org.ppr.patterns.dao.factory;

import org.ppr.entity.Agencia;
import org.ppr.entity.Banco;
import org.ppr.entity.Cartao;
import org.ppr.entity.Cidade;
import org.ppr.entity.Cliente;
import org.ppr.entity.Conta;
import org.ppr.entity.Endereco;
import org.ppr.entity.Estado;
import org.ppr.entity.Funcionario;
import org.ppr.entity.Pais;
import org.ppr.entity.Relatorio;
import org.ppr.entity.Transacao;

import org.ppr.patterns.dao.impl.AgenciaDao;
import org.ppr.patterns.dao.impl.BancoDao;
import org.ppr.patterns.dao.impl.CartaoDao;
import org.ppr.patterns.dao.impl.CidadeDao;
import org.ppr.patterns.dao.impl.ClienteDao;
import org.ppr.patterns.dao.impl.ContaDao;
import org.ppr.patterns.dao.impl.EnderecoDao;
import org.ppr.patterns.dao.impl.EstadoDao;
import org.ppr.patterns.dao.impl.FuncionarioDao;
import org.ppr.patterns.dao.impl.PaisDao;
import org.ppr.patterns.dao.impl.RelatorioDao;
import org.ppr.patterns.dao.impl.TransacaoDao;


public enum DaoEnum {
	
	AGENCIA(Agencia.class,AgenciaDao.class),
	BANCO(Banco.class,BancoDao.class),
	CARTAO(Cartao.class,CartaoDao.class),
	CIDADE(Cidade.class,CidadeDao.class),
	CLIENTE(Cliente.class,ClienteDao.class),
	CONTA(Conta.class,ContaDao.class),
	CONSULTA(Transacao.class,TransacaoDao.class),
	DEPOSITO(Transacao.class,TransacaoDao.class),
	ENDERECO(Endereco.class,EnderecoDao.class),
	ESTADO(Estado.class,EstadoDao.class),
	GERENTE(Funcionario.class,FuncionarioDao.class),
	PAIS(Pais.class,PaisDao.class),
	PESSOAFISICA(Cliente.class,ClienteDao.class),
	PESSOAJURIDICA(Cliente.class,ClienteDao.class),
	RELATORIO(Relatorio.class,RelatorioDao.class),
	SAQUE(Transacao.class,TransacaoDao.class),
	TRANSFERENCIA(Transacao.class,TransacaoDao.class),
	TRANSACAO(Transacao.class, TransacaoDao.class);

	private Class<?> entityClass;
	
	private Class<?> daoClass;
	
	private DaoEnum(Class<?> entityClass, Class<?> daoClass) {
		
		this.entityClass = entityClass;
		
		this.daoClass = daoClass;
	}
	
	public Class<?> getEntityClass(){
		
		return this.entityClass;
	}
	
	public Class<?> getDaoClass(){
		
		return this.daoClass;
	}
}