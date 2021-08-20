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
    private Integer[]levels;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Sport Teams Randomizer");
        return "home";
    }

    @PostMapping("/levels")
    public String playerList(Model model, @RequestParam(required = false) String players,
                             @RequestParam(required = false) Integer teams) {
        if (players != null) {
            this.common = players.split(System.getProperty("line.separator"));
        }
        if (teams != null) {
            this.teams = teams;
        }
        model.addAttribute("players", common);

        return "levels";
    }

    @PostMapping("/display")
    public String display(Model model, @RequestParam(required = false) Integer [] level) {
        if (level.length == 0) {
            level = this.levels;
        } else {
            this.levels = level;
        }

        List<String> level1 = new ArrayList<>();
        List<String> level2 = new ArrayList<>();
        List<String> level3 = new ArrayList<>();

        List<List<String>> finalTeams = new ArrayList<List<String>>(teams);

        for (int i = 0; i < level.length; i++) {
            if (level[i] == 1) {
                level1.add(common[i]);
            } else if (level[i] == 2) {
                level2.add(common[i]);
            } else {
                level3.add(common[i]);
            }
        }

        Collections.shuffle(level1);
        Collections.shuffle(level2);
        Collections.shuffle(level3);

        for(int i = 0; i < teams; i++)  {
            finalTeams.add(new ArrayList<String>());
        }

        int i = 0;
        int teamIndex = 0;

            while (i < (int)level1.size()) {
                finalTeams.get(teamIndex).add(level1.get(i));
                i++;
                teamIndex++;
                if (teamIndex == teams) {
                    teamIndex = 0;
                }
            }
            i = 0;
            while (i < level2.size()) {
                finalTeams.get(teamIndex).add(level2.get(i));
                i++;
                teamIndex++;
                if (teamIndex == teams) {
                    teamIndex = 0;
                }
            }
            i = 0;
        while (i < level3.size()) {
            finalTeams.get(teamIndex).add(level3.get(i));
            i++;
            teamIndex++;
            if (teamIndex == teams) {
                teamIndex = 0;
            }
        }


        /*
        PriorityQueue<Map.Entry<String, Integer>> maxPQ = new PriorityQueue<>(
                (a,b) -> a.getValue()==b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue()-b.getValue()
        );

        for (int i = 0; i < level.length; i++) {
            maxPQ.add(new AbstractMap.SimpleEntry<String, Integer>(common[i], level[i]));
        }



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

         */



        model.addAttribute("finalTeams", finalTeams);
        model.addAttribute("level", level);

        return "display";
    }


}
