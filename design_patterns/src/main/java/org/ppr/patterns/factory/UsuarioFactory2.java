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

package org.ppr.patterns.factory;

import java.lang.reflect.InvocationTargetException;

import org.ppr.entity.Gerente;
import org.ppr.entity.PessoaFisica;
import org.ppr.entity.PessoaJuridica;
import org.ppr.entity.common.TipoUsuario;

public class UsuarioFactory2<T>{

    private Class<?> clazz;

    public UsuarioFactory2(TipoUsuario tipoUsuario){

		switch(tipoUsuario){
	
		case GERENTE: this.clazz = Gerente.class;
	
		    break;
	
	
		case PESSOA_FISICA: this.clazz = PessoaFisica.class;
	
		    break;
		    
		case PESSOA_JURIDICA: this.clazz = PessoaJuridica.class;
	
		    break;
		    
	
		default: this.clazz = null;
		}
    }

    @SuppressWarnings("unchecked")
	public T newInstance(){

	if(this.clazz == null)
	    return null;
	try{
	    
	    return (T) this.clazz.getDeclaredConstructor().newInstance();
	}
	catch(InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e){

	    e.printStackTrace();

	    return null;
	}
    }
}
