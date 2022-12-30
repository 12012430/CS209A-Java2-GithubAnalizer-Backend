package com.example.cs209a_final_proj.Mapper;

import com.example.cs209a_final_proj.entity.Developer;
import com.example.cs209a_final_proj.entity.IssueStatus;
import com.example.cs209a_final_proj.entity.Repo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RepoMapper {

  List<Repo> findAll();

  List<Developer> developers();

  List<IssueStatus> issueStatus();
}