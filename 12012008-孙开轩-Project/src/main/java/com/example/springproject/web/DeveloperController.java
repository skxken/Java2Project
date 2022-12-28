package com.example.springproject.web;

import com.example.springproject.model.developer;
import com.example.springproject.repository.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/developer")
public class DeveloperController {
    @Autowired
    DeveloperRepository developerRepository;

    @RequestMapping("/total1")
    public String getTotal1() {
        return String.valueOf(developerRepository.findAllByRepo(1));
    }

    @RequestMapping("/total2")
    public String getTotal2() {
        return String.valueOf(developerRepository.findAllByRepo(2));
    }

    @RequestMapping("/top1")
    public ArrayList<String> getTop1() {
        ArrayList<String> ans = new ArrayList<>();
        List<developer> list = developerRepository.findByCommit_times(1);
        for (int i = 0; i < 5; i++) {
            ans.add(list.get(i).getUsername());
            ans.add(String.valueOf(list.get(i).getCommit_times()));
            ans.add(list.get(i).getNickname());
            ans.add(list.get(i).getEmail());
            ans.add(list.get(i).getGithub_id());
        }
        return ans;
    }

    @RequestMapping("/top2")
    public ArrayList<String> getTop2() {
        ArrayList<String> ans = new ArrayList<>();
        List<developer> list = developerRepository.findByCommit_times(2);
        for (int i = 0; i < 5; i++) {
            ans.add(list.get(i).getUsername());
            ans.add(String.valueOf(list.get(i).getCommit_times()));
            ans.add(list.get(i).getNickname());
            ans.add(list.get(i).getEmail());
            ans.add(list.get(i).getGithub_id());
        }
        return ans;
    }
}
