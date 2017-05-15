package com.theirornyard.IronSnap.entities;

import javax.persistence.*;

/**
 * Created by Keith on 5/15/17.
 */
@Entity
@Table(name = "pictures")
public class IronPicture {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String filename;

    @Column(nullable = false)
    String originalFilename;

    public IronPicture() {
    }

    public IronPicture(String filename, String originalFilename) {
        this.filename = filename;
        this.originalFilename = originalFilename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }
}