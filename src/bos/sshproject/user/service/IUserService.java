package bos.sshproject.user.service;

import org.springframework.stereotype.Controller;

import bos.sshproject.user.domin.User;

public interface IUserService {

	User login(User model);

}
