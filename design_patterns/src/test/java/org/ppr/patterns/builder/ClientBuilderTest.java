/*
BSD 3-Clause License

Copyright (c) 2018-2026,
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

package org.ppr.patterns.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import org.ppr.entity.Cliente;
import org.ppr.entity.common.TipoCliente;

public class ClientBuilderTest{

    @Test
    public void buildTest(){

	ClientBuilder builder = new ClientBuilder();

	Cliente newClient = builder.addTipoCliente(TipoCliente.PESSOA_FISICA)
							  .addId(1L)
	                          .addEmail("jefecomp.official@gmail.com")
	                          .addPrimeiroNome("Jeferson")
	                          .addUltimoNome("Souza")
	                          .build();

	assertEquals(Long.valueOf(1L), newClient.getId());
	assertEquals("jefecomp.official@gmail.com", newClient.getEmail());
	assertEquals("Jeferson", newClient.getPrimeiroNome());
	assertEquals("Souza", newClient.getUltimoNome());
	assertEquals(TipoCliente.PESSOA_FISICA, newClient.getTipoCLiente());
    } 
}
