package com.eehniketsjunk.httpserver.config;

public class Configuration {

    public String getWebroot() {
        return webroot;
    }

    public void setWebroot(String webroot) {
        this.webroot = webroot;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    private String webroot;
    private int port;

}
