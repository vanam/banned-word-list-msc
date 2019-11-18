package com.martinvana.bannedwordlist.controller;

import java.util.List;

import com.martinvana.bannedwordlist.service.BannedWordListService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST API.
 */
@RestController
@RequestMapping("/api/v1")
public class BannedWordListController {

    /**
     * Banned word list business logic.
     */
    private final BannedWordListService bannedWordListService;

    /**
     * @param bannedWordListService Banned word list business logic.
     */
    public BannedWordListController(BannedWordListService bannedWordListService) {
        this.bannedWordListService = bannedWordListService;
    }

    /**
     * @param count Number of banned words.
     * @return List of {@code count} random banned words.
     */
    @GetMapping("/list/{count}")
    public List<String> getClient(@PathVariable("count") int count) {
        return bannedWordListService.getBannedWordList(count);
    }
}
