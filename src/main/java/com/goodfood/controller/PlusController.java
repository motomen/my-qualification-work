package com.goodfood.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.*;
import org.springframework.social.google.api.plus.moments.Moment;
import org.springframework.social.google.api.plus.moments.MomentsPage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Created by Yaroslav on 06.04.2015.
 */
@Controller
@RequestMapping(value="/plus", method = RequestMethod.GET)
public class PlusController {

    private final Google google;

    @Autowired
    public PlusController(Google google) {
        this.google = google;
    }

    @ResponseBody
    @RequestMapping("/people")
    public PeoplePage searchPeople(String query, @RequestParam(required=false) String pageToken) {
        return google.plusOperations().searchPeople(query, pageToken);
    }

    @ResponseBody
    @RequestMapping("/people/{id}")
    public Person getPerson(@PathVariable String id) {
        return google.plusOperations().getPerson(id);
    }

    @ResponseBody
    @RequestMapping("/people/{id}/circles")
    public PeoplePage getCircles(@PathVariable String id, @RequestParam(required=false) String pageToken) {
        return google.plusOperations().getPeopleInCircles(id, pageToken);
    }

    @ResponseBody
    @RequestMapping("/people/{activityId}/plusoners")
    public PeoplePage getPlusOners(@PathVariable String activityId, @RequestParam(required=false) String pageToken) {
        return google.plusOperations().getActivityPlusOners(activityId, pageToken);
    }

    @ResponseBody
    @RequestMapping("/people/{activityId}/resharers")
    public PeoplePage getResharers(@PathVariable String activityId, @RequestParam(required=false) String pageToken) {
        return google.plusOperations().getActivityResharers(activityId, pageToken);
    }

    @ResponseBody
    @RequestMapping("/activities/search")
    public ActivitiesPage searchPublicActivities(String query, @RequestParam(required=false) String pageToken) {
        return google.plusOperations().searchPublicActivities(query, pageToken);
    }

    @ResponseBody
    @RequestMapping("/activities/list/{profileId}")
    public ActivitiesPage getActivities(@PathVariable String profileId, String pageToken) {
        return google.plusOperations().getActivities(profileId, pageToken);
    }

    @ResponseBody
    @RequestMapping("/activities/{id}")
    public Activity getActivity(@PathVariable String id) {
        return google.plusOperations().getActivity(id);
    }

    @ResponseBody
    @RequestMapping("/activities/{activityId}/comments")
    public ActivityCommentsPage getComments(@PathVariable String activityId, @RequestParam(required=false) String pageToken) {
        return google.plusOperations().getComments(activityId, pageToken);
    }

    @ResponseBody
    @RequestMapping("/moments/list")
    public MomentsPage listMoments(String pageToken) {
        return google.plusOperations().getMoments(pageToken);
    }

    @ResponseBody
    @RequestMapping(value="/moments/insert", method=RequestMethod.POST)
    public Moment insertMoment(@RequestBody Moment moment) {
        return google.plusOperations().insertMoment(moment);
    }

    @ResponseBody
    @RequestMapping(value="/moments/delete", method=RequestMethod.POST)
    public void deleteMoment(@RequestBody String id) {
        try{
            google.plusOperations().deleteMoment(id);
        }catch(HttpClientErrorException e) {
            System.out.println(e.getResponseBodyAsString());
        }
    }
}
