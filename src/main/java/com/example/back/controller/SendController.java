package com.example.back.controller;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {

    @GetMapping("/webhook")
    @ResponseBody
    public void webhookSend() {
        SlackApi slackApi = new SlackApi("https://hooks.slack.com/services/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");
        slackApi.call(new SlackMessage("#webhook","TestSender", "check"));
    }
}
