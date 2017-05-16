package com.theirornyard.IronSnap.entities;

import com.theirornyard.IronSnap.services.IronSnapFileRepository;

import java.io.File;

/**
 * Created by Keith on 5/16/17.
 */
public class DeleteRunnable implements Runnable {
    IronSnapFileRepository repo;

    IronPicture file;

    public DeleteRunnable(IronSnapFileRepository repo, IronPicture file) {
        this.repo = repo;
        this.file = file;
    }

    @Override
    public void run() {
        // wait for file.expirationTime seconds
        try {
            Thread.sleep(file.getExpirationTime() * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // delete the file from the fileSystem
        // find the file
        File f = new File("public/files/" + file.getFilename());

        // delete it
        f.delete();

        // delete the file from the database
        repo.delete(file);
    }
}
