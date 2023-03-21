package com.example.boot01.repository;

import com.example.boot01.domain.Board;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
    @Autowired
    private BoardRepository boardRepository;

    /*
     * 1) insert 기능 테스트
     * 데이터베이스에 insert를 실행하는 기능은 JpaRepository의 save()를 통해서 이루어짐.
     * save()는 현재의 영속 컨텍스트 내에 데이터가 존재하는지 찾아보고 해당 엔티티 객체가 없을 때는
     * insert를, 존재 할 때는 update를 자동으로 실행
     **/
    @Test
    public void testInsert() {
        for(int i=1; i<=100; i++){
            Board board = Board.builder()
                    .title("title...")
                    .content("content..." + i)
                    .writer("user" + (i % 10))
                    .build();
            Board result = boardRepository.save(board);
            log.info("BNO : " + result.getBno());
        }
    }

    @Test
    public void testSelect() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();
        log.info(board);
    }

    @Test
    public void testUpdate() {
        Long bno = 100L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board = result.orElseThrow();

        board.change("update... title 100", "update content 100");
        boardRepository.save(board);
    }

    @Test
    public void testUpdate2() {
        Long bno = 100L;
        Board board = Board.builder()
                .title("title...")
                .content("content... update2")
                .build();
       boardRepository.save(board);
    }
    //오류남 null값 없어야 해서

    @Test
    public void testUpdate3() {
        //없는 bno를 지정한 경우
        Long bno = 1000L;
        Board board = Board.builder()
                .bno(bno)
                .title("title...")
                .content("content... update3")
                .writer("user..update")
                .build();
        boardRepository.save(board);
    }

    @Test
    public void testDelete() {
        Long bno = 2L;
        boardRepository.deleteById(bno);
    }

    /*
    * 수정이나 삭제 시에 굳이 select문이 먼저 실행되는 이유는 JPA의 동작 방식과 관련.
    JPA를 이용한다는 것은 엄밀하게 말하면 영속 컨텍스트와 데이터베이스를 동기화 해서 관리한다는 의미.
    그러므로 특정한 엔티티 객체가 추가되면 영속 컨텍스트에 추가하고, 데이터베이스와 동기화가 이루어짐.
    마찬가지로 수정이나 삭제를 한다면 영속 컨텍스트에 해당 엔티티 객체거 존재해야만 하므로
    select로 엔티티 객체가 존재해야만 하므로 먼저 select로 엔티티 객체를 영속 컨텍스트에 저장해서 이를 삭제한 후에 delete가 이루어짐.
    5) Pageable과 Page<E> 타입
    Spring Data JPA를 이용해서 별도의 코드 없이도 CRUD가 실행되지만,
    페이징 처리도 가능.
    페이징 처리는 Pageable이라는 타입의 객체를 구성해서 파라미터로 전달하면 됨.
    Pageable은 인터페이스로 설계되어 있고, 일반적으로는 PageRequest.of()라는 기능을 이용해서 개발이 가능.
    *PageRequest.of(페이지 번호, 사이즈) : 페이지 번호은 0부터
    * PageRequest.of(페이지 번호, 사이즈, Sort) : 정렬 조건 추가
    * PageRequest.of(페이지 번호, 사이즈, Sort.Direction, 속성...) : 정렬 방향과 여러 속성 지정.
    파라미터로 Pageable을 이용하면 리턴 타입은 Page<T> 타입을 이용할 수 있는데
    이는 단순 목록뿐 아니라 페이징 처리에 데이터가 많은 경우에는 count 처리를 자동으로 실행. 대부분의 Pageable 파라이터는 메서드 마지막에 사용하고, 파라미터에 Pageable이 있는 경우에는 메서드의 리턴 타입을 Page<T> 타입으로 설계.
    JpaRepository에는 findAll()이라는 기능을 제공하여 기본적인 페이징 처리를 지원.
    * */
    /*
     * findAll()의 리턴 타입으로 나오는 Page<T> 타입은 내부적으로 페이징 처리에 필요한 여러 정보를 처리.
     * 예) 다음 페이지가 존재 하는지, 이전 페이지가 존재하는지, 전체 데이터의 개수는 몇 개인지
     * 등의 기능들을 모두 알아 낼 수 있음.*/
    @Test
    public void testPaging(){
        // 1 page order by bno desc
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);

        log.info("total count: " + result.getTotalElements());
        log.info("total page: " + result.getTotalPages());
        log.info("page number: " + result.getNumber());
        log.info("page size: " + result.getSize());

        //prev, next
        log.info(result.hasPrevious() + ": " + result.hasNext());
        List<Board> boardList = result.getContent();

        boardList.forEach(board->log.info(board));


    }


    @Test
    public void testSearch1() {
        //2 page order by bno desc
        Pageable pageable = PageRequest.of(1,10,Sort.by("bno").descending());
        boardRepository.search1(pageable);

    }

    @Test
    public void testSearchAll() {
        String[] types = {"t", "c", "w"};
        String keyword = "1";
        Pageable pageable = PageRequest.of(0,10,Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);
    }

    @Test
    public void testSearchAll2(){
        String[] types={"t","c","w"};
        String keyword="1";
        Pageable pageable =PageRequest.of(0, 10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);

        //total pages
        log.info(result.getTotalPages());

        //page size
        log.info(result.getSize());

        //pageNumber
        log.info(result.getNumber());

        //prev next
        log.info(result.hasPrevious() + ": " + result.hasNext());
        result.getContent().forEach(board -> log.info(board));
    }
}



