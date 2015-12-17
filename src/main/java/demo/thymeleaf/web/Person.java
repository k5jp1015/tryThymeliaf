package demo.thymeleaf.web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//↓注：このクラスが（エンティティクラスとして）JPAの管理下に自動でおかれることを示すアノテーション
@Entity
public class Person {
		//↓注：下に続くidという変数をプライマリキーにするという意味．
        @Id
        //↓注：idは，値を自動的に生成することを意味．
        @GeneratedValue
        protected Integer id;
        protected String name;
        protected String tel;
        protected String mail;
        protected String description;

        public Person() {
          super();
        }

        public Person(String name, String tel, String mail, String description) {

          super();
          this.name = name;
          this.tel  = tel;
          this.mail = mail;
          this.description = description;
        }

        // for debug
        public String toString() {
          return "[name:" + name + ", tel:" + tel + ", mail:" + mail + ", description:" + description + "]";
        }
}
