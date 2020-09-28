package com.ppphuang.web.service;

import com.ppphuang.web.beans.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface TagService {
    Tag saveTag(Tag tag);

    Tag getTag(Long id);

    Tag getTagByName(String name);

    Page<Tag> listTag(Pageable pageable);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);
}
