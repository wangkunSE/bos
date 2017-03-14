package bos.sshproject.user.service;

import org.springframework.stereotype.Controller;

import bos.sshproject.user.domain.User;

public interface IUserService {

	User login(User model);

	void editPassword(String password, String string);


}
