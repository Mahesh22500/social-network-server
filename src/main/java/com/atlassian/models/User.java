package com.atlassian.models;



import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;



@Entity
public class User {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	
	private List<Integer> followers =new ArrayList<>();
	
	private List<Integer> following = new ArrayList<>();
	
	private String gender;
	
	@JsonIgnore
	@ManyToMany
	private List<Post> savedPosts = new ArrayList<>();
	
	public User() {
		
	}

	











	public User(Integer id, String firstName, String lastName, String email, String password, List<Integer> followers,
			List<Integer> following, String gender, List<Post> savedPosts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.followers = followers;
		this.following = following;
		this.gender = gender;
		this.savedPosts = savedPosts;
	}













	public List<Post> getSavedPosts() {
		return savedPosts;
	}













	public void setSavedPosts(List<Post> savedPosts) {
		this.savedPosts = savedPosts;
	}













	public List<Integer> getFollowers() {
		return followers;
	}







	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}







	public List<Integer> getFollowing() {
		return following;
	}







	public void setFollowing(List<Integer> following) {
		this.following = following;
	}







	public String getGender() {
		return gender;
	}







	public void setGender(String gender) {
		this.gender = gender;
	}







	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
