package com.ruoyi.common.constant;


/**
 * 系统安全秘钥常亮
 * @author Administrator
 *
 */
public class SysSecurityKeyConstant {
	
	/**
	 * 前后台约定md5加签秘钥=====>APP端
	 */
	public static final String md5Key_app="SPNbRiokxGNpAe0ysIEr732QdzcsofDe7O6HNkqd42IXPTJWy2ruCA4jNuwi9yZVryJP1Yrp";
	
	/**
	 * 前后台约定md5加签秘钥=====>web端
	 */
	public static final String md5Key_web="chaIvocpGtFb59EwSJaphONCce0X2NZMISBqYVMgzSh1yY08lKafivBCGH3oHTMg3wgevtOu";
	
	
	/**
	 * RSA加密公钥=====>app端
	 */
	public static final String publicKey_app = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLRBwD8Lpk70Ys9pjcnOx53A6Fd/p0Lq644pfvIzUx3RucOztVrK1QBj707ARipC5cZ2TtyNQMK/Eo2REatG9RZCULu4kNT7AXDS1vWP9X9K1kFBoCpEvLVAXjmxEKmksdc1xdOVeTMgGu0GGtNDK4FVYjvfWTNi81N/F8XDIZ+QIDAQAB";
	
	
	/**
	 * RSA加密私钥=====>app端
	 */
	public static final String privateKey_app = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItEHAPwumTvRiz2mNyc7HncDoV3+nQurrjil+8jNTHdG5w7O1WsrVAGPvTsBGKkLlxnZO3I1Awr8SjZERq0b1FkJQu7iQ1PsBcNLW9Y/1f0rWQUGgKkS8tUBeObEQqaSx1zXF05V5MyAa7QYa00MrgVViO99ZM2LzU38XxcMhn5AgMBAAECgYBnSRsx/O5wf1jfbgBO1f84HXGToTjZw+mHa15dWBJSqfaZNwC5eLiM+iTlBsn10mgj1fbvt5s0b76KmfpqgNlxPHFwim15mffePKyegRyRFCeQm6p0R2JnI0YvINgc2FyTz7kT+ZovQ+vB54VJbB7iPdPbx+eJJdYNyreE/BpUpQJBANY/LTtiHUljHMebCF7VO/EQT1OxwUnT7jfcgiqqbk3itJiERqarXeBpgUmWI8UGTK1lIqRzAeN61+UU0oEGPB8CQQCmaCBJFK/98zpN8Rs2rROL3GLA/dY+3v+SxuGdiDMSqKeoB3dJTEmNxOqM8iN8Lmc9y3arceh1HiMpd6Z4subnAkBE9H51X2W3qfuoTsCJ7S1mr+4oLMzeGyTPu9v1KXdo/+9KK0CmAf+r66kd6wHGNvnU0PkuXomcEnyMEYCS4FPXAkBcNf8OACS1+H4qipyl46qdKfafMwnxtOiykPqcrMiAYmzlM53qRBfOM3w1tkfrnFshZwnPX0ONArJBXEgnQhupAkBT0OVoJxPc6PxUS0qSLbdT5mHvgnlcfO1IqQWylGc95kzXVn1zU/37wJ7eH8X//tBKWxo3rSE5vGvOoFW0tCeQ";
	
	
	/**
	 * RSA加密公钥=====>web端
	 */
	public static final String publicKey_web = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLcNztsZFasKPcQWtTPGCpeohixdAnkfajYN3biythvfhxygezMOuDfQKcr31r/t62fKeA7HrhVH7fa8eFObYoJN2WVcYsTlut/M9CCnAp6nX0QoEA1WSLv3AQKEy96oDr+MccBHGjdhQz4BOdmN1I+UWjVcrbAqwkFTYP29XgsQIDAQAB";
	
	
	/**
	 * RSA加密私钥=====>web端
	 */
	public static final String privateKey_web = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAItw3O2xkVqwo9xBa1M8YKl6iGLF0CeR9qNg3duLK2G9+HHKB7Mw64N9ApyvfWv+3rZ8p4DseuFUft9rx4U5tigk3ZZVxixOW638z0IKcCnqdfRCgQDVZIu/cBAoTL3qgOv4xxwEcaN2FDPgE52Y3Uj5RaNVytsCrCQVNg/b1eCxAgMBAAECgYBtGsdTGMBB9LqR6YIfo7uCgITcvCjt/VC9b1rIoLE2Dl2qM0kpFVsCDDuR++IoMUl0Aj9SKjbPvrxy9rMr9AcpY7ljlYPFMJWgZrmkeqPV+cHl8UqiVRB/jhOYMqC2KQIx2ht8axRs9bpkIP9uENaBIHrww/SgNcyewUhtGEEFHQJBAMm8IfXP09ojv/r7UBOQK1MSUTf+CVQQLYtWvOSDGl8O4xfpT7jDUjobnl3T7aWTseJYS931mUKY3O9o+wE+HHcCQQCw8wone4+7Xp6+II0LLVUD2jxCOk0CgEg5fCBeAfVAApPNcdZEdTXSH2KGh4VqJW5goMiYFNFkO5zn5AYwJ74XAkEApXe9tzz3fNaT8o5OOS2oi6PboHyrNBts7/x+RUTPzaaNWKCTNm5nNKhQto9NPuGtJ4Xa/L0lL33owiqur289swJAEszPsGA1Zi/oZ8Tnl19qn+0o59Rv6CsfAVSJz9MfbnyXnYVRwZY+cKO7ARYW+68cahHLHjVgBek7g3u7mJQwYwJANPZQYhO60ZuZZztpKwjc18JaS0FxVIGb7CgDYPX2OXJn08oMKfNFX8GUkKOuvm7HH6mausuexh7VgGTjcZe52g==";
	
}

