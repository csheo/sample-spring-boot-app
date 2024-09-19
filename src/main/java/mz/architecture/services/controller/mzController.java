package mz.architecture.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import mz.architecture.services.domain.mz;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class mzController {

    private final Logger LOG = LoggerFactory.getLogger(mzController.class);
    private final List<mz> objs = new ArrayList<>();

    @GetMapping
    public List<mz> findAll() {
        return objs;
    }

    @GetMapping("/{id}")
    public mz findById(@PathVariable("id") Long id) {
        mz obj = objs.stream().filter(it -> it.getId().equals(id))
                .findFirst()
                .orElseThrow();
        LOG.info("Found: {}", obj.getId());
        return obj;
    }

    @PostMapping
    public mz add(@RequestBody mz obj) {
        obj.setId((long) (objs.size() + 1));
        LOG.info("Added: {}", obj);
        objs.add(obj);
        return obj;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        mz obj = objs.stream().filter(it -> it.getId().equals(id)).findFirst().orElseThrow();
        objs.remove(obj);
        LOG.info("Removed: {}", id);
    }

    @PutMapping
    public void update(@RequestBody mz obj) {
        mz objTmp = objs.stream()
                .filter(it -> it.getId().equals(obj.getId()))
                .findFirst()
                .orElseThrow();
        objs.set(objs.indexOf(objTmp), obj);
        LOG.info("Updated: {}", obj.getId());
    }

}