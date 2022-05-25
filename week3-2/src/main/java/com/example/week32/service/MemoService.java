package com.example.week32.service;

import com.example.week32.domain.Memo;
import com.example.week32.domain.MemoRepository;
import com.example.week32.domain.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service

public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional //이게 업데이트될 때 DB에 정말 반영이 되야된다고 말해줌
    public boolean update(Long id, MemoRequestDto requestDto) { //boolean 타입으로 변환시켜 밑에서 true or false값을 반환
        Memo memo = memoRepository.findById(id).orElseThrow(    //할 수 있게 함
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")

        );
        if (!memo.getPassword().equals(requestDto.getPassword())) { // ! equals 를 사용하자!! // !== 은 먹히지 않음
            System.out.println("비밀번호가 일치하지 않습니다.");     //repository에 저장된 값이 requestDto(수정된 값)과 다를 때
            return false;
        } else {
            System.out.println("수정완료!");
            memo.update(requestDto);
            return true;
        }

    }
    @Transactional
    public boolean delete(Long id, MemoRequestDto requestDto) { //boolean 타입으로 변환시켜 밑에서 true or false값을 반환
        Memo memo = memoRepository.findById(id).orElseThrow(    //할 수 있게 함
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")

        );
        if (!memo.getPassword().equals(requestDto.getPassword())) { // ! equals 를 사용하자!! // !== 은 먹히지 않음
            System.out.println("비밀번호가 일치하지 않습니다.");     //repository에 저장된 값이 requestDto(수정된 값)과 다를 때
            return false;
        } else {
            System.out.println("삭제완료!");
            memo.update(requestDto);
            memoRepository.deleteById(id);
            return true;
        }

    }
}