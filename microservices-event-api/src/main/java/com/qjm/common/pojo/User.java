package com.qjm.common.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class User {

  private Long id;

  private Date createdAt;

  private Date lastModified;

  private String email;

  private String firstName;

  private String lastName;

  private String username;

  private String phone;

  private String password;
}
