package com.theirornyard.IronSnap.services;

import com.theirornyard.IronSnap.entities.IronPicture;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Keith on 5/15/17.
 */
public interface IronSnapFileRepository extends CrudRepository<IronPicture, Integer>{

}