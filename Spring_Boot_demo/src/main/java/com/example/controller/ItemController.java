package com.example.controller;

import com.example.model.ItemInfo;
import com.example.repository.ItemRepository;
import com.example.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {
    @Autowired
    ItemService itemService;

    @PostMapping(value = "/item")
    public Object Item(@RequestBody(required = false) ItemInfo item) {
        return itemService.Itemput(item);
    }

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping(value = "/itemget")
    public List<ItemInfo> itemInfoList() {
        return itemRepository.findAll();
    }

    /**
     * 按照itemname查询活动
     *
     * @return
     */
    @GetMapping(value = "itemget/getname/{itemname}")
    public List<ItemInfo> actListByItemname(@PathVariable("itemname") String itemname) {
        return itemRepository.findByItemname(itemname);
    }

}
