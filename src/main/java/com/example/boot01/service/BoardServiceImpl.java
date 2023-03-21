package com.example.boot01.service;


import com.example.boot01.domain.Board;
import com.example.boot01.dto.BoardDTO;
import com.example.boot01.dto.PageRequestDTO;
import com.example.boot01.dto.PageResponseDTO;
import com.example.boot01.repository.BoardRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
@Transactional
public class BoardServiceImpl implements BoardService {
    private final ModelMapper modelMapper;
    private final BoardRepository boardRepository;

    @Override
    public Long register(BoardDTO boardDTO){
        Board board = modelMapper.map(boardDTO, Board.class);
        Long bno = boardRepository.save(board).getBno();
        return bno;
    }

    @Override
    public BoardDTO readOne(Long bno){
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        BoardDTO boardDTO = modelMapper.map(board, BoardDTO.class);
        return boardDTO;
    }

    //3) 수정 작업 처리
    //수정 작업은 기존 엔티티 객체에서 필요한 부분만 변경하도록 작성
    @Override
    public void modify(BoardDTO boardDTO){
        Optional<Board> result = boardRepository.findById(boardDTO.getBno());
        Board board = result.orElseThrow();
        board.change(boardDTO.getTitle(), boardDTO.getContent());
        boardRepository.save(board);
    }

    //삭제
    @Override
    public void remove(Long bno){
        boardRepository.deleteById(bno);
    }

    //목록/ 검색 기능
    @Override
    public PageResponseDTO<BoardDTO> list(PageRequestDTO pageRequestDTO){
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = (Pageable) pageRequestDTO.getPageable("bno");
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        List<BoardDTO> boardDTOList = result.getContent().stream()
                .map(board->modelMapper.map(board, BoardDTO.class))
                .collect(Collectors.toList());

        return PageResponseDTO.<BoardDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .boardList(boardDTOList)
                .total((int)result.getTotalElements())
                .build();
    }
}
