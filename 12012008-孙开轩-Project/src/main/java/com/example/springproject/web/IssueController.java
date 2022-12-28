package com.example.springproject.web;

import com.example.springproject.model.developer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.springproject.repository.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    OpenIssueRepository openIssueRepository;
    @RequestMapping("/getInfo1")
    public ArrayList<Double> getInfo1()
    {
        ArrayList<Double> ans=new ArrayList<>();
        ans.add((double) openIssueRepository.findAllByRepo(1));
        ans.add((double) issueRepository.findAllByRepo(1));
        List<Integer> list=issueRepository.findDaysByRepo(1);
        double tot=0,num=0,avg=0,maxnum=0,S=0;
        for(int i=0;i<list.size();i++)
        {
            tot+= list.get(i);
            num++;
            if(list.get(i)>maxnum)
                maxnum=list.get(i);
        }
        avg=tot/num;
        ans.add(avg);
        ans.add(maxnum);
        for(int i=0;i< list.size();i++)
            S+=(list.get(i)-avg)*(list.get(i)-avg);
        S/=num;
        ans.add(Math.pow(S,0.5));
        return ans;
    }
    @RequestMapping("/getInfo2")
    public ArrayList<Double> getInfo2()
    {
        ArrayList<Double> ans=new ArrayList<>();
        ans.add((double) openIssueRepository.findAllByRepo(2));
        ans.add((double) issueRepository.findAllByRepo(2));
        List<Integer> list=issueRepository.findDaysByRepo(2);
        double tot=0,num=0,avg=0,maxnum=0,S=0;
        for(int i=0;i<list.size();i++)
        {
            tot+= list.get(i);
            num++;
            if(list.get(i)>maxnum)
                maxnum=list.get(i);
        }
        avg=tot/num;
        ans.add(avg);
        ans.add(maxnum);
        for(int i=0;i< list.size();i++)
            S+=(list.get(i)-avg)*(list.get(i)-avg);
        S/=num;
        ans.add(Math.pow(S,0.5));
        System.out.println(ans);
        return ans;
    }
}
