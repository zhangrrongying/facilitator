package com.ec.facilitator.base.mail;

/**
 * 邮件内容类型enum html：网页模式；plain：文本模式
 * @author 张荣英
 * @date 2017年5月11日 下午4:23:37
 */
public enum ContentFormat {
	Html(0),Plain(1);
	
	private int value = 1;
	
	private ContentFormat(int value) {    
        this.value = value;
    }
	
	public int value() {
        return this.value;
    }
}
