package com.uni.project.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageableUtils {

    /**
     * Determines the page size to be used, applying a default value of 5 if both
     * page and pageSize are set to 0.
     */
    public int determinePageSize(int page, int pageSize) {
        return (page == 0 && pageSize == 0) ? 5 : pageSize;
    }

    public int determineTopicPageSize(int page, int pageSize) {
        return (page == 0 && pageSize == 0) ? 10 : pageSize;
    }

}
