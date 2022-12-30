package com.example.cs209a_final_proj.Service;

import com.example.cs209a_final_proj.entity.Developer;
import com.example.cs209a_final_proj.entity.IssueStatus;
import com.example.cs209a_final_proj.entity.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.cs209a_final_proj.Mapper.RepoMapper;
import java.util.List;

@Service
public class RepoService {
    @Autowired
    private RepoMapper repoMapper;

    public List<Repo> findAll(){
        return repoMapper.findAll();
    }

    public List<Developer> developers(int repo_id){
        return repoMapper.developers();
    }

    public List<IssueStatus> issueStatus(int repo_id){
        return repoMapper.issueStatus();
    }

}
