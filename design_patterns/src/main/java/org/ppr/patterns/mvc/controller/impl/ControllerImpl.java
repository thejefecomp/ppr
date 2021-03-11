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
 
 package org.ppr.patterns.mvc.controller.impl;
 
 import java.time.LocalDateTime;

import org.ppr.entity.Cliente;
import org.ppr.entity.PessoaFisica;
import org.ppr.entity.PessoaJuridica;
import org.ppr.entity.common.TipoCliente;
import org.ppr.entity.vo.ClienteVO;
import org.ppr.entity.vo.PessoaFisicaVO;
import org.ppr.entity.vo.PessoaJuridicaVO;
import org.ppr.patterns.facade.DataFacade;
import org.ppr.patterns.facade.impl.DataFacadeImpl;
import org.ppr.patterns.mvc.controller.Controller;
import org.ppr.patterns.mvc.controller.OperationEnum;
 
 
 public class ControllerImpl implements Controller{
	 
	 private DataFacade dataFacade;
	 
	 private ClienteVO map(Cliente source) {
		 
		 ClienteVO destination = null;
		 
		 switch(source.getTipoCLiente()) {
		 
		 case PESSOA_FISICA: 
			 				destination = new PessoaFisicaVO();
			 				((PessoaFisicaVO)destination).setCpf(((PessoaFisica)source).getCpf());
			 				break;
			 
		 case PESSOA_JURIDICA: 
			 				 destination = new PessoaJuridicaVO();
			 				 ((PessoaJuridicaVO)destination).setRazaoSocial(((PessoaJuridica)source).getRazaoSocial());
			 				 ((PessoaJuridicaVO)destination).setNomeFantasia(((PessoaJuridica)source).getNomeFantasia());
			 				 ((PessoaJuridicaVO)destination).setCnpj(((PessoaJuridica)source).getCnpj());
			 				 break;
			 
	     default: return null;
		 
		 }
		 
		 destination.setDataCriacao(LocalDateTime.now());
		 destination.setId(source.getId());
		 destination.setPrimeiroNome(source.getPrimeiroNome());
		 destination.setUltimoNome(source.getUltimoNome());
		 
		 return destination;
	 }
	 
	 private Cliente map(ClienteVO source){
		 
		 Cliente destination = null;
		 
		 switch(source.getTipoCLiente()) {
		 
		 case PESSOA_FISICA: 
			 				destination = new PessoaFisica();
			 				((PessoaFisica)destination).setCpf(((PessoaFisicaVO)source).getCpf());
			 				break;
			 
		 case PESSOA_JURIDICA: 
			 				 destination = new PessoaJuridica();
			 				 ((PessoaJuridica)destination).setRazaoSocial(((PessoaJuridicaVO)source).getRazaoSocial());
			 				 ((PessoaJuridica)destination).setNomeFantasia(((PessoaJuridicaVO)source).getNomeFantasia());
			 				 ((PessoaJuridica)destination).setCnpj(((PessoaJuridicaVO)source).getCnpj());
			 				 break;
			 
	     default: return null;
		 
		 }
		 
		 destination.setDataCriacao(LocalDateTime.now());
		 destination.setTipoCliente(source.getTipoCLiente());
		 destination.setId(source.getId());
		 destination.setPrimeiroNome(source.getPrimeiroNome());
		 destination.setUltimoNome(source.getUltimoNome());
		 
		 return destination;
	 }
	 
	 public ControllerImpl(){
		 
		 this.dataFacade = new DataFacadeImpl();
	 }
	 
	 public Boolean addCliente(Object ...data) {
		 
		 if(data == null || data.length == 0) {
			 
			 return false;
		 }
		 	 
		 Cliente cliente = this.map((ClienteVO) data[0]);
		 this.dataFacade.insert(cliente.getId(),cliente);
		
		 return true;
	 }
	 
	 public ClienteVO findCliente(TipoCliente tipoCliente, Object ...data) {
		 
		 if(data == null || data.length == 0) {
			 
			 return null;
		 }
		 
		 Cliente result = null;
		 
		 switch (tipoCliente) {
		 
			 case PESSOA_FISICA:
				 
				 result = this.dataFacade.find((Long)data[0],PessoaFisica.class);
				 
				break;
	
			 case PESSOA_JURIDICA:
				 
				 result = this.dataFacade.find((Long)data[0],PessoaJuridica.class);
				 break;
				 
			 default: break;
		}
		 
		if(result == null) {
			
			return null;
		}
		 
		return this.map(result); 
	 }
	 
	 
	 public Object execute(OperationEnum operation, Object ... data){
		 
		 switch(operation){
			 
			 case ADD_PESSOA_FISICA:
			 case ADD_PESSOA_JURIDICA:
				 
				 return this.addCliente(data);
			 
			 case DEL_PESSOA_FISICA:
			 case DEL_PESSOA_JURIDICA:
				 
				 break;
				 
			 case FIND_PESSOA_FISICA:
				 
				 return this.findCliente(TipoCliente.PESSOA_FISICA, data);
				 
			 case FIND_PESSOA_JURIDICA:
				 
				 return this.findCliente(TipoCliente.PESSOA_JURIDICA, data);
	 
			 default:
		 }
		 
		 return null;
	 }	 
 }