package com.example.repository;

import com.example.model.ItemInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<ItemInfo, Integer> {

    public List<ItemInfo> findByItemid(Integer itemid);

    public List<ItemInfo> findByItemname(String itemname);
}
