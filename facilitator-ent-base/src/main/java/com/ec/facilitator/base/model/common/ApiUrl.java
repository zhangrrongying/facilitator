package com.ec.facilitator.base.model.common;

public enum ApiUrl {
	
	
	NOTIFY("/v0/sys/notify");//通知用户


    public String url ;

    private ApiUrl(String url) {
        this.url = url;
    }

}
