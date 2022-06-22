package com.example.accountuser.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class Utility {
  public static final int DEFAULT_PAGE_SIZE = 10;
  public static final int DEFAULT_PAGE_OFFSET = 0;

  public static final String DEFAULT_SORT_BY = "id";

  public static Pageable createPageableObject(Integer offset, Integer size, String sortBy) {
    final Integer pageSize = Optional.ofNullable(size).orElse(DEFAULT_PAGE_SIZE);
    final Integer pageOffset = Optional.ofNullable(offset).orElse(DEFAULT_PAGE_OFFSET);
    final String pageSortBy = Optional.ofNullable(sortBy).orElse(DEFAULT_SORT_BY);
    return PageRequest.of(pageOffset, pageSize, Sort.by(pageSortBy).ascending());
  }
}
