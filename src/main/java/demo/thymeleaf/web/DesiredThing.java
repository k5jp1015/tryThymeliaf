package demo.thymeleaf.web;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//↓注：このクラスが（エンティティクラスとして）JPAの管理下に自動でおかれることを示すアノテーション
@Entity
public class DesiredThing {
		//↓注：下に続くidという変数をプライマリキーにするという意味．
        @Id
        //↓注：idは，値を自動的に生成することを意味．
        @GeneratedValue
        protected Integer id;
        protected String name;
        protected String disired;
        protected String reason;

        public DesiredThing() {
          super();
        }

        public DesiredThing(String name, String disired, String reason) {

          super();
          this.name = name;
          this.disired  = disired;
          this.reason = reason;
        }

        // for debug
        public String toString() {
          return "DesiredThing[ 名前：" + name + ",欲しいもの：" + disired + ",理由：" + reason + "]";

        }
}

