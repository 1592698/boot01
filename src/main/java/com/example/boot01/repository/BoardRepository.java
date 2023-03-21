package com.example.boot01.repository;

import com.example.boot01.domain.Board;
import com.example.boot01.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

/*
*2. JpaRepository 인터페이스
* Spring Data JPA를 이용할 떄는 JpaRepository라는 인터페이스를 이용해서 인터페이스 선언만으로
* 데이터베이스 관련 작업을 어느 정도 처리 할 수 있음.
* 개발 단계에서 JpaRepository 인터페이스를 상속하는 인터페이스를 선언하는 것만으로 crud와 페이징 처리가 모두 완료*/
//JpaRepository 인터페이스를 상속 할 때에는 엔티티타입과 @Id 타입을 지정해 주어야 하는 점을 제외하면 아무런 코드가 없어도 개발가능

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
}

/*
3. 테스트 코드를 통한 CRUD / 페이징 처리 확인.
Spring Data JPA를 이용하면 SQL의 개발도 거의 없고, JPS의 많은 기능을 활용할 수 있지만 항상 테스트 코드로
동작 여부를 확인하는 것이 좋음.
프로젝트에 test에 repository 패키지를 추가하고 BoardRepository 클래스를 추가.
*/