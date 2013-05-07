package com.makanyuk.handler;

import java.util.List;


public abstract class HandlerEntities<T>{
	
	public void sendEntities(List<T> entities) {
		handlerEntities(entities);
	}
	
	protected abstract void handlerEntities(List<T> entities);	
}
