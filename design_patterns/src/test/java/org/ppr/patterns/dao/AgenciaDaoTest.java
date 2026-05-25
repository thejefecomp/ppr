/*
BSD 3-Clause License

Copyright (c) 2019-2026,
Jeferson L. R. Souza (thejefecomp) - thejefecomp@neartword.com
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

package org.ppr.patterns.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.ppr.entity.Agencia;
import org.ppr.entity.Banco;
import org.ppr.entity.Cidade;
import org.ppr.entity.Endereco;
import org.ppr.entity.Pais;
import org.ppr.patterns.dao.factory.DaoEnum;
import org.ppr.patterns.dao.factory.DaoFactory;
import org.ppr.patterns.dao.impl.AgenciaDao;
import org.ppr.patterns.dao.impl.BancoDao;
import org.ppr.patterns.dao.impl.CidadeDao;
import org.ppr.patterns.dao.impl.EnderecoDao;
import org.ppr.patterns.dao.impl.PaisDao;

public class AgenciaDaoTest {
	
	private AgenciaDao agenciaDao;
	
	private BancoDao bancoDao;
	
	private CidadeDao cidadeDao;
	
	private EnderecoDao enderecoDao;
	
	private PaisDao paisDao;
	
	public AgenciaDaoTest() {
	
		try {
			this.agenciaDao = DaoFactory.getInstance().getDao(DaoEnum.AGENCIA.getEntityClass());
			this.bancoDao = DaoFactory.getInstance().getDao(DaoEnum.BANCO.getEntityClass());
			this.cidadeDao = DaoFactory.getInstance().getDao(DaoEnum.CIDADE.getEntityClass());
			this.enderecoDao = DaoFactory.getInstance().getDao(DaoEnum.ENDERECO.getEntityClass());
			this.paisDao = DaoFactory.getInstance().getDao(DaoEnum.PAIS.getEntityClass());
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Test
	public void insertTest() {
		
		Banco banco = new Banco();
		
		banco.setId(1L);
		banco.setNome("Banco Teste");
		banco.setDescricao(banco.getNome());
		
		Pais pais = new Pais();
		pais.setId(1L);
		pais.setNome("Pais Teste");
		
		Cidade cidade = new Cidade();
		cidade.setId(1L);
		cidade.setNome("Cidade Teste");
		cidade.setPais(pais);
		
		Endereco endereco = new Endereco();
		
		endereco.setId(1L);
		endereco.setCep(12345678L);
		endereco.setLogradouro("Rua Teste");
		endereco.setNumero(100);
		endereco.setCidade(cidade);
		
		Agencia agencia = new Agencia();
		agencia.setId(1L);
		agencia.setNumeroAgencia(1234L);
		agencia.setNome("Agencia Teste");
		agencia.setBanco(banco);
		agencia.setDataAbertura(LocalDate.now());
		agencia.setEndereco(endereco);
		
		Agencia agenciaRetorno = this.agenciaDao.insert(agencia.getId(), agencia);
		
		assertEquals(agencia, agenciaRetorno);
		assertEquals(banco, agenciaRetorno.getBanco());
		assertEquals(endereco, agenciaRetorno.getEndereco());
		assertEquals(cidade, agencia.getEndereco().getCidade());
		assertEquals(pais, agenciaRetorno.getEndereco().getCidade().getPais());
	}
	
	@Test
	public void findTest() {
		
		this.insertTest();
		
		Agencia agencia = this.agenciaDao.find(1L);
		
		assertNotNull(agencia);
		assertNotNull(agencia.getBanco());
		assertNotNull(agencia.getEndereco());
		
		List<Agencia> agenciaList = this.agenciaDao.findAll();
		
		assertNotNull(agenciaList);
		assertEquals(1, agenciaList.size());
		
		Banco banco = this.bancoDao.find(1L);
		
		assertNotNull(banco);
		assertEquals(banco, agencia.getBanco());
		
		List<Banco> bancoList = this.bancoDao.findAll();
		
		assertNotNull(bancoList);
		assertEquals(1, bancoList.size());
		
		Endereco endereco = this.enderecoDao.find(1L);
		
		assertNotNull(endereco);
		assertNotNull(endereco.getCidade());
		assertEquals(endereco, agencia.getEndereco());
		
		List<Endereco> enderecoList = this.enderecoDao.findAll();
		
		assertNotNull(enderecoList);
		assertEquals(1, enderecoList.size());
		
		Cidade cidade = this.cidadeDao.find(1L);
		
		assertNotNull(cidade);
		assertEquals(cidade, endereco.getCidade());
		assertNotNull(cidade.getPais());
		
		List<Cidade> cidadeList = this.cidadeDao.findAll();
		
		assertNotNull(cidadeList);
		assertEquals(1, cidadeList.size());
		
		Pais pais = this.paisDao.find(1L);
		
		assertNotNull(pais);
		assertEquals(pais, cidade.getPais());
		
		List<Pais> paisList = this.paisDao.findAll();
		
		assertNotNull(paisList);
		assertEquals(1, paisList.size());
	}
	
	@Test
	public void deleteTest() {
		
		this.insertTest();
		
		this.agenciaDao.delete(1L);
		
		assertNull(this.agenciaDao.find(1L));
	}
	
	@Test
	public void updateTest() {
		
		this.insertTest();
		
		Agencia agencia = this.agenciaDao.find(1L);
		
		assertEquals("Agencia Teste", agencia.getNome());
		
		agencia.setNome("Nome Agencia Atualizado");
		
		this.agenciaDao.update(agencia.getId(), agencia);
		
		agencia = this.agenciaDao.find(1L);
		
		assertEquals("Nome Agencia Atualizado", agencia.getNome());
	}
}
