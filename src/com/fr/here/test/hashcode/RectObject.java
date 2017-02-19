package com.fr.here.test.hashcode;

public class RectObject {
	public int x;
	public int y;
	public RectObject(int x,int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public int hashCode(){
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
	@Override
	public boolean equals(Object obj){
		//return false;
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		final RectObject other = (RectObject)obj;
		if(x != other.x){
			return false;
		}
		if(y != other.y){
			return false;
		}
		return true;
	}
}
