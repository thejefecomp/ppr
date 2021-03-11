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

package org.ppr.database.impl.memory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.ppr.database.Database;

public class MemoryDatabaseImpl implements Database{

    private static MemoryDatabaseImpl instance;
    
    private Map<String, Map<?,?>> databaseEntityStorage;

    private MemoryDatabaseImpl(){
    	
    	this.databaseEntityStorage = new TreeMap<String, Map<?,?>>();
    }
    
    private <T> Set<String> findClassCastSet(Class<T> entityClass){
    	
    	Set<String> classCastSet = new HashSet<>();
    	
    	this.databaseEntityStorage.keySet().stream().forEach(key -> {
    		
    		try {
				
    			Class<?> clazz = Class.forName(key);
    			
    			if(clazz.asSubclass(entityClass) == clazz) {
    				
    				classCastSet.add(clazz.getName());
    			}
				
			} catch (ClassCastException e) { }
    		catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
    	});
    	
    	return classCastSet;
    }
    
    private <K,T> boolean containsKeyInternal(K key, Class<T> entityClass) {
    	
    	return this.databaseEntityStorage.containsKey(entityClass.getName()) ? this.databaseEntityStorage.get(entityClass.getName()).containsKey(key) : false;   
    }
    
    @SuppressWarnings("unchecked")
	private <K,T> T insertOrUpdateInternal(K key, T entity) {
    	
    	if(!this.containsKeyInternal(key, entity.getClass())){
    		
    		this.addEntityStorage(entity.getClass());
    	}
    	
    	((Map<K,T>)this.databaseEntityStorage.get(entity.getClass().getName())).put(key, entity);
    	
    	
    	return entity;
    }
    
    public static Database getInstance(){

	synchronized(MemoryDatabaseImpl.class){

	    if(instance == null){

		instance = new MemoryDatabaseImpl();
	    }
	}

	return instance;
    }
    
    public static Database getNewInstance() {
    	
    	return new MemoryDatabaseImpl(); 
    }
    
    public <K,T> boolean addEntityStorage(Class<T> entityClass) {
    	
    	if(this.databaseEntityStorage.containsKey(entityClass.getName())) {
    		
    		return false;
    	}
    	
    	this.databaseEntityStorage.put(entityClass.getName(), new TreeMap<K,T>());
    	
    	
    	return true;
    	
    }
    
    @SuppressWarnings(value = "unchecked")
    @Override
    public <K,T> T delete(K key, Class<T> entityClass){

	    if(!this.containsKeyInternal(key, entityClass)) {
	    	
	    	return null;
	    }
	    
	    return (T) this.databaseEntityStorage.get(entityClass.getName()).remove(key);
	    
    }

    @SuppressWarnings("unchecked")
    @Override
	public <K,T> T find(K key, Class<T> entityClass){

    	if(!this.containsKeyInternal(key, entityClass)) {
		
    		Set<String> classCastSet = this.findClassCastSet(entityClass);
    		
    		T result = null;
    		
    		for(String className : classCastSet) {
    			
    			result = (T) this.databaseEntityStorage.get(className).get(key);
    			
    			if(result != null) {
    				break;
    			}
    		}
    		
    		return result;
    	}
	
    	return (T) this.databaseEntityStorage.get(entityClass.getName()).get(key);
    }
    
    @SuppressWarnings({ "unchecked" })
	@Override
	public <T> List<T> findAll(Class<T> entityClass) {
    	
    	if(!this.databaseEntityStorage.containsKey(entityClass.getName())) {
    		
    		Set<String> classCastSet = this.findClassCastSet(entityClass);
    		
    		List<T> resultList = new ArrayList<>();
    		
    		for(String className : classCastSet) {
    			
    			resultList.addAll((Collection<T>)this.databaseEntityStorage.get(className).values());
    		}
    		
    		return resultList;
    	}
    	
    	List<T> resultList = new ArrayList<>();
    	
    	resultList.addAll((Collection<T>)this.databaseEntityStorage.get(entityClass.getName()).values());
    	
		return resultList;
	}

	@Override
    public <K,T> T insert(K key, T entity){

    	return this.insertOrUpdateInternal(key, entity);
    }

    
    @Override
    public <K,T> T update(K key, T entity){

    	return this.insertOrUpdateInternal(key, entity);
    }
}