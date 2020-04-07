package com.example.longecological.utils.message;

public class EmailAccountData {
	
	private String myEmailAccount;
	
	private String myEmailPassword;

	public EmailAccountData(int num){
		//邮箱1号
		String myEmailAccount1 = "13410631036@163.com";
	    String myEmailPassword1 = "jjst123";
	    //邮箱2号
	    String myEmailAccount2 = "a13410631036@163.com";
	    String myEmailPassword2 = "jjst456";
	    //邮箱3号
	    String myEmailAccount3 = "b13410631036@163.com";
	    String myEmailPassword3 = "jjst789";
	    //邮箱4号
	    String myEmailAccount4 = "a18878139172@163.com";
	    String myEmailPassword4 = "jjst258";
	    //邮箱5号
	    String myEmailAccount5 = "b18878139172@163.com";
	    String myEmailPassword5 = "jjst369";
	    //邮箱6号
	    String myEmailAccount6 = "c18878139172@163.com";
	    String myEmailPassword6 = "jjst111";
	    //邮箱7号
	    String myEmailAccount7 = "a15607926866@163.com";
	    String myEmailPassword7 = "jjst222";
	    //邮箱8号
	    String myEmailAccount8 = "b15607926866@163.com";
	    String myEmailPassword8 = "jjst333";
	    //邮箱9号
	    String myEmailAccount9 = "c15607926866@163.com";
	    String myEmailPassword9 = "jjst444";
	    //邮箱10号
	    String myEmailAccount10 = "a18107558045@163.com";
	    String myEmailPassword10 = "jjst555";
	    //邮箱11号
	    String myEmailAccount11 = "b18107558045@163.com";
	    String myEmailPassword11 = "jjst666";
	    //邮箱12号
	    String myEmailAccount12 = "c18107558045@163.com";
	    String myEmailPassword12 = "jjst777";
	
	    
	    //获取随机数
//	    int num = new Random().nextInt(100000)%10;
	    //取值
	    if(num == 0){
	    	this.myEmailAccount = myEmailAccount1;
	    	this.myEmailPassword = myEmailPassword1;
	    }else if(num == 1){
	    	this.myEmailAccount = myEmailAccount2;
	    	this.myEmailPassword = myEmailPassword2;
	    }else if(num ==2){
	    	this.myEmailAccount = myEmailAccount3;
	    	this.myEmailPassword = myEmailPassword3;
	    }else if(num == 3){
	    	this.myEmailAccount = myEmailAccount4;
	    	this.myEmailPassword = myEmailPassword4;
	    }else if(num == 4){
	    	this.myEmailAccount = myEmailAccount5;
	    	this.myEmailPassword = myEmailPassword5;
	    }else if(num == 5){
	    	this.myEmailAccount = myEmailAccount6;
	    	this.myEmailPassword = myEmailPassword6;
	    }else if(num == 6){
	    	this.myEmailAccount = myEmailAccount7;
	    	this.myEmailPassword = myEmailPassword7;
	    }else if(num == 7){
	    	this.myEmailAccount = myEmailAccount8;
	    	this.myEmailPassword = myEmailPassword8;
	    }else if(num == 8){
	    	this.myEmailAccount = myEmailAccount9;
	    	this.myEmailPassword = myEmailPassword9;
	    }else if(num == 9){
	    	this.myEmailAccount = myEmailAccount10;
	    	this.myEmailPassword = myEmailPassword10;
	    }else if(num == 10){
	    	this.myEmailAccount = myEmailAccount11;
	    	this.myEmailPassword = myEmailPassword11;
	    }else{
	    	this.myEmailAccount = myEmailAccount12;
	    	this.myEmailPassword = myEmailPassword12;
	    }
	}

	public String getMyEmailAccount() {
		return myEmailAccount;
	}

	public void setMyEmailAccount(String myEmailAccount) {
		this.myEmailAccount = myEmailAccount;
	}

	public String getMyEmailPassword() {
		return myEmailPassword;
	}

	public void setMyEmailPassword(String myEmailPassword) {
		this.myEmailPassword = myEmailPassword;
	} 
	
}
