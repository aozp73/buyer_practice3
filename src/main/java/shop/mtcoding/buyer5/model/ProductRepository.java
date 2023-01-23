package shop.mtcoding.buyer5.model;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProductRepository {
        public int insert(@Param("name") String name, @Param("price") int price,
                        @Param("qty") int qty);

        public List<Product> findAll();

        public Product findById(int id);

        public int updateById(@Param("name") String name, @Param("price") int price,
                        @Param("qty") int qty, @Param("id") int id);

        public int deleteById(int id);
}