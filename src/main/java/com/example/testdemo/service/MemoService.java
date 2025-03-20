package com.example.testdemo.service;

import com.example.testdemo.dto.MemoRequestDto;
import com.example.testdemo.dto.MemoResponseDto;
import com.example.testdemo.entity.Memo;
import com.example.testdemo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getContent());
        Memo savedMemo = memoRepository.save(memo);
        return new MemoResponseDto(savedMemo.getId(), savedMemo.getContent());
    }

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {
        List<Memo> memos = memoRepository.findAll();

        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            MemoResponseDto dto = new MemoResponseDto(memo.getId(), memo.getContent());
            dtos.add(dto);
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto update(Long memoId, MemoRequestDto dto) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 메모가 없습니다.")
        );

        memo.update(dto.getContent());
        return new MemoResponseDto(memo.getId(), memo.getContent());
    }

    @Transactional
    public void deleteById(Long memoId) {
        if(!memoRepository.existsById(memoId)) {
            throw new IllegalArgumentException("해당하는 메모가 없습니다.");
        }
        memoRepository.deleteById(memoId);
    }
}
