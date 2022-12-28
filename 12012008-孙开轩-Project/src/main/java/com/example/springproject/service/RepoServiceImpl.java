package com.example.springproject.service;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import com.example.springproject.model.*;
import com.example.springproject.repository.*;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoServiceImpl implements RepoService {

    private final DeveloperRepository developerRepository;
    private final CommitRepository commitRepository;
    private final IssueRepository issueRepository;
    private final ReleaseRepository releaseRepository;
    private final OpenIssueRepository openIssueRepository;

    @Autowired
    public RepoServiceImpl(DeveloperRepository developerRepository, CommitRepository commitRepository, IssueRepository issueRepository, ReleaseRepository releaseRepository, OpenIssueRepository openIssueRepository) {
        this.developerRepository = developerRepository;
        this.commitRepository = commitRepository;
        this.issueRepository = issueRepository;
        this.releaseRepository = releaseRepository;
        this.openIssueRepository = openIssueRepository;
    }


    class node {
        public String username, nickname, email;

        public node(String username, String nickname, String email) {
            this.username = username;
            this.nickname = nickname;
            this.email = email;
        }
    }

    @Override
    public void deleteData() {
        developerRepository.deleteAll();
        commitRepository.deleteAll();
        issueRepository.deleteAll();
        releaseRepository.deleteAll();
        openIssueRepository.deleteAll();
    }

    @Override
    public void pushData(String repo_name, int repo_num) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request;
        int flg = 0, cnt = 0, commit_time; //part1,5
        String username, nickname, email, id, commit_t;
        Map<String, node> mi = new HashMap<>();
        Map<String, Integer> mt = new HashMap<>();
        List<commit> commits = new ArrayList<>();
        String u = "https://api.github.com/repos/" + repo_name + "/commits?per_page=100&page=";
        while (flg == 0) {
            cnt++;
            request = HttpRequest.newBuilder()
                    .uri(URI.create(u + cnt))
                    .setHeader("Authorization", "Bearer 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")
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            assert response != null;
            String str2 = response.body();
            JSONArray jsonArray = JSONArray.parseArray(str2);
            if (jsonArray.size() < 100) {
                flg = 1;
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                if (o.get("author") == null)
                    continue;
                JSONObject j = JSONObject.parseObject(o.get("author").toString());
                username = j.get("login").toString();
                id = j.get("id").toString();
                JSONObject j1 = JSONObject.parseObject(o.get("commit").toString());
                JSONObject j2 = JSONObject.parseObject(j1.get("author").toString());
                nickname = j2.get("name").toString();
                email = j2.get("email").toString();
                commit_t = j2.get("date").toString();
                commit c = new commit(commit_t, (commit_t.charAt(11) - '0') * 10 + commit_t.charAt(12) - '0', repo_num);
                commits.add(c);
                node n = new node(username, nickname, email);
                mi.put(id, n);
                if (!mt.containsKey(id))
                    mt.put(id, 1);
                else
                    mt.replace(id, mt.get(id) + 1);
            }
        }
        List<developer> developers = new ArrayList<>();
        for (Map.Entry<String, node> entry : mi.entrySet()) {
            id = entry.getKey();
            username = entry.getValue().username;
            nickname = entry.getValue().nickname;
            email = entry.getValue().email;
            commit_time = mt.get(id);
            developers.add(new developer(username, nickname, email, id, commit_time, repo_num));
        }
        developerRepository.saveAll(developers);
        commitRepository.saveAll(commits);
        u = "https://api.github.com/repos/" + repo_name + "/issues/events?per_page=100&page=";//part 3
        flg = 0;
        cnt = 0;
        String start_time, end_time;
        List<issue> issues = new ArrayList<>();
        while (flg == 0) {
            cnt++;
            //System.out.println(u+cnt);
            request = HttpRequest.newBuilder()
                    .uri(URI.create(u + cnt))
                    .setHeader("Authorization", "Bearer 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")
                    .build();
            HttpResponse<String> response = null;
            try {
                response = client.send(request, HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
            assert response != null;
            String str2 = response.body();
            JSONArray jsonArray = JSONArray.parseArray(str2);
            if (jsonArray.size() < 100)
                flg = 1;
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject o = jsonArray.getJSONObject(i);
                //System.out.println(u+cnt);
                if (!o.get("event").equals("closed"))
                    continue;
                JSONObject issue = JSONObject.parseObject(o.get("issue").toString());
                start_time = issue.get("created_at").toString();
                if (issue.get("closed_at") == null) {
                    //System.out.println("1");
                    continue;
                }
                end_time = issue.get("closed_at").toString();
                int year = (start_time.charAt(0) - '0') * 1000 + (start_time.charAt(1) - '0') * 100 + (start_time.charAt(2) - '0') * 10 + (start_time.charAt(3) - '0');
                int month = (start_time.charAt(5) - '0') * 10 + start_time.charAt(6) - '0';
                int day = (start_time.charAt(8) - '0') * 10 + start_time.charAt(9) - '0';
                LocalDate startTime = LocalDate.of(year, month, day);
                year = (end_time.charAt(0) - '0') * 1000 + (end_time.charAt(1) - '0') * 100 + (end_time.charAt(2) - '0') * 10 + (end_time.charAt(3) - '0');
                month = (end_time.charAt(5) - '0') * 10 + end_time.charAt(6) - '0';
                day = (end_time.charAt(8) - '0') * 10 + end_time.charAt(9) - '0';
                LocalDate endTime = LocalDate.of(year, month, day);
                long days = ChronoUnit.DAYS.between(startTime, endTime);
                issues.add(new issue(start_time, end_time, (int) days, repo_num));
            }
        }
        issueRepository.saveAll(issues);

        u = "https://api.github.com/repos/" + repo_name + "/releases?per_page=100&page=1";//part4
        List<release> releases = new ArrayList<>();
        request = HttpRequest.newBuilder()
                .uri(URI.create(u))
                .setHeader("Authorization", "Bearer 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")
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        String str2 = response.body();
        JSONArray jsonArray = JSONArray.parseArray(str2);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject o = jsonArray.getJSONObject(i);
            releases.add(new release(o.get("published_at").toString(), repo_num));
        }
        releaseRepository.saveAll(releases);

        u = "https://api.github.com/repos/" + repo_name;//part2
        request = HttpRequest.newBuilder()
                .uri(URI.create(u))
                .setHeader("Authorization", "Bearer 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")
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        String str = response.body();
        JSONObject jsonObject = JSONObject.parseObject(str);
        openIssueRepository.save(new open_issue(Integer.parseInt(jsonObject.get("open_issues").toString()), repo_num));
    }
}
