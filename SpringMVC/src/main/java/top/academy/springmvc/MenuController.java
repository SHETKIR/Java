package top.academy.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class MenuController {

    private List<Menu> menus = new ArrayList<>();

    @GetMapping("/menus")
    public String listMenus(Model model) {
        model.addAttribute("menus", menus);
        return "menus";
    }

    @GetMapping("/menu/new")
    public String newMenuForm(Model model) {
        model.addAttribute("menu", new Menu());
        return "menu-form";
    }

    @PostMapping("/menu")
    public String saveMenu(@RequestParam String name,
                          @RequestParam String description,
                          @RequestParam BigDecimal price,
                          @RequestParam String category) {
        Menu menu = new Menu();
        menu.setId(generateUniqueId());
        menu.setName(name);
        menu.setDescription(description);
        menu.setPrice(price);
        menu.setCategory(category);

        menus.add(menu);

        return "redirect:/menus";
    }

    private Long generateUniqueId() {
        Random random = new Random();
        return Math.abs(random.nextLong());
    }
}