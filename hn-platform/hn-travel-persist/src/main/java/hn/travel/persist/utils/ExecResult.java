package hn.travel.persist.utils;

/**
 * 执行脚本后的返回�
 */
public class ExecResult{
	/**
	 * 执行脚本状�?�?-表示成功；非0-表示不成�?
	 */
	private int status = 0;
	private String msg = "";
	ExecResult(int status, String msg){
		this.status = status;
		this.msg = msg;
	}
	public int getStatus() {return status;}
	public void setStatus(int status) {this.status = status;}
	public String getMsg() {return msg;}
	public void setMsg(String msg) {this.msg = msg;}
}
