package com.rizomm.filemanager.business.services;

import com.rizomm.filemanager.business.entities.User;
import java.util.List;

public interface UserService {

  List<User> findAll();

  User create(User user);
}
