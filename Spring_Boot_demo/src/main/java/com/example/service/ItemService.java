package com.example.service;

import com.example.model.ItemInfo;
import com.example.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    public Object Itemput(ItemInfo item) {
        List<ItemInfo> list = itemRepository.findByItemname(item.getItemname());
        if (list.size() > 0) {
            return "fail";//添加失败
        } else {
            itemRepository.save(item);
            return "success";//添加成功
        }
    }
}
