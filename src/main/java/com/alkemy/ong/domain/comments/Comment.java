package com.alkemy.ong.domain.comments;

import com.alkemy.ong.domain.news.News;
import com.alkemy.ong.domain.users.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Comment {
  Long id;
  User user;
  News news;
}
