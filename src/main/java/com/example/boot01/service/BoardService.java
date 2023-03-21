package com.example.boot01.service;

import com.example.boot01.dto.BoardDTO;
import com.example.boot01.dto.PageRequestDTO;
import com.example.boot01.dto.PageResponseDTO;


public interface BoardService {
    //1.등록 작업 처리
    //BoardService 인터페이스에 register() 선언
    Long register(BoardDTO boardDTO);

    //2) 조회 작업 처리
    // 조회작업 처리는 특정한 게시물의 번호를 이용하므로 BoardService와 BoardServiceImpl에 코드 추가
    BoardDTO readOne(Long bno);

    //3) 수정
    void modify(BoardDTO boardDTO);

    //4) 삭제
    void remove(Long bno);

    //5) list()라는 이름으로 목록/ 검색 기능을 선언.
    PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO);
}
