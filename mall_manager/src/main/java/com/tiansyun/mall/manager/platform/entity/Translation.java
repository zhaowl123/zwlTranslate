package com.tiansyun.mall.manager.platform.entity;

/**
 * @Author: ZYJ
 * @Description: 翻译实体类
 * @Date: Created in 2021/8/12 8:45
 */
public class Translation {

    private static final long serialVersionUID = 1L;

    private int beTrColumn;         // 需要翻译的列数
    private int trColumn;           // 翻译完成填写列数
    private int startRow;           // 翻译开始读取行数
    private String beTrLanguage;    // 需要翻译列语言 en ja zh-Hans
    private String trLanguage;      // 翻译成什么语言
    private String word;            // 需要翻译的文字

    public int getBeTrColumn() {
        return beTrColumn;
    }

    public void setBeTrColumn(int beTrColumn) {
        this.beTrColumn = beTrColumn;
    }

    public int getTrColumn() {
        return trColumn;
    }

    public void setTrColumn(int trColumn) {
        this.trColumn = trColumn;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public String getBeTrLanguage() {
        return beTrLanguage;
    }

    public void setBeTrLanguage(String beTrLanguage) {
        this.beTrLanguage = beTrLanguage;
    }

    public String getTrLanguage() {
        return trLanguage;
    }

    public void setTrLanguage(String trLanguage) {
        this.trLanguage = trLanguage;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
