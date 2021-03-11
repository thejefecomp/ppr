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

package org.ppr.patterns.mvc.view;

import java.util.Scanner;

import org.ppr.entity.common.TipoCliente;
import org.ppr.entity.vo.PessoaFisicaVO;
import org.ppr.entity.vo.PessoaJuridicaVO;
import org.ppr.patterns.mvc.controller.Controller;
import org.ppr.patterns.mvc.controller.OperationEnum;
import org.ppr.patterns.mvc.controller.impl.ControllerImpl;


/*
 * TODO
 */
public class AppUI{

    private Controller controller;

    private Scanner scanner;

    public AppUI(){

	this.controller = new ControllerImpl();

	this.scanner = new Scanner(System.in);
	
    }
    
    public void addPessoaFisica(){

		PessoaFisicaVO cliente = new PessoaFisicaVO();
	
		System.out.println("Digite o primeiro nome do cliente: ");
		cliente.setPrimeiroNome(this.scanner.next());
	
		System.out.println("Digite o ultimo nome do cliente: ");
		cliente.setUltimoNome(this.scanner.next());
	
		System.out.println("Digite o cpf do cliente: ");
		cliente.setCpf(this.scanner.nextLong());
		cliente.setId(cliente.getCpf());
		
		cliente.setTipoCliente(TipoCliente.PESSOA_FISICA);
		
		if((Boolean) this.controller.execute(OperationEnum.ADD_PESSOA_FISICA, cliente)){
			
			System.out.println("Cliente Cadastrado com Sucesso!");
		}
		else {
			
			System.out.println("Erro no Cadastro do Cliente!");
		}
    }
    
    public void addPessoaJuridica(){

		PessoaJuridicaVO cliente = new PessoaJuridicaVO();
	
		System.out.println("Digite o primeiro nome do proprietario: ");
		cliente.setPrimeiroNome(this.scanner.next());
	
		System.out.println("Digite o ultimo nome do proprietario: ");
		cliente.setUltimoNome(this.scanner.next());
	
		System.out.println("Digite a razao social da empresa: ");
		cliente.setRazaoSocial(this.scanner.next());
		
		System.out.println("Digite o nome fantasia da empresa: ");
		cliente.setNomeFantasia(this.scanner.next());
		
		System.out.println("Digite o cnpj da empresa: ");
		cliente.setCnpj(this.scanner.nextLong());
		
		cliente.setTipoCliente(TipoCliente.PESSOA_JURIDICA);
		
		if ((Boolean) this.controller.execute(OperationEnum.ADD_PESSOA_JURIDICA, cliente)) {
			
			System.out.println("Cliente Cadastrado com Sucesso!");
		}
		else {
			
			System.out.println("Erro no Cadastro do Cliente!");
		}
    }
    
    public String completeNumberMask(String number, int expectedLength) {
    	
		StringBuilder sbNumber = new StringBuilder();
    	
    	if(number.length() < expectedLength) {
    		
    		int total = expectedLength - number.length();
    		for(int i = 0; i < total; i++) {
    			sbNumber.append("0");
    		}
    	}
    	
    	sbNumber.append(number);
    	
    	return sbNumber.toString();
    }
    
    public void findPessoaFisica() {
    	
    	System.out.println("Digite o cpf: ");
    	Long cpf = this.scanner.nextLong();
    	PessoaFisicaVO result = (PessoaFisicaVO) this.controller.execute(OperationEnum.FIND_PESSOA_FISICA, cpf);
    	
    	if(result == null) {
    		
    		System.out.println("Nenhum Cliente Encontrado!");
    		
    		return;
    	}
    	
    	this.printPessoaFisica(result);
    }
    
    public void findPessoaJuridica() {
    	
    	System.out.println("Digite o cnpj: ");
    	Long cnpj = this.scanner.nextLong();
    	PessoaJuridicaVO result = (PessoaJuridicaVO) this.controller.execute(OperationEnum.FIND_PESSOA_JURIDICA, cnpj);
    	
    	if(result == null) {
    		
    		System.out.println("Nenhum Cliente Encontrado!");
    		
    		return;
    	}
    	
    	this.printPessoaJuridica(result);
    }

    public boolean menu(){

    	
    	this.printMenu(true, "Cadastrar Cliente", "Encontrar Cliente");
    
		switch(this.scanner.nextInt()){
		
			case 1:
				
					this.printMenu(false, "Pessoa Fisica", "Pessoa Juridica");	
					
					switch(scanner.nextInt()) {
					
						case 1: 
								this.addPessoaFisica();
								break;
						case 2:
								this.addPessoaJuridica();
								break;
								
						default: break;
					
					}
					
					break;
					
			case 2: 
				
					this.printMenu(false, "Pessoa Fisica", "Pessoa Juridica");
					
					switch(this.scanner.nextInt()) {
					
						case 1: 
								this.findPessoaFisica();
								break;
								
						case 2: 
								this.findPessoaJuridica();
								break;
								
						default: break;
					}
					
					break;
					
			case 3: 
					this.scanner.close();
					
					return false;
					
			default: break;
		}
		
		return true;
    }
    
    public void printMenu(boolean root, String ...options) {
    	
    	System.out.println("***********Exemplo de APP  MVC *************");
    	System.out.println("Digite a opcao desejada:");
    	
    	for(int i = 0; i < options.length; i++) {
    		
    		System.out.println((i+1) + " - "+ options[i]);
    	}
    	
    	if(root) {
    	
    		System.out.println((options.length +1) + " - Sair");
    	}
    	else {
    		
    		System.out.println((options.length +1) + " - Voltar");
    	}
    	
    	
    	System.out.println("***********Exemplo de APP  MVC *************");
    	
    }
    
    public void printPessoaFisica(PessoaFisicaVO cliente) {
    	
    	System.out.println("--- Cliente Pessoa Fisica ---");
    	System.out.println("Primeiro Nome: "+cliente.getPrimeiroNome());
    	System.out.println("Ultimo Nome: "+cliente.getUltimoNome());
    	System.out.println("CPF: "+ this.completeNumberMask(cliente.getCpf().toString(), 11));
    	System.out.println("Data de Cadastro: "+ cliente.getDataCriacao());
    }
    
    public void printPessoaJuridica(PessoaJuridicaVO cliente) {
    	
    	System.out.println("--- Cliente Pessoa Juridica ---");
    	System.out.println("Primeiro Nome Proprietario: "+cliente.getPrimeiroNome());
    	System.out.println("Ultimo Nome Proprietario: "+cliente.getUltimoNome());
    	System.out.println("Razao Social: "+cliente.getRazaoSocial());
    	System.out.println("Nome Fantasia: "+cliente.getNomeFantasia());
    	System.out.println("CNPJ: "+ this.completeNumberMask(cliente.getCnpj().toString(), 14));
    	System.out.println("Data de Cadastro: "+ cliente.getDataCriacao());
    }
}
