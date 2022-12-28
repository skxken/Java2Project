package com.example.springproject.web;

import com.example.springproject.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springproject.repository.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/release")
public class ReleaseController {
    @Autowired
    ReleaseRepository releaseRepository;
    @Autowired
    CommitRepository commitRepository;
    @RequestMapping("/total1")
    public String getTotal1()
    {
        return String.valueOf(releaseRepository.findAllByRepo(1));
    }
    @RequestMapping("/total2")
    public String getTotal2()
    {
        return String.valueOf(releaseRepository.findAllByRepo(2));
    }
    @RequestMapping("/count1")
    public ArrayList<Integer> getCount1()
    {
        List<String> releases=releaseRepository.findTime(1);
        List<String> commits=commitRepository.findTime(1);
        int[] cnt=new int[releases.size()+10];
        int ptr1=0,ptr2=0;
        while(ptr1<releases.size()&&ptr2<commits.size())
        {
            if(commits.get(ptr2).compareTo(releases.get(ptr1))<=0) {
                cnt[ptr1]++;
                ptr2++;
            }
            else
                ptr1++;
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<releases.size();i++)
            ans.add(cnt[i]);
        return ans;
    }
    @RequestMapping("/count2")
    public ArrayList<Integer> getCount2()
    {
        List<String> releases=releaseRepository.findTime(2);
        List<String> commits=commitRepository.findTime(2);
        int[] cnt=new int[releases.size()+10];
        int ptr1=0,ptr2=0;
        while(ptr1<releases.size()&&ptr2<commits.size())
        {
            if(commits.get(ptr2).compareTo(releases.get(ptr1))<=0) {
                cnt[ptr1]++;
                ptr2++;
            }
            else
                ptr1++;
        }
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<releases.size();i++)
            ans.add(cnt[i]);
        return ans;
    }
    @RequestMapping("/hour1")
    public ArrayList<Integer> getHour1()
    {
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<=23;i++)
            ans.add(commitRepository.findHour(i,1));
        return ans;
    }
    @RequestMapping("/hour2")
    public ArrayList<Integer> getHour2()
    {
        ArrayList<Integer> ans=new ArrayList<>();
        for(int i=0;i<=23;i++)
            ans.add(commitRepository.findHour(i,2));
        return ans;
    }
}
