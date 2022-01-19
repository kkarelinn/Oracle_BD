package com.bd.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class ParseXMLConf {

    private Document answer = null;

    public ParseXMLConf(){

        try {
            FileInputStream fis = new FileInputStream("resources/dataSource.xml");
            this.answer = Jsoup.parse(fis, null, "", Parser.xmlParser());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getSource() {
        return Objects.requireNonNull(answer.selectFirst("source-name")).text();
    }

    public String getUrl() {
        return Objects.requireNonNull(answer.selectFirst("connection-url")).text();
    }

    public String getDriverClass() {
        return Objects.requireNonNull(answer.selectFirst("driver-class")).text();
    }

    public String getUserName() {
        return Objects.requireNonNull(answer.selectFirst("user-name")).text();
    }

    public String getPassword(){
        return Objects.requireNonNull(answer.selectFirst("password")).text();
    }

    }

