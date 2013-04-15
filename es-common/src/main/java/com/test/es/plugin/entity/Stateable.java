package com.test.es.plugin.entity;

/**
 *User:xinmingmao<br>
 *Date:2013-4-15<br>
 *Time:下午7:07:20
 */
public interface Stateable<T extends Enum<? extends Stateable.State>> {
	public void setState(T state);
	
	public State getState();
	
	public static interface State{
		
	}
	
	public static  enum AuditState implements State{
		waiting("等待审核"),fail("审核失败"),success("审核成功");
		
		private final String info;
		
		private AuditState(String info){
			this.info = info;
		}
		
		public String getInfo(){
			return this.info;
		}
	}
	
	public static enum ShowStatus implements State{
		hide("不显示"),show("显示");
		private final String info ;
		private ShowStatus(String info){
			this.info = info ;
		}
		
		public String getInfo(){
			return this.info ;
		}
	}

}

