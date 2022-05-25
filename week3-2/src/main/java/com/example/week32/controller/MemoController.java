package com.example.week32.controller;

import com.example.week32.domain.Memo;
import com.example.week32.domain.MemoRepository;
import com.example.week32.domain.MemoRequestDto;
import com.example.week32.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @PostMapping("/api/memos")
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos")
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }




    @DeleteMapping("/api/memos/{id}")
    public Long deleteBlog(@PathVariable Long id,@RequestBody MemoRequestDto requestDto) {
        if(memoService.delete(id, requestDto)){//blogService.update(id, requestDto) == true로 이해하면 될 것 같다. // ctrl + update 누르면 사용된 위치로 가게됨
            return 1L; //service class에서 true를 반환하면 1로 return // L은 Long 타입으로 선언하였기 떄문에 붙여줘야 에러가 안남!
        } else {
            return 0L; //service class에서 false를 반환하면 0로 return
        }

    }
    @PutMapping ("/api/memos/{id}")
    public Long updateBlog(@PathVariable Long id,@RequestBody MemoRequestDto requestDto){
        if(memoService.update(id, requestDto)){//blogService.update(id, requestDto) == true로 이해하면 될 것 같다. // ctrl + update 누르면 사용된 위치로 가게됨
            return 1L; //service class에서 true를 반환하면 1로 return // L은 Long 타입으로 선언하였기 떄문에 붙여줘야 에러가 안남!
        } else {
            return 0L; //service class에서 false를 반환하면 0로 return
        }

    }
}