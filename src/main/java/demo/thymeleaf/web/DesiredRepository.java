package demo.thymeleaf.web;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Spring DATA JPAの Repository
@Repository
public interface DesiredRepository extends JpaRepository<DesiredThing, Integer> {
	//↑継承クラスの型引数には、Entityクラスの型とその＠id(idプロパティ(Primarｙ key)の型を指定する)
}
