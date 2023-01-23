package shop.mtcoding.buyer5.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.buyer5.model.Product;
import shop.mtcoding.buyer5.model.ProductRepository;
import shop.mtcoding.buyer5.model.Purchase;
import shop.mtcoding.buyer5.model.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Transactional
    public int 구매하기(int principalId, int productId, int count) {
        // 상품 존재확인
        Product product = productRepository.findById(productId);
        if (product == null) {
            return -1;
        }

        // 구매재고 대비 현재고 비교
        if (product.getQty() < count) {
            return -1;
        }

        // product_tb 반영 (update)
        int res1 = productRepository.updateById(product.getName(), product.getPrice(), product.getQty() - count,
                product.getId());
        if (res1 != 1) {
            return -1;
        }

        // purchase_tb 반영 (insert)
        int res2 = purchaseRepository.insert(principalId, productId, count);
        if (res2 != 1) {
            return -1;
        }

        return 1;
    }

    @Transactional
    public int 구매목록삭제(int purchaseId) {
        // 목록에 있는지 체크
        Purchase purchase = purchaseRepository.findById(purchaseId);
        if (purchase == null) {
            return -1;
        }

        // product_tb에 반영 (update)
        int productId = purchase.getProductId();
        Product product = productRepository.findById(productId);
        int res1 = productRepository.updateById(product.getName(), product.getPrice(),
                product.getQty() + purchase.getCount(),
                productId);
        if (res1 != 1) {
            return -1;
        }

        // purchase_tb 반영 (delete)
        int res2 = purchaseRepository.deleteById(purchaseId);
        if (res2 != 1) {
            return -1;
        }

        return 1;
    }
}
