package com.background.medicine.entity;

import javax.persistence.*;

@Entity
@Table(name="file")
public class file {
    @Id
    @Column(name="fileID")
    public int fileID;
    @Column(name="fileName")
    public String fileName;
    @Column(name="author")
    public String author;
    @Column(name="cateName")
    public String cateName;
    @Column(name="translator")
    public String translator;
    @Column(name="edition")
    public String edition;
    @Column(name="bookNumber")
    public String bookNumber;
    @Column(name="originalFileLocation")
    public String originalFileLocation;
    @Column(name="fileLocation")
    public String fileLocation;
    @Column(name="readingQuantity")
    public int readingQuantity;
    @Column(name="citedQuantity")
    public int citedQuantity;
    @Column(name="press")
    public String press;
    @Column(name="dynasty")
    public String dynasty;

    public int getFileID() {
        return fileID;
    }

    public void setFileID(int fileID) {
        this.fileID = fileID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }

    public String getOriginalFileLocation() {
        return originalFileLocation;
    }

    public void setOriginalFileLocation(String originalFileLocation) {
        this.originalFileLocation = originalFileLocation;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public int getReadingQuantity() {
        return readingQuantity;
    }

    public void setReadingQuantity(int readingQuantity) {
        this.readingQuantity = readingQuantity;
    }

    public int getCitedQuantity() {
        return citedQuantity;
    }

    public void setCitedQuantity(int citedQuantity) {
        this.citedQuantity = citedQuantity;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getDynasty() {
        return dynasty;
    }

    public void setDynasty(String dynasty) {
        this.dynasty = dynasty;
    }

    @Override
    public String toString() {
        return "file{" +
                "fileID=" + fileID +
                ", fileName='" + fileName + '\'' +
                ", author='" + author + '\'' +
                ", cateName='" + cateName + '\'' +
                ", translator='" + translator + '\'' +
                ", edition='" + edition + '\'' +
                ", bookNumber='" + bookNumber + '\'' +
                ", originalFileLocation='" + originalFileLocation + '\'' +
                ", fileLocation='" + fileLocation + '\'' +
                ", readingQuantity=" + readingQuantity +
                ", citedQuantity=" + citedQuantity +
                ", press='" + press + '\'' +
                ", dynasty='" + dynasty + '\'' +
                '}';
    }
}
