-- developer
select repo_id,count(num) as total_developers from(select Count(developer_name) as num ,repo_id  from commit group by developer_name,repo_id) as table1 group by repo_id

-- open issues
select repo_id,Count(issue_id) as total_open_issues from issue where state = 'open' group by repo_id

-- closed issues
select repo_id,Count(issue_id) as total_closed_issues from issue where state = 'close' group by repo_id

-- total releases
select repo_id,count(release_id) as total_releases from release group by repo_id;

--typical issue Resolution time
select repo_id,sum(delta) / count(delta) as issue_resolution_time from (select repo_id,closed_at - issue.create_at as delta from issue where closed_at is not null) as table1 group by repo_id;

select *
from (select developer_name, count(sha) as count from commit group by developer_name) as table1
order by count desc;
--哪个开发者commit最多


-- Insert commit_between_release into database
update release
set commit_between_release =table5.commit_between_release
from (select id, release_name, released_at, id - pre_id - 1 as commit_between_release
      from (select id, release_name, released_at, coalesce(pre_id, 0) as pre_id
            from (select id,
                         release_name,
                         released_at,
                         tag,
                         lag(id, 1)
                         over (
                             order by id) as pre_id
                  from (select row_number() over () as id, release_name, released_at, tag
                        from (select release_name, released_at, 'release' as tag
                              from release
                              union
                              select sha as release_name, committed_at as released_at, 'commit' as tag
                              from commit
                              order by released_at) as table1) as table2
                  where tag = 'release') as table3) as table4
      order by id desc) as table5
where release.release_name = table5.release_name;
