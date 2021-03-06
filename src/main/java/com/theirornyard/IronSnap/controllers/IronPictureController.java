package com.theirornyard.IronSnap.controllers;


import com.theirornyard.IronSnap.entities.DeleteRunnable;
import com.theirornyard.IronSnap.entities.IronPicture;
import com.theirornyard.IronSnap.services.IronSnapFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Keith on 5/15/17.
 */
@RestController
public class IronPictureController {
@Autowired
    IronSnapFileRepository pictures;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public void upload(MultipartFile file, int expirationTime, HttpServletResponse response) throws IOException {
        File dir = new File("public/files");
        dir.mkdirs();
        File f = File.createTempFile("file", file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        IronPicture ironPicture = new IronPicture(f.getName(), file.getOriginalFilename(), expirationTime);
        pictures.save(ironPicture);

        response.sendRedirect("/");
    }
    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public List<IronPicture> getPictures() throws InterruptedException {
        List<IronPicture> ironPictures = (List)pictures.findAll();
        for(IronPicture picture : ironPictures){
            Thread t = new Thread(new DeleteRunnable(pictures, picture) );

            t.start();
        }

        return ironPictures;
    }


}
