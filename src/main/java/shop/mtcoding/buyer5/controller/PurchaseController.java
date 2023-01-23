package shop.mtcoding.buyer5.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import shop.mtcoding.buyer5.dto.PurchaseAllDto;
import shop.mtcoding.buyer5.model.PurchaseRepository;
import shop.mtcoding.buyer5.model.User;
import shop.mtcoding.buyer5.service.PurchaseService;

@Controller
public class PurchaseController {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    HttpSession session;

    @Autowired
    PurchaseService purchaseService;

    @PostMapping("/product/{id}/purchase")
    public String purchase(@PathVariable int id, int count) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        int res = purchaseService.구매하기(principal.getId(), id, count);
        if (res == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/";
    }

    @GetMapping("/purchase")
    public String purchaseList(Model model) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        List<PurchaseAllDto> purchaseList = purchaseRepository.findByUserId(principal.getId());
        model.addAttribute("purchaseList", purchaseList);

        return "purchase/list";
    }

    @PostMapping("/purchase/{id}/delete")
    public String delete(@PathVariable int id) {
        // 인증
        User principal = (User) session.getAttribute("principal");
        if (principal == null) {
            return "redirect:/notfound";
        }

        int res = purchaseService.구매목록삭제(id);
        if (res == -1) {
            return "redirect:/notfound";
        }

        return "redirect:/purchase";
    }
}
