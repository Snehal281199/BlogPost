package com.example.demo.DTO;



import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.example.demo.Entity.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

	
private int id;
    
  @NotEmpty
	private String name;
   @Email(message="your Email address is not Valid !")
   @Size(min=4,message="Username name must be min of 4 characters")
	private String email;
  @NotEmpty
  @Size(min=3,max=10,message="Password must be min of 3 characters and max of 10 characters")
	private String password;
  @NotEmpty
	private String about;

  private Set<RoleDto> roles=new HashSet<>();
//password field is ignored during serialization process || use this when u know your field contains
  //sensitive information
	/*
	 * @JsonIgnore public String getPassword() { return this.password; }
	 * 
	 * @JsonProperty public void setPassword(String password) {
	 * this.password=password; }
	 */
}
