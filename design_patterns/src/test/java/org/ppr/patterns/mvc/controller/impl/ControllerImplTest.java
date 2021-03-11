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


package org.ppr.patterns.mvc.controller.impl;

import static org.junit.Assert.fail;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.ppr.entity.Cliente;
import org.ppr.entity.PessoaFisica;
import org.ppr.entity.PessoaJuridica;
import org.ppr.entity.common.TipoCliente;
import org.ppr.entity.vo.PessoaFisicaVO;
import org.ppr.entity.vo.PessoaJuridicaVO;
import org.ppr.patterns.dao.factory.DaoEnum;
import org.ppr.patterns.dao.factory.DaoFactory;
import org.ppr.patterns.dao.impl.ClienteDao;
import org.ppr.patterns.mvc.controller.OperationEnum;

public class ControllerImplTest {
	
	@Test
	public void executeTest() {
		
		ControllerImpl controllerImpl = new ControllerImpl();
		
		PessoaFisicaVO clienteVO = new PessoaFisicaVO();
		
		clienteVO.setPrimeiroNome("Jeferson");
		clienteVO.setUltimoNome("Souza");
		clienteVO.setCpf(Long.valueOf("00424786931"));
		clienteVO.setId(clienteVO.getCpf());
		
		clienteVO.setTipoCliente(TipoCliente.PESSOA_FISICA);
		
		Assert.assertTrue((Boolean)controllerImpl.execute(OperationEnum.ADD_PESSOA_FISICA, clienteVO));
		
		controllerImpl.execute(OperationEnum.ADD_PESSOA_FISICA, clienteVO);
		
		PessoaJuridicaVO clienteJurVO = new PessoaJuridicaVO();
		
		clienteJurVO.setPrimeiroNome("Jeferson");
		clienteJurVO.setUltimoNome("Souza");
		clienteJurVO.setRazaoSocial("JCOM Ltda.");
		clienteJurVO.setNomeFantasia("JCOM");
		clienteJurVO.setCnpj(Long.valueOf("01234567890123"));
		clienteJurVO.setId(clienteJurVO.getCnpj());
		
		clienteJurVO.setTipoCliente(TipoCliente.PESSOA_JURIDICA);
		
		controllerImpl.execute(OperationEnum.ADD_PESSOA_JURIDICA, clienteJurVO);
		
		try {
			
			ClienteDao clienteDao = DaoFactory.getInstance().getDao(DaoEnum.PESSOAFISICA.getEntityClass());
			Cliente cliente = clienteDao.find(Long.valueOf("00424786931"));
			Assert.assertNotNull(cliente);
			Assert.assertEquals("Jeferson", cliente.getPrimeiroNome());
			Assert.assertEquals("Souza", cliente.getUltimoNome());
			Assert.assertTrue(cliente instanceof PessoaFisica);
			Assert.assertEquals(Long.valueOf("00424786931"), ((PessoaFisica)cliente).getCpf());
			
			clienteDao = DaoFactory.getInstance().getDao(DaoEnum.PESSOAJURIDICA.getEntityClass());
			cliente = clienteDao.find(Long.valueOf("01234567890123"));
			Assert.assertNotNull(cliente);
			Assert.assertEquals("Jeferson", cliente.getPrimeiroNome());
			Assert.assertEquals("Souza", cliente.getUltimoNome());
			Assert.assertTrue(cliente instanceof PessoaJuridica);
			Assert.assertEquals("JCOM Ltda.", ((PessoaJuridica)cliente).getRazaoSocial());
			Assert.assertEquals("JCOM", ((PessoaJuridica)cliente).getNomeFantasia());
			Assert.assertEquals(Long.valueOf("01234567890123"), ((PessoaJuridica)cliente).getCnpj());
			
			List<Cliente> clienteList = clienteDao.findAll();
			
			Assert.assertNotNull(clienteList);
			Assert.assertEquals(2, clienteList.size());
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			
			fail();
		}
		
		clienteVO = (PessoaFisicaVO) controllerImpl.execute(OperationEnum.FIND_PESSOA_FISICA, Long.valueOf("00424786931"));
		
		Assert.assertNotNull(clienteVO);
		Assert.assertEquals("Jeferson", clienteVO.getPrimeiroNome());
		Assert.assertEquals("Souza", clienteVO.getUltimoNome());
		Assert.assertEquals(Long.valueOf("00424786931"), clienteVO.getCpf());
		
		clienteJurVO = (PessoaJuridicaVO) controllerImpl.execute(OperationEnum.FIND_PESSOA_JURIDICA, Long.valueOf("01234567890123"));
		
		Assert.assertNotNull(clienteJurVO);
		Assert.assertEquals("Jeferson", clienteJurVO.getPrimeiroNome());
		Assert.assertEquals("Souza", clienteJurVO.getUltimoNome());
		Assert.assertEquals("JCOM Ltda.", clienteJurVO.getRazaoSocial());
		Assert.assertEquals("JCOM", clienteJurVO.getNomeFantasia());
		Assert.assertEquals(Long.valueOf("01234567890123"), clienteJurVO.getCnpj());
	}
}
