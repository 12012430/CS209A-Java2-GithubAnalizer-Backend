package com.example.cs209a_final_proj.Controller;
import com.example.cs209a_final_proj.Service.RepoService;
import com.example.cs209a_final_proj.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@RestController
@CrossOrigin
@RequestMapping("/repo")
public class RepoController {
    private static Connection con = null;
    private static boolean verbose = false;
    private static Properties prop = new Properties();

    private void openDB() {
        String host="localhost";
        String dbname="postgres";
        String user="postgres";
        String pwd="123456";
        try {
            Class.forName("org.postgresql.Driver");
        } catch (Exception e) {
            System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
            System.exit(1);
        }
        String url = "jdbc:postgresql://" + host + "/" + dbname;
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        try {
            con = DriverManager.getConnection(url, props);
            if (verbose) {
                System.out.println("Successfully connected to the database "
                        + dbname + " as " + user);
            }
        } catch (SQLException e) {
            System.err.println("Database connection failed");
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
    private void closeDB() {
        if (con != null) {
            try {
                con.close();
                con = null;
            } catch (Exception e) {
                System.err.println("Error closing the database connection");
                System.err.println(e.getMessage());
            }
        }
    }
    @Autowired
    private RepoService repoService;
    @RequestMapping("/findAll")
    public List<Repo> findAll(){
        return repoService.findAll();
    }
    @GetMapping("/developers/{repo_id}")
    public List<Developer> developers(@PathVariable("repo_id") int repo_id){
        System.out.println(repo_id);
        List<Developer> ld = new ArrayList<>();
        openDB();
        try {
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select Count(developer_name) as num ,repo_id,developer_name  from commit where repo_id= "+repo_id+" group by developer_name,repo_id order by num desc limit 9");
            while (rs.next()) {
                Developer d = new Developer();
                d.setName(rs.getString("developer_name"));
                d.setCommits(rs.getInt("num"));
                ld.add(d);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error in retrieving data");
            System.err.println(e.getMessage());
        }
        return ld;
    }

    @GetMapping("/issuestatus/{repo_id}")
    public List<IssueStatus> issuestatus(@PathVariable("repo_id") int repo_id){
        System.out.println(repo_id);
        List<IssueStatus> is = new ArrayList<>();
        openDB();
        try {
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select count(issue_id) as num, state from issue where repo_id="+repo_id+" group by state");
            IssueStatus d = new IssueStatus();
            while (rs.next()) {

                String state = rs.getString("state");
                System.out.println(state);
                if(state.equals("open")) d.setOpen_issues(rs.getInt("num"));
                else d.setClosed_issues(rs.getInt("num"));

            }
            rs.close();
            stmt.close();
            is.add(d);
        } catch (SQLException e) {
            System.err.println("Error in retrieving data");
            System.err.println(e.getMessage());
        }
        return is;
    }

    @GetMapping("/resolution/{repo_id}")
    public List<Resolution> resolutions(@PathVariable("repo_id") int repo_id){
        List<Resolution> r=new ArrayList<>();
        openDB();
        try{
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select  extract('epoch' from (closed_at-create_at)/3600.00/24.00) as days from issue where repo_id="+repo_id+" and closed_at is not null ");
            Resolution d = new Resolution();
            int three=0;
            int week=0;
            int month=0;
            int more=0;
            while (rs.next()) {
                float i=rs.getFloat("days");
                if(i<=3.0) three++;
                else if(i<=7.0) week++;
                else if(i<=30.0) month++;
                else more++;
            }
            rs.close();
            stmt.close();
            d.setThree(three);
            d.setWeek(week);
            d.setMonth(month);
            d.setMore(more);
            r.add(d);
        } catch (SQLException e) {
            System.err.println("Error in retrieving data");
            System.err.println(e.getMessage());
        }
        return r;
    }

    @GetMapping("/commitbr/{repo_id}")
    public List<CommitBR> commitbr(@PathVariable("repo_id") int repo_id){
        List<CommitBR> c=new ArrayList<>();
        openDB();
        try{
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select * from release where repo_id="+repo_id+" order by released_at desc limit 9");
            while (rs.next()) {
                CommitBR d = new CommitBR();
                d.setReleaseName(rs.getString("release_name"));
                d.setCommits(rs.getInt("commit_between_release"));
                c.add(d);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error in retrieving data");
            System.err.println(e.getMessage());
        }
        return c;
    }

    @GetMapping("/commitweek/{repo_id}")
    public List<CommitWeek> commitweeks(@PathVariable("repo_id") int repo_id){
        List<CommitWeek> c=new ArrayList<>();
        openDB();
        try{
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select extract(dow from committed_at::date AT TIME ZONE 'Asia/shanghai')+1 as week from commit where repo_id="+repo_id);
            CommitWeek d = new CommitWeek();
            int weekday=0;
            int weekend=0;
            while (rs.next()) {
                int i;
                i=rs.getInt("week");
                if(i==1||i==7) weekend++;
                else weekday++;
            }
            d.setWeekday(weekday);
            d.setWeekend(weekend);
            c.add(d);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error in retrieving data");
            System.err.println(e.getMessage());
        }
        return c;
    }

    @GetMapping("/commitday/{repo_id}")
    public List<CommitDay> commitdays(@PathVariable("repo_id") int repo_id){
        List<CommitDay> c=new ArrayList<>();
        openDB();
        try{
            java.sql.Statement stmt = con.createStatement();
            java.sql.ResultSet rs = stmt.executeQuery("select extract(hour from committed_at AT TIME ZONE 'Asia/shanghai') as hour from commit where repo_id="+repo_id);
            CommitDay d = new CommitDay();
            int morning=0;
            int afternoon=0;
            int evening=0;
            while (rs.next()) {
                int i;
                i=rs.getInt("hour");
                if(i>=5&&i<13) morning++;
                else if(i>=13&&i<21) afternoon++;
                else evening++;
            }
            d.setMorning(morning);
            d.setAfternoon(afternoon);
            d.setEvening(evening);
            c.add(d);
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            System.err.println("Error in retrieving data");
            System.err.println(e.getMessage());
        }
        return c;
    }

}
