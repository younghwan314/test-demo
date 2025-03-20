package com.example.testdemo.controller;

import com.example.testdemo.dto.MemoRequestDto;
import com.example.testdemo.dto.MemoResponseDto;
import com.example.testdemo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoResponseDto> save(@RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.save(dto));
    }

    @GetMapping("/memos")
    public ResponseEntity<List<MemoResponseDto>> findAll() {
        return ResponseEntity.ok(memoService.findAll());
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> findOne(@PathVariable Long memoId) {
        return ResponseEntity.ok(memoService.findById(memoId));
    }

    @PutMapping("/memos/{memoId}")
    public ResponseEntity<MemoResponseDto> update(@PathVariable Long memoId, @RequestBody MemoRequestDto dto) {
        return ResponseEntity.ok(memoService.update(memoId, dto));
    }

    @DeleteMapping("/memos/{memoId}")
    public void delete(@PathVariable Long memoId) {
        memoService.deleteById(memoId);
    }
}
