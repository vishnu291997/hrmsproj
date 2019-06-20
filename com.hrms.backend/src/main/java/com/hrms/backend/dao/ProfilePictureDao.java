package com.hrms.backend.dao;

import com.hrms.backend.modal.ProfilePicture;

public interface ProfilePictureDao {
	void uploadProfilePicture(ProfilePicture profilePicture);
	ProfilePicture  getProfilePicture(String email);
}
