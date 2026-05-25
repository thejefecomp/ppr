/*
BSD 3-Clause License

Copyright (c) 2018,
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

package org.ppr.patterns.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import org.ppr.entity.Gerente;
import org.ppr.entity.PessoaFisica;
import org.ppr.entity.PessoaJuridica;
import org.ppr.entity.Usuario;
import org.ppr.entity.common.TipoUsuario;

public class UserFactoryTest{

    @Test
    public void newInstanceTest(){

	UsuarioFactory factory = new UsuarioFactory();
	
	Usuario usuario = factory.newInstance(TipoUsuario.GERENTE);

	assertNotNull(usuario);
	assertTrue(usuario instanceof Gerente);
	
	
	usuario = factory.newInstance(TipoUsuario.PESSOA_FISICA);

	assertNotNull(usuario);
	assertTrue(usuario instanceof PessoaFisica);
	
	usuario = factory.newInstance(TipoUsuario.PESSOA_JURIDICA);

	assertNotNull(usuario);
	assertTrue(usuario instanceof PessoaJuridica);
	
	usuario = factory.newInstance(null);

	assertNull(usuario);



	




    }
}
