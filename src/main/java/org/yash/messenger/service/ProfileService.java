package org.yash.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yash.messenger.database.DatabaseClass;
import org.yash.messenger.model.Profile;

public class ProfileService {
	private static Map<String, Profile> profiles = DatabaseClass.getProfiles();
	
	private static long counter = 0;
	
	static {
		long id = ++counter;
		profiles.put("yash", new Profile(id, "yash"));
	}
	
	public ProfileService(){

	}
	
	public List<Profile> getAllProfiles(){

		return new ArrayList<>(profiles.values());
	}
	
	public Profile getProfile(String profileName){
		return profiles.get(profileName);
	}
	
	public Profile addProfile(Profile profile){
		profile.setId(++counter);
		profiles.put(profile.getName(), new Profile(profile.getId(), profile.getName()));
		return profiles.get(profile.getName());
	}
	
	public Profile updateProfile(Profile profile){
		if (profile.getName().isEmpty()) {
			return null;
		}
		profiles.put(profile.getName(), profile);
		return profile;
	}
	
	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
}
