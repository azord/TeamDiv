package com.example.soccerrandomizer.Controller;

import com.example.soccerrandomizer.Model.Post;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.swing.plaf.IconUIResource;
import java.util.*;
import java.lang.Object;

@Controller
public class MainController {
    private String[]common;
    private int teams;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Sport Teams Randomizer");
        return "home";
    }

    @PostMapping("/levels")
    public String playerList(Model model, @RequestParam String players, @RequestParam Integer teams) {
        this.common = players.split(System.getProperty("line.separator"));
        this.teams = teams;
        model.addAttribute("players", common);

        return "levels";
    }

    @PostMapping("/display")
    public String display(Model model, @RequestParam Integer [] level) {

        PriorityQueue<Map.Entry<String, Integer>> maxPQ = new PriorityQueue<>(
                (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        for (int i = 0; i < level.length; i++) {
            maxPQ.add(new AbstractMap.SimpleEntry<String, Integer>(common[i], level[i]));
        }

        List<List<String>> finalTeams = new ArrayList<List<String>>(teams);

        for(int i = 0; i < teams; i++)  {
            finalTeams.add(new ArrayList<String>());
        }
        int i = 0;
        while (!maxPQ.isEmpty()) {
            finalTeams.get(i).add(maxPQ.peek().getKey());
            maxPQ.poll();
            i++;
            if (i == teams) {
                i = 0;
            }
        }

        model.addAttribute("finalTeams", finalTeams);

        return "display";
    }


}
