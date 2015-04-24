package com.goodfood.service.impl;

import com.goodfood.dao.LinkDao;
import com.goodfood.model.Link;
import com.goodfood.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yaroslav on 24.04.2015.
 */
@Service
@Transactional
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkDao linkDao;

    @Override
    public void addLink(Link link) {
        linkDao.addLink(link);
    }
}
