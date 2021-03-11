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

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.TreeMap;

import org.ppr.database.Database;
import org.ppr.database.impl.memory.MemoryDatabaseImpl;
import org.ppr.patterns.dao.Dao;

public class DaoFactory {
		
	private static DaoFactory instance;
	
	private Map<String, Dao<?>> daoMap;
	
	private DaoFactory() {
		
		this.daoMap = new TreeMap<>();
	}
	
	public static DaoFactory getInstance() {
		
		synchronized(DaoFactory.class) {
			
			if(instance == null) {
				
				instance = new DaoFactory();
			}
		}
		
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getDao (Class<?> entityClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		if(this.daoMap.containsKey(entityClass.getName())){
			
			return (T) daoMap.get(entityClass.getName());
		}
		
		DaoEnum daoEntry = DaoEnum.valueOf(entityClass.getSimpleName().toUpperCase());
		
		T daoImpl = daoEntry == null ? null : (T) daoEntry.getDaoClass().getDeclaredConstructor(Database.class).newInstance(MemoryDatabaseImpl.getInstance());
		
		this.daoMap.put(entityClass.getName(), (Dao<?>)daoImpl);
		
		return daoImpl;
	}
}